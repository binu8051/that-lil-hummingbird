package com.charlesmadere.hummingbird.networking;

import android.support.annotation.Nullable;
import android.text.TextUtils;

import com.charlesmadere.hummingbird.misc.Constants;
import com.charlesmadere.hummingbird.misc.CurrentUser;
import com.charlesmadere.hummingbird.misc.RetrofitUtils;
import com.charlesmadere.hummingbird.misc.Timber;
import com.charlesmadere.hummingbird.models.AbsAnime;
import com.charlesmadere.hummingbird.models.AnimeV1;
import com.charlesmadere.hummingbird.models.AnimeV2;
import com.charlesmadere.hummingbird.models.AuthInfo;
import com.charlesmadere.hummingbird.models.ErrorInfo;
import com.charlesmadere.hummingbird.models.LibraryEntry;
import com.charlesmadere.hummingbird.models.LibraryUpdate;
import com.charlesmadere.hummingbird.models.Story;
import com.charlesmadere.hummingbird.models.User;
import com.charlesmadere.hummingbird.models.WatchingStatus;
import com.charlesmadere.hummingbird.preferences.Preferences;

import java.io.IOException;
import java.lang.annotation.Annotation;
import java.util.ArrayList;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Converter;
import retrofit2.Response;
import retrofit2.Retrofit;

public final class Api {

    private static final String TAG = "Api";


    public static void addOrUpdateLibraryEntry(final String id, final LibraryUpdate libraryUpdate,
            final ApiResponse<LibraryEntry> listener) {
        getApi().addOrUpdateLibraryEntry(id, libraryUpdate).enqueue(new Callback<LibraryEntry>() {
            @Override
            public void onResponse(final Call<LibraryEntry> call,
                    final Response<LibraryEntry> response) {
                if (response.isSuccessful()) {
                    listener.success(response.body());
                } else {
                    listener.failure(retrieveErrorInfo(response));
                }
            }

            @Override
            public void onFailure(final Call<LibraryEntry> call, final Throwable t) {
                Timber.e(TAG, "add or update library entry (" + id + ") call failed", t);
                listener.failure(null);
            }
        });
    }

    public static void authenticate(final AuthInfo authInfo, final ApiResponse<Void> listener) {
        getApi().authenticate(authInfo).enqueue(new Callback<String>() {
            @Override
            public void onResponse(final Call<String> call, final Response<String> response) {
                if (response.isSuccessful()) {
                    final String body = response.body();

                    if (TextUtils.isEmpty(body)) {
                        listener.failure(retrieveErrorInfo(response));
                    } else {
                        Preferences.Account.AuthToken.set(body);
                        Preferences.Account.Username.set(authInfo.getUsername());
                        listener.success(null);
                    }
                } else {
                    listener.failure(retrieveErrorInfo(response));
                }
            }

            @Override
            public void onFailure(final Call<String> call, final Throwable t) {
                Timber.e(TAG, "authenticate call failed", t);
                listener.failure(null);
            }
        });
    }

    public static void getActivityFeed(final String username,
            final ApiResponse<ArrayList<Story>> listener) {
        getApi().getActivityFeed(username).enqueue(new Callback<ArrayList<Story>>() {
            @Override
            public void onResponse(final Call<ArrayList<Story>> call,
                    final Response<ArrayList<Story>> response) {
                if (response.isSuccessful()) {
                    listener.success(response.body());
                } else {
                    listener.failure(retrieveErrorInfo(response));
                }
            }

            @Override
            public void onFailure(final Call<ArrayList<Story>> call, final Throwable t) {
                Timber.e(TAG, "get user (" + username + ") activity feed call failed", t);
                listener.failure(null);
            }
        });
    }

    public static void getActivityFeed(final User user,
            final ApiResponse<ArrayList<Story>> listener) {
        getActivityFeed(user.getName(), listener);
    }

    public static void getAnimeById(final AbsAnime anime, final ApiResponse<AnimeV2> listener) {
        if (anime.getVersion() == AbsAnime.Version.V1) {
            getAnimeById(anime.getId(), listener);
        } else {
            listener.success((AnimeV2) anime);
        }
    }

    public static void getAnimeById(final String id, final ApiResponse<AnimeV2> listener) {
        getApi().getAnimeById(Constants.API_KEY, id).enqueue(new Callback<AnimeV2>() {
            @Override
            public void onResponse(final Call<AnimeV2> call, final Response<AnimeV2> response) {
                AnimeV2 body = null;

                if (response.isSuccessful()) {
                    body = response.body();
                }

                if (body == null) {
                    listener.failure(retrieveErrorInfo(response));
                } else {
                    listener.success(body);
                }
            }

            @Override
            public void onFailure(final Call<AnimeV2> call, final Throwable t) {
                Timber.e(TAG, "get anime (" + id + ") by id call failed ", t);
                listener.failure(null);
            }
        });
    }

    public static void getAnimeByMyAnimeListId(final AbsAnime anime,
            final ApiResponse<AnimeV2> listener) {
        if (anime.getVersion() == AbsAnime.Version.V1) {
            final AnimeV1 animeV1 = (AnimeV1) anime;
            getAnimeByMyAnimeListId(animeV1.getMyAnimeListId(), listener);
        } else {
            listener.success((AnimeV2) anime);
        }
    }

    public static void getAnimeByMyAnimeListId(final String id,
            final ApiResponse<AnimeV2> listener) {
        getApi().getAnimeByMyAnimeListId(Constants.API_KEY, id).enqueue(new Callback<AnimeV2>() {
            @Override
            public void onResponse(final Call<AnimeV2> call, final Response<AnimeV2> response) {
                AnimeV2 body = null;

                if (response.isSuccessful()) {
                    body = response.body();
                }

                if (body == null) {
                    listener.failure(retrieveErrorInfo(response));
                } else {
                    listener.success(body);
                }
            }

            @Override
            public void onFailure(final Call<AnimeV2> call, final Throwable t) {
                Timber.e(TAG, "get anime (" + id + ") by My Anime List id call failed", t);
                listener.failure(null);
            }
        });
    }

    public static void getCurrentUser(final ApiResponse<User> listener) {
        getUser(Preferences.Account.Username.get(), new ApiResponse<User>() {
            @Override
            public void failure(@Nullable final ErrorInfo error) {
                listener.failure(error);
            }

            @Override
            public void success(final User user) {
                CurrentUser.set(user);
                listener.success(user);
            }
        });
    }

    public static void getCurrentUserActivityFeed(final ApiResponse<ArrayList<Story>> listener) {
        getActivityFeed(Preferences.Account.Username.get(), listener);
    }

    public static void getCurrentUserLibraryEntries(@Nullable final WatchingStatus watchingStatus,
            final ApiResponse<ArrayList<LibraryEntry>> listener) {
        getLibraryEntries(Preferences.Account.Username.get(), watchingStatus, listener);
    }

    public static void getLibraryEntries(final String username,
            @Nullable final WatchingStatus watchingStatus,
            final ApiResponse<ArrayList<LibraryEntry>> listener) {
        getApi().getLibraryEntries(username, watchingStatus)
                .enqueue(new Callback<ArrayList<LibraryEntry>>() {
            @Override
            public void onResponse(final Call<ArrayList<LibraryEntry>> call,
                    final Response<ArrayList<LibraryEntry>> response) {
                if (response.isSuccessful()) {
                    listener.success(response.body());
                } else {
                    listener.failure(retrieveErrorInfo(response));
                }
            }

            @Override
            public void onFailure(final Call<ArrayList<LibraryEntry>> call, final Throwable t) {
                Timber.e(TAG, "get library entries for user (" + username + ") failed", t);
                listener.failure(null);
            }
        });
    }

    public static void getLibraryEntries(final User user,
            @Nullable final WatchingStatus watchingStatus,
            final ApiResponse<ArrayList<LibraryEntry>> listener) {
        getLibraryEntries(user.getName(), watchingStatus, listener);
    }

    public static void getUser(final String username, final ApiResponse<User> listener) {
        getApi().getUser(username).enqueue(new Callback<User>() {
            @Override
            public void onResponse(final Call<User> call, final Response<User> response) {
                User body = null;

                if (response.isSuccessful()) {
                    body = response.body();
                }

                if (body == null) {
                    listener.failure(retrieveErrorInfo(response));
                } else {
                    listener.success(body);
                }
            }

            @Override
            public void onFailure(final Call<User> call, final Throwable t) {
                Timber.e(TAG, "get user (" + username + ") call failed", t);
                listener.failure(null);
            }
        });
    }

    @Nullable
    private static ErrorInfo retrieveErrorInfo(final Response response) {
        final Retrofit retrofit = RetrofitUtils.getRetrofit();
        final Converter<ResponseBody, ErrorInfo> converter = retrofit
                .responseBodyConverter(ErrorInfo.class, new Annotation[0]);

        ErrorInfo errorInfo = null;

        try {
            final ResponseBody errorBody = response.errorBody();
            errorInfo = converter.convert(errorBody);

            if (errorInfo != null) {
                Timber.e(TAG, "Received server error: \"" + errorInfo.getError() + '"');
            }
        } catch (final IllegalStateException | IOException e) {
            Timber.w(TAG, "couldn't convert response's error body", e);
        }

        return errorInfo;
    }

    private static HummingbirdApi getApi() {
        return RetrofitUtils.getHummingbirdApi();
    }

}

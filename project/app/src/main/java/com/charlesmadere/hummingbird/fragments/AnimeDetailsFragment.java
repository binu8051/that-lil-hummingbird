package com.charlesmadere.hummingbird.fragments;

import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.StringRes;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import com.charlesmadere.hummingbird.R;
import com.charlesmadere.hummingbird.activities.GalleryActivity;
import com.charlesmadere.hummingbird.misc.Constants;
import com.charlesmadere.hummingbird.models.AnimeDigest;
import com.charlesmadere.hummingbird.models.ShowType;
import com.charlesmadere.hummingbird.models.SimpleDate;
import com.charlesmadere.hummingbird.views.KeyValueTextView;
import com.facebook.drawee.view.SimpleDraweeView;

import java.text.NumberFormat;
import java.util.Locale;

import butterknife.BindView;
import butterknife.OnClick;

public class AnimeDetailsFragment extends BaseFragment {

    private static final String TAG = "AnimeDetailsFragment";
    private static final String KEY_ANIME_DIGEST = "AnimeDigest";

    private AnimeDigest mAnimeDigest;

    @BindView(R.id.ibYouTubeLink)
    ImageButton mYouTubeLink;

    @BindView(R.id.kvtvCommunityRating)
    KeyValueTextView mCommunityRating;

    @BindView(R.id.kvtvAired)
    KeyValueTextView mAired;

    @BindView(R.id.kvtvFinishedAiring)
    KeyValueTextView mFinishedAiring;

    @BindView(R.id.kvtvProducers)
    KeyValueTextView mProducers;

    @BindView(R.id.kvtvStartedAiring)
    KeyValueTextView mStartedAiring;

    @BindView(R.id.sdvPoster)
    SimpleDraweeView mPoster;

    @BindView(R.id.tvAgeRating)
    TextView mAgeRating;

    @BindView(R.id.tvEpisodeCount)
    TextView mEpisodeCount;

    @BindView(R.id.tvGenres)
    TextView mGenres;

    @BindView(R.id.tvShowType)
    TextView mShowType;

    @BindView(R.id.tvSynopsis)
    TextView mSynopsis;

    @BindView(R.id.tvTitle)
    TextView mTitle;


    public static AnimeDetailsFragment create(final AnimeDigest digest) {
        final Bundle args = new Bundle(1);
        args.putParcelable(KEY_ANIME_DIGEST, digest);

        final AnimeDetailsFragment fragment = new AnimeDetailsFragment();
        fragment.setArguments(args);

        return fragment;
    }

    @Override
    public String getFragmentName() {
        return TAG;
    }

    @Override
    public void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        final Bundle args = getArguments();
        mAnimeDigest = args.getParcelable(KEY_ANIME_DIGEST);
    }

    @Override
    public View onCreateView(final LayoutInflater inflater, final ViewGroup container,
            final Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        return inflater.inflate(R.layout.fragment_anime_details, container, false);
    }

    @OnClick(R.id.cvPoster)
    void onPosterClick() {
        startActivity(GalleryActivity.getLaunchIntent(getContext(), mAnimeDigest.getInfo(),
                mAnimeDigest.getInfo().getPosterImage()));
    }

    @Override
    public void onViewCreated(final View view, final Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mTitle.setText(mAnimeDigest.getTitle());

        final AnimeDigest.Info info = mAnimeDigest.getInfo();

        if (info.hasPosterImage()) {
            mPoster.setImageURI(Uri.parse(info.getPosterImage()));
            mPoster.setVisibility(View.VISIBLE);
        }

        if (info.hasShowType()) {
            mShowType.setText(info.getShowType().getTextResId());
            mShowType.setVisibility(View.VISIBLE);
        }

        if (info.hasGenres()) {
            mGenres.setText(info.getGenresString(getResources()));
            mGenres.setVisibility(View.VISIBLE);
        }

        if (info.hasAgeRating()) {
            mAgeRating.setText(info.getAgeRating().getTextResId());
            mAgeRating.setVisibility(View.VISIBLE);

            if (info.hasAgeRatingGuide()) {
                // TODO
            }
        }

        if (info.hasEpisodeCount() && info.getShowType() != ShowType.MOVIE) {
            mEpisodeCount.setText(getResources().getQuantityString(R.plurals.x_episodes,
                    info.getEpisodeCount(), NumberFormat.getInstance()
                            .format(info.getEpisodeCount())));
            mEpisodeCount.setVisibility(View.VISIBLE);
        }

        if (info.hasStartedAiringDate()) {
            if (info.getShowType() == ShowType.MOVIE) {
                setAiringDateView(mAired, R.string.aired, info.getStartedAiringDate());
            } else {
                setAiringDateView(mStartedAiring, R.string.started_airing,
                        info.getStartedAiringDate());

                if (info.hasFinishedAiringDate()) {
                    setAiringDateView(mFinishedAiring, R.string.finished_airing,
                            info.getFinishedAiringDate());
                }
            }
        }

        if (info.hasFinishedAiringDate()) {
            mFinishedAiring.setText(getText(R.string.finished_airing),
                    info.getFinishedAiringDate().getRelativeTimeText(getContext()));
            mFinishedAiring.setVisibility(View.VISIBLE);
        }

//        TODO
//        if (mAnimeV2.hasProducers()) {
//            mProducers.setText(getResources().getQuantityText(R.plurals.producers,
//                    mAnimeV2.getProducers().size()), mAnimeV2.getProducersString(getResources()));
//            mProducers.setVisibility(View.VISIBLE);
//        }

        mCommunityRating.setText(getText(R.string.community_rating),
                String.format(Locale.getDefault(), "%.4f", info.getBayesianRating()));

        if (info.hasYouTubeVideoId()) {
            mYouTubeLink.setVisibility(View.VISIBLE);
        }

        if (info.hasSynopsis()) {
            mSynopsis.setText(info.getSynopsis());
        } else {
            mSynopsis.setText(R.string.no_synopsis_available);
        }
    }

    @OnClick(R.id.ibYouTubeLink)
    void onYouTubeLinkClick() {
        openUrl(Constants.YOUTUBE_BASE_URL + mAnimeDigest.getInfo().getYouTubeVideoId());
    }

    private void setAiringDateView(final KeyValueTextView view, @StringRes final int keyTextResId,
            final SimpleDate date) {
        view.setText(getText(keyTextResId), date.getRelativeTimeText(getContext()));
        view.setVisibility(View.VISIBLE);
    }

}

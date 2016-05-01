package com.charlesmadere.hummingbird.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class MediaStory extends AbsStory implements Parcelable {

    @SerializedName("media")
    private AbsMedia mMedia;


    public AbsMedia getMedia() {
        return mMedia;
    }

    @Override
    public Type getType() {
        return Type.MEDIA_STORY;
    }

    @Override
    protected void readFromParcel(final Parcel source) {
        super.readFromParcel(source);
        mMedia = source.readParcelable(AbsMedia.class.getClassLoader());
    }

    @Override
    public void writeToParcel(final Parcel dest, final int flags) {
        super.writeToParcel(dest, flags);
        dest.writeParcelable(mMedia, flags);
    }

    public static final Creator<MediaStory> CREATOR = new Creator<MediaStory>() {
        @Override
        public MediaStory createFromParcel(final Parcel source) {
            final MediaStory ms = new MediaStory();
            ms.readFromParcel(source);
            return ms;
        }

        @Override
        public MediaStory[] newArray(final int size) {
            return new MediaStory[size];
        }
    };


    public static abstract class AbsMedia implements Parcelable {
        @SerializedName("id")
        private String mId;

        public String getId() {
            return mId;
        }

        public abstract Type getType();

        @Override
        public int describeContents() {
            return 0;
        }

        protected void readFromParcel(final Parcel source) {
            mId = source.readString();
        }

        @Override
        public void writeToParcel(final Parcel dest, final int flags) {
            dest.writeString(mId);
        }


        public enum Type implements Parcelable {
            @SerializedName("anime")
            ANIME,

            @SerializedName("manga")
            MANGA;

            @Override
            public int describeContents() {
                return 0;
            }

            @Override
            public void writeToParcel(final Parcel dest, final int flags) {
                dest.writeInt(ordinal());
            }

            public static final Creator<Type> CREATOR = new Creator<Type>() {
                @Override
                public Type createFromParcel(final Parcel source) {
                    final int ordinal = source.readInt();
                    return values()[ordinal];
                }

                @Override
                public Type[] newArray(final int size) {
                    return new Type[size];
                }
            };
        }
    }


    public static class AnimeMedia extends AbsMedia implements Parcelable {
        @Override
        public Type getType() {
            return Type.ANIME;
        }

        public static final Creator<AnimeMedia> CREATOR = new Creator<AnimeMedia>() {
            @Override
            public AnimeMedia createFromParcel(final Parcel source) {
                final AnimeMedia am = new AnimeMedia();
                am.readFromParcel(source);
                return am;
            }

            @Override
            public AnimeMedia[] newArray(final int size) {
                return new AnimeMedia[size];
            }
        };
    }


    public static class MangaMedia extends AbsMedia implements Parcelable {
        @Override
        public Type getType() {
            return Type.MANGA;
        }

        public static final Creator<MangaMedia> CREATOR = new Creator<MangaMedia>() {
            @Override
            public MangaMedia createFromParcel(final Parcel source) {
                final MangaMedia mm = new MangaMedia();
                mm.readFromParcel(source);
                return mm;
            }

            @Override
            public MangaMedia[] newArray(final int size) {
                return new MangaMedia[size];
            }
        };
    }

}

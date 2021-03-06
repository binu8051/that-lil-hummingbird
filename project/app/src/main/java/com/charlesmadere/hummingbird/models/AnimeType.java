package com.charlesmadere.hummingbird.models;

import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.StringRes;

import com.charlesmadere.hummingbird.R;
import com.google.gson.annotations.SerializedName;

public enum AnimeType implements Parcelable {

    @SerializedName("Movie")
    MOVIE(R.string.movie),

    @SerializedName("Music")
    MUSIC(R.string.music),

    @SerializedName("ONA")
    ONA(R.string.ona),

    @SerializedName("OVA")
    OVA(R.string.ova),

    @SerializedName("Special")
    SPECIAL(R.string.special),

    @SerializedName("TV")
    TV(R.string.tv);

    @StringRes
    private final int mTextResId;


    AnimeType(@StringRes final int textResId) {
        mTextResId = textResId;
    }

    @StringRes
    public int getTextResId() {
        return mTextResId;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(final Parcel dest, final int flags) {
        dest.writeInt(ordinal());
    }

    public static final Creator<AnimeType> CREATOR = new Creator<AnimeType>() {
        @Override
        public AnimeType createFromParcel(final Parcel source) {
            return values()[source.readInt()];
        }

        @Override
        public AnimeType[] newArray(final int size) {
            return new AnimeType[size];
        }
    };

}

package com.charlesmadere.hummingbird.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class WatchlistStatusUpdateSubstory extends AbsSubstory implements Parcelable {

    @SerializedName("new_status")
    private NewStatus mNewStatus;


    public NewStatus getNewStatus() {
        return mNewStatus;
    }

    @Override
    public Type getType() {
        return Type.WATCHLIST_STATUS_UPDATE;
    }

    @Override
    public String toString() {
        return getType().toString() + ':' + mNewStatus.toString();
    }

    @Override
    protected void readFromParcel(final Parcel source) {
        super.readFromParcel(source);
        mNewStatus = source.readParcelable(NewStatus.class.getClassLoader());
    }

    @Override
    public void writeToParcel(final Parcel dest, final int flags) {
        super.writeToParcel(dest, flags);
        dest.writeParcelable(mNewStatus, flags);
    }

    public static final Creator<WatchlistStatusUpdateSubstory> CREATOR = new Creator<WatchlistStatusUpdateSubstory>() {
        @Override
        public WatchlistStatusUpdateSubstory createFromParcel(final Parcel source) {
            final WatchlistStatusUpdateSubstory wsus = new WatchlistStatusUpdateSubstory();
            wsus.readFromParcel(source);
            return wsus;
        }

        @Override
        public WatchlistStatusUpdateSubstory[] newArray(final int size) {
            return new WatchlistStatusUpdateSubstory[size];
        }
    };


    public enum NewStatus implements Parcelable {
        @SerializedName("Completed")
        COMPLETED,

        @SerializedName("Currently Watching")
        CURRENTLY_WATCHING,

        @SerializedName("Dropped")
        DROPPED,

        @SerializedName("On Hold")
        ON_HOLD,

        @SerializedName("Plan to Watch")
        PLAN_TO_WATCH;


        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(final Parcel dest, final int flags) {
            dest.writeInt(ordinal());
        }

        public static final Creator<NewStatus> CREATOR = new Creator<NewStatus>() {
            @Override
            public NewStatus createFromParcel(final Parcel source) {
                final int ordinal = source.readInt();
                return values()[ordinal];
            }

            @Override
            public NewStatus[] newArray(final int size) {
                return new NewStatus[size];
            }
        };
    }

}

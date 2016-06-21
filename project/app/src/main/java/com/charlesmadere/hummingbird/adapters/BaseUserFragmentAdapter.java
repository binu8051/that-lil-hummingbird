package com.charlesmadere.hummingbird.adapters;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.util.SparseArrayCompat;
import android.view.ViewGroup;

import com.charlesmadere.hummingbird.R;
import com.charlesmadere.hummingbird.fragments.AnimeLibraryFragment;
import com.charlesmadere.hummingbird.fragments.BaseFeedFragment;
import com.charlesmadere.hummingbird.fragments.UserProfileFragment;
import com.charlesmadere.hummingbird.models.UserDigest;
import com.charlesmadere.hummingbird.models.WatchingStatus;

import java.lang.ref.WeakReference;

public abstract class BaseUserFragmentAdapter extends FragmentStatePagerAdapter {

    public static final int POSITION_PROFILE = 0;
    public static final int POSITION_FEED = 1;

    private static final WatchingStatus[] ORDER = { WatchingStatus.CURRENTLY_WATCHING,
            WatchingStatus.COMPLETED, WatchingStatus.PLAN_TO_WATCH,
            WatchingStatus.ON_HOLD, WatchingStatus.DROPPED };

    private final Context mContext;
    private final SparseArrayCompat<WeakReference<Fragment>> mFragments;
    private final UserDigest mUserDigest;


    public BaseUserFragmentAdapter(final FragmentActivity activity, final UserDigest digest) {
        this(activity, activity.getSupportFragmentManager(), digest);
    }

    public BaseUserFragmentAdapter(final Context context, final FragmentManager fm,
            final UserDigest digest) {
        super(fm);
        mContext = context;
        mUserDigest = digest;
        mFragments = new SparseArrayCompat<>(getCount());
    }

    @Override
    public void destroyItem(final ViewGroup container, final int position, final Object object) {
        super.destroyItem(container, position, object);
        mFragments.put(position, null);
    }

    @Override
    public int getCount() {
        return WatchingStatus.values().length + 2;
    }

    protected abstract BaseFeedFragment createFeedFragment();

    public BaseFeedFragment getFeedFragment() {
        return (BaseFeedFragment) mFragments.get(POSITION_FEED).get();
    }

    @Override
    public Fragment getItem(final int position) {
        final Fragment fragment;

        switch (position) {
            case POSITION_PROFILE:
                fragment = UserProfileFragment.create(mUserDigest);
                break;

            case POSITION_FEED:
                fragment = createFeedFragment();
                break;

            default:
                final WatchingStatus watchingStatus = ORDER[position - 2];
                fragment = AnimeLibraryFragment.create(mUserDigest.getUserId(), watchingStatus);
                break;
        }

        mFragments.put(position, new WeakReference<>(fragment));
        return fragment;
    }

    @Override
    public CharSequence getPageTitle(final int position) {
        final int pageTitleResId;

        switch (position) {
            case POSITION_PROFILE:
                pageTitleResId = R.string.profile;
                break;

            case POSITION_FEED:
                pageTitleResId = R.string.feed;
                break;

            default:
                final WatchingStatus watchingStatus = ORDER[position - 2];
                pageTitleResId = watchingStatus.getTextResId();
                break;
        }

        return mContext.getText(pageTitleResId);
    }

    public UserDigest getUserDigest() {
        return mUserDigest;
    }

    @Override
    public Object instantiateItem(final ViewGroup container, final int position) {
        final Fragment fragment = (Fragment) super.instantiateItem(container, position);
        mFragments.put(position, new WeakReference<>(fragment));
        return fragment;
    }

}

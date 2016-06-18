package com.charlesmadere.hummingbird.views;

import android.annotation.TargetApi;
import android.content.Context;
import android.net.Uri;
import android.os.Build;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.charlesmadere.hummingbird.R;
import com.charlesmadere.hummingbird.misc.CurrentUser;
import com.charlesmadere.hummingbird.models.AppNewsStatus;
import com.charlesmadere.hummingbird.models.User;
import com.charlesmadere.hummingbird.preferences.Preference;
import com.charlesmadere.hummingbird.views.NavigationDrawerItemView.Entry;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class NavigationDrawerView extends ScrimInsetsFrameLayout implements
        Preference.OnPreferenceChangeListener<AppNewsStatus> {

    private NavigationDrawerItemView[] mNavigationDrawerItemViews;

    @BindView(R.id.avatarView)
    AvatarView mAvatar;

    @BindView(R.id.sdvCoverImage)
    SimpleDraweeView mCoverImage;

    @BindView(R.id.proBadge)
    TextView mProBadge;

    @BindView(R.id.tvUsername)
    TextView mUsername;


    public NavigationDrawerView(final Context context, final AttributeSet attrs) {
        super(context, attrs);
    }

    public NavigationDrawerView(final Context context, final AttributeSet attrs,
            final int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public NavigationDrawerView(final Context context, final AttributeSet attrs,
            final int defStyleAttr, final int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    private void findAllNavigationDrawerItemViewChildren() {
        final List<NavigationDrawerItemView> navigationDrawerItemViews = new ArrayList<>();
        findNavigationDrawerItemViewChildren(this, navigationDrawerItemViews);

        if (navigationDrawerItemViews.isEmpty()) {
            throw new IllegalStateException("couldn't find a single NavigationDrawerItemView"
                    + " within the NavigationDrawerView");
        }

        mNavigationDrawerItemViews = new NavigationDrawerItemView[navigationDrawerItemViews.size()];
        navigationDrawerItemViews.toArray(mNavigationDrawerItemViews);
    }

    private void findNavigationDrawerItemViewChildren(final View view,
            final List<NavigationDrawerItemView> navigationDrawerItemViews) {
        if (view instanceof NavigationDrawerItemView) {
            navigationDrawerItemViews.add((NavigationDrawerItemView) view);
        } else if (view instanceof ViewGroup) {
            final ViewGroup viewGroup = (ViewGroup) view;

            for (int i = 0; i < viewGroup.getChildCount(); ++i) {
                findNavigationDrawerItemViewChildren(viewGroup.getChildAt(i),
                        navigationDrawerItemViews);
            }
        }
    }

    public NavigationDrawerItemView getNavigationDrawerItemView(final Entry entry) {
        for (final NavigationDrawerItemView view : mNavigationDrawerItemViews) {
            if (view.getEntry() == entry) {
                return view;
            }
        }

        throw new IllegalStateException(entry + " is missing from the navigation drawer");
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();

        if (isInEditMode()) {
            return;
        }

        ButterKnife.bind(this);
        findAllNavigationDrawerItemViewChildren();

        final User user = CurrentUser.get().getUser();
        mAvatar.setContent(user);
        mCoverImage.setImageURI(Uri.parse(user.getCoverImageUrl()));
        mUsername.setText(user.getId());

        if (user.isPro()) {
            mProBadge.setVisibility(VISIBLE);
        }
    }

    @Override
    public void onPreferenceChange(final Preference<AppNewsStatus> preference) {
        final AppNewsStatus appNewsStatus = preference.get();
        final NavigationDrawerItemView appNewsView = getNavigationDrawerItemView(Entry.APP_NEWS);

        if (appNewsStatus != null && appNewsStatus.isImportantNewsAvailable()) {

        } else {

        }
    }

    @Override
    public boolean onTouchEvent(final MotionEvent event) {
        return true;
    }

    public void setOnNavigationDrawerItemViewClickListener(
            @Nullable final NavigationDrawerItemView.OnClickListener l) {
        for (final NavigationDrawerItemView view : mNavigationDrawerItemViews) {
            view.setOnClickListener(l);
        }
    }

    public void setSelectedNavigationDrawerItemViewEntry(@Nullable final Entry e) {
        for (final NavigationDrawerItemView view : mNavigationDrawerItemViews) {
            view.setSelected(e == view.getEntry());
        }
    }

}

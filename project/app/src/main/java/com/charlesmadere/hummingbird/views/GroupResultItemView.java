package com.charlesmadere.hummingbird.views;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.util.AttributeSet;
import android.view.View;
import android.widget.TextView;

import com.charlesmadere.hummingbird.R;
import com.charlesmadere.hummingbird.activities.GroupActivity;
import com.charlesmadere.hummingbird.adapters.AdapterView;
import com.charlesmadere.hummingbird.adapters.SearchResultsAdapter;
import com.charlesmadere.hummingbird.models.SearchBundle;
import com.facebook.drawee.view.SimpleDraweeView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class GroupResultItemView extends CardView implements AdapterView<Void>,
        SearchResultsAdapter.SearchResultHandler, View.OnClickListener {

    private SearchBundle.GroupResult mGroupResult;

    @BindView(R.id.sdvLogo)
    SimpleDraweeView mLogo;

    @BindView(R.id.tvDescription)
    TextView mDescription;

    @BindView(R.id.tvTitle)
    TextView mTitle;

    @BindView(R.id.divider)
    View mDivider;


    public GroupResultItemView(final Context context, final AttributeSet attrs) {
        super(context, attrs);
    }

    public GroupResultItemView(final Context context, final AttributeSet attrs,
            final int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public void onClick(final View v) {
        final Context context = getContext();
        context.startActivity(GroupActivity.getLaunchIntent(context, mGroupResult.getLink(),
                mGroupResult.getTitle()));
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        ButterKnife.bind(this);
        setOnClickListener(this);
    }

    @Override
    public void setContent(final SearchBundle.AbsResult result, final boolean showDivider) {
        setContent((SearchBundle.GroupResult) result, showDivider);
    }

    public void setContent(final SearchBundle.GroupResult content, final boolean showDivider) {
        mGroupResult = content;

        mLogo.setImageURI(content.getImage());
        mTitle.setText(content.getTitle());

        if (content.hasDescription()) {
            mDescription.setText(content.getDescription());
            mDescription.setVisibility(VISIBLE);
        } else {
            mDescription.setVisibility(GONE);
        }

        mDivider.setVisibility(showDivider ? VISIBLE : GONE);
    }

    @Override
    public void setContent(final Void content) {
        // intentionally empty
    }

}

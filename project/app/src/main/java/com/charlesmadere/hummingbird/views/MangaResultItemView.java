package com.charlesmadere.hummingbird.views;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.util.AttributeSet;
import android.view.View;
import android.widget.TextView;

import com.charlesmadere.hummingbird.R;
import com.charlesmadere.hummingbird.activities.MangaActivity;
import com.charlesmadere.hummingbird.adapters.AdapterView;
import com.charlesmadere.hummingbird.adapters.SearchResultsAdapter;
import com.charlesmadere.hummingbird.models.SearchBundle;
import com.facebook.drawee.view.SimpleDraweeView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MangaResultItemView extends CardView implements AdapterView<Void>,
        SearchResultsAdapter.Handler, View.OnClickListener {

    private SearchBundle.MangaResult mMangaResult;

    @BindView(R.id.sdvCover)
    SimpleDraweeView mCover;

    @BindView(R.id.tvSynopsis)
    TextView mSynopsis;

    @BindView(R.id.tvTitle)
    TextView mTitle;

    @BindView(R.id.divider)
    View mDivider;


    public MangaResultItemView(final Context context, final AttributeSet attrs) {
        super(context, attrs);
    }

    public MangaResultItemView(final Context context, final AttributeSet attrs,
            final int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public void onClick(final View v) {
        final Context context = getContext();
        context.startActivity(MangaActivity.getLaunchIntent(context, mMangaResult.getLink(),
                mMangaResult.getTitle()));
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        ButterKnife.bind(this);
        setOnClickListener(this);
    }

    @Override
    public void setContent(final SearchBundle.AbsResult result, final boolean showDivider) {
        setContent((SearchBundle.MangaResult) result, showDivider);
    }

    public void setContent(final SearchBundle.MangaResult result, final boolean showDivider) {
        mMangaResult = result;

        mCover.setImageURI(result.getImage());
        mTitle.setText(result.getTitle());

        if (result.hasDescription()) {
            mSynopsis.setText(result.getDescription());
            mSynopsis.setVisibility(VISIBLE);
        } else {
            mSynopsis.setVisibility(GONE);
        }

        mDivider.setVisibility(showDivider ? VISIBLE : GONE);
    }

    @Override
    public void setContent(final Void content) {
        // intentionally empty
    }

}

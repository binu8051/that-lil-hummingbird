package com.charlesmadere.hummingbird.views;

import android.content.Context;
import android.net.Uri;
import android.support.v7.widget.CardView;
import android.util.AttributeSet;
import android.view.View;
import android.widget.TextView;

import com.charlesmadere.hummingbird.R;
import com.charlesmadere.hummingbird.adapters.AdapterView;
import com.charlesmadere.hummingbird.models.SearchBundle;
import com.facebook.drawee.view.SimpleDraweeView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class GroupResultItemView extends CardView implements AdapterView<SearchBundle.GroupResult>,
        View.OnClickListener {

    @BindView(R.id.sdvLogo)
    SimpleDraweeView mLogo;

    @BindView(R.id.tvTitle)
    TextView mTitle;


    public GroupResultItemView(final Context context, final AttributeSet attrs) {
        super(context, attrs);
    }

    public GroupResultItemView(final Context context, final AttributeSet attrs,
            final int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public void onClick(final View v) {
        // TODO
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();

        if (isInEditMode()) {
            return;
        }

        ButterKnife.bind(this);
        setOnClickListener(this);
    }

    @Override
    public void setContent(final SearchBundle.GroupResult content) {
        mLogo.setImageURI(Uri.parse(content.getImage()));
        mTitle.setText(content.getTitle());
    }

}

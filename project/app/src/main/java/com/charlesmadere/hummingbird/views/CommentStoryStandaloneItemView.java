package com.charlesmadere.hummingbird.views;

import android.content.Context;
import android.net.Uri;
import android.support.v7.widget.CardView;
import android.util.AttributeSet;
import android.widget.TextView;

import com.charlesmadere.hummingbird.R;
import com.charlesmadere.hummingbird.activities.UserActivity;
import com.charlesmadere.hummingbird.adapters.AdapterView;
import com.charlesmadere.hummingbird.models.CommentStory;
import com.facebook.drawee.view.SimpleDraweeView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class CommentStoryStandaloneItemView extends CardView implements AdapterView<CommentStory> {

    private CommentStory mCommentStory;

    @BindView(R.id.commentTitleTextView)
    CommentTitleTextView mCommentTitleTextView;

    @BindView(R.id.likeTextView)
    LikeTextView mLikeTextView;

    @BindView(R.id.sdvAvatar)
    SimpleDraweeView mAvatar;

    @BindView(R.id.tvComment)
    TextView mComment;

    @BindView(R.id.tvTimeAgo)
    TextView mTimeAgo;


    public CommentStoryStandaloneItemView(final Context context, final AttributeSet attrs) {
        super(context, attrs);
    }

    public CommentStoryStandaloneItemView(final Context context, final AttributeSet attrs,
            final int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @OnClick(R.id.sdvAvatar)
    void onAvatarClick() {
        final Context context = getContext();
        context.startActivity(UserActivity.getLaunchIntent(context, mCommentStory.getUser()));
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();

        if (isInEditMode()) {
            return;
        }

        ButterKnife.bind(this);
    }

    @Override
    public void setContent(final CommentStory content) {
        mCommentStory = content;
        mAvatar.setImageURI(Uri.parse(mCommentStory.getUser().getAvatar()));
        mLikeTextView.setContent(mCommentStory);

        if (content.hasGroupId()) {
            mCommentTitleTextView.setText(mCommentStory.getUser(), mCommentStory.getGroup());
        } else {
            mCommentTitleTextView.setText(mCommentStory.getUser());
        }

        mTimeAgo.setText(mCommentStory.getCreatedAt().getRelativeTimeText(getContext()));
        mComment.setText(mCommentStory.getComment());
    }

}
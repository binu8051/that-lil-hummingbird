package com.charlesmadere.hummingbird.views;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.util.AttributeSet;
import android.view.View;
import android.widget.TextView;

import com.charlesmadere.hummingbird.R;
import com.charlesmadere.hummingbird.activities.AnimeActivity;
import com.charlesmadere.hummingbird.activities.MangaActivity;
import com.charlesmadere.hummingbird.adapters.AdapterView;
import com.charlesmadere.hummingbird.models.AbsSubstory;
import com.charlesmadere.hummingbird.models.Anime;
import com.charlesmadere.hummingbird.models.Manga;
import com.charlesmadere.hummingbird.models.MediaStory;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MediaStoryItemView extends CardView implements AdapterView<MediaStory>,
        View.OnClickListener {

    private MediaStory mMediaStory;

    @BindView(R.id.msivZero)
    AbsSubstoryItemView mMediaZero;

    @BindView(R.id.msivOne)
    AbsSubstoryItemView mMediaOne;

    @BindView(R.id.shareFeedButton)
    ShareFeedButton mShareFeedButton;

    @BindView(R.id.showMoreFeedButton)
    ShowMoreFeedButton mShowMoreFeedButton;

    @BindView(R.id.sdvImage)
    SimpleDraweeView mImage;

    @BindView(R.id.tvMediaType)
    TextView mMediaType;

    @BindView(R.id.tvGenres)
    TextView mGenres;

    @BindView(R.id.tvTitle)
    TextView mTitle;


    public MediaStoryItemView(final Context context, final AttributeSet attrs) {
        super(context, attrs);
    }

    public MediaStoryItemView(final Context context, final AttributeSet attrs,
            final int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public void onClick(final View v) {
        final MediaStory.AbsMedia media = mMediaStory.getMedia();
        final Context context = getContext();

        switch (media.getType()) {
            case ANIME:
                context.startActivity(AnimeActivity.getLaunchIntent(context,
                        ((MediaStory.AnimeMedia) media).getAnime()));
                break;

            case MANGA:
                context.startActivity(MangaActivity.getLaunchIntent(context,
                        ((MediaStory.MangaMedia) media).getManga()));
                break;

            default:
                throw new RuntimeException("encountered unknown " +
                        MediaStory.AbsMedia.Type.class.getName() + ": \"" + media.getType() + '"');
        }
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        ButterKnife.bind(this);
        setOnClickListener(this);
    }

    @Override
    public void setContent(final MediaStory content) {
        mMediaStory = content;
        final MediaStory.AbsMedia media = mMediaStory.getMedia();

        switch (media.getType()) {
            case ANIME:
                setContent((MediaStory.AnimeMedia) media);
                break;

            case MANGA:
                setContent((MediaStory.MangaMedia) media);

            default:
                throw new RuntimeException("encountered unknown " +
                        MediaStory.AbsMedia.Type.class.getName() + ": \"" + media.getType() + '"');
        }

        final ArrayList<AbsSubstory> substories = mMediaStory.getSubstories();
        mMediaZero.setContent(substories.get(0), mMediaStory.getUser());

        if (substories.size() >= 2) {
            mMediaOne.setContent(substories.get(1), mMediaStory.getUser());
            mMediaOne.setVisibility(VISIBLE);
        } else {
            mMediaOne.setVisibility(GONE);
        }

        mShareFeedButton.setContent(mMediaStory);

        if (mMediaStory.getSubstoryCount() >= 3) {
            mShowMoreFeedButton.setContent(mMediaStory);
            mShowMoreFeedButton.setVisibility(VISIBLE);
        } else {
            mShowMoreFeedButton.setContent(null);
            mShowMoreFeedButton.setVisibility(INVISIBLE);
        }
    }

    private void setContent(final MediaStory.AnimeMedia media) {
        final Anime anime = media.getAnime();
        mImage.setImageURI(anime.getPosterImage());
        mTitle.setText(anime.getTitle());

        if (anime.hasType()) {
            mMediaType.setText(anime.getType().getTextResId());
            mMediaType.setVisibility(VISIBLE);
        } else {
            mMediaType.setVisibility(GONE);
        }

        if (anime.hasGenres()) {
            mGenres.setText(anime.getGenresString(getResources()));
            mGenres.setVisibility(VISIBLE);
        } else {
            mGenres.setVisibility(GONE);
        }
    }

    private void setContent(final MediaStory.MangaMedia media) {
        final Manga manga = media.getManga();
        mImage.setImageURI(manga.getPosterImage());
        mTitle.setText(manga.getTitle());

        if (manga.hasType()) {
            mMediaType.setText(manga.getType().getTextResId());
            mMediaType.setVisibility(VISIBLE);
        } else {
            mMediaType.setVisibility(GONE);
        }

        if (manga.hasGenres()) {
            mGenres.setText(manga.getGenresString(getResources()));
            mGenres.setVisibility(VISIBLE);
        } else {
            mGenres.setVisibility(GONE);
        }
    }

}

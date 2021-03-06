package com.charlesmadere.hummingbird.views;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.util.AttributeSet;
import android.widget.TextView;

import com.charlesmadere.hummingbird.R;
import com.charlesmadere.hummingbird.adapters.AdapterView;
import com.charlesmadere.hummingbird.misc.MiscUtils;
import com.charlesmadere.hummingbird.models.UserDigest;

import java.text.NumberFormat;
import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AnimeBreakdownView extends CardView implements AdapterView<UserDigest> {

    private NumberFormat mNumberFormat;

    @BindView(R.id.abgvGenre0)
    AnimeBreakdownGenreView mGenre0;

    @BindView(R.id.abgvGenre1)
    AnimeBreakdownGenreView mGenre1;

    @BindView(R.id.abgvGenre2)
    AnimeBreakdownGenreView mGenre2;

    @BindView(R.id.abgvGenre3)
    AnimeBreakdownGenreView mGenre3;

    @BindView(R.id.abgvGenre4)
    AnimeBreakdownGenreView mGenre4;

    @BindView(R.id.abgvGenre5)
    AnimeBreakdownGenreView mGenre5;

    @BindView(R.id.animeBreakdownPieView)
    AnimeBreakdownPieView mAnimeBreakdownPieView;

    @BindView(R.id.tvGenreCount)
    TextView mGenreCount;

    @BindView(R.id.tvLifeSpentOnAnime)
    TextView mLifeSpentOnAnime;

    @BindView(R.id.tvPrimaryGenre)
    TextView mPrimaryGenre;


    public AnimeBreakdownView(final Context context, final AttributeSet attrs) {
        super(context, attrs);
    }

    public AnimeBreakdownView(final Context context, final AttributeSet attrs,
            final int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();

        ButterKnife.bind(this);
        mNumberFormat = NumberFormat.getInstance();

        if (isInEditMode()) {
            mAnimeBreakdownPieView.setValues(30, 21);
        }
    }

    @Override
    public void setContent(final UserDigest content) {
        final UserDigest.Info info = content.getInfo();
        final UserDigest.Info.Genre topGenre = info.getTopGenre();
        mAnimeBreakdownPieView.setValues(info.getAnimeWatched(), topGenre.getNum());

        mPrimaryGenre.setText(info.getTopGenre().getData().getName());
        mGenreCount.setText(getResources().getQuantityString(R.plurals.out_of_x_titles,
                info.getAnimeWatched(), mNumberFormat.format(info.getAnimeWatched())));

        final ArrayList<UserDigest.Info.Genre> topGenres = info.getTopGenres();

        if (topGenres == null || topGenres.isEmpty()) {
            throw new IllegalArgumentException("only use this method when top genres are available");
        }

        setGenreView(mGenre0, topGenres, 1);
        setGenreView(mGenre1, topGenres, 2);
        setGenreView(mGenre2, topGenres, 3);
        setGenreView(mGenre3, topGenres, 4);
        setGenreView(mGenre4, topGenres, 5);
        setGenreView(mGenre5, topGenres, 6);

        mLifeSpentOnAnime.setText(MiscUtils.getElapsedTime(getResources(),
                info.getLifeSpentOnAnimeSeconds()));
    }

    private void setGenreView(final AnimeBreakdownGenreView view,
            final ArrayList<UserDigest.Info.Genre> topGenres, final int index) {
        if (topGenres.size() >= index) {
            view.setText(topGenres.get(index - 1));
            view.setVisibility(VISIBLE);
        } else {
            view.setVisibility(GONE);
        }
    }

}

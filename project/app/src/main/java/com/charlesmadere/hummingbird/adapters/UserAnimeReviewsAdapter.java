package com.charlesmadere.hummingbird.adapters;

import android.content.Context;

import com.charlesmadere.hummingbird.R;
import com.charlesmadere.hummingbird.models.AnimeReview;
import com.charlesmadere.hummingbird.models.Feed;

public class UserAnimeReviewsAdapter extends BasePaginationAdapter<AnimeReview> {

    public UserAnimeReviewsAdapter(final Context context) {
        super(context);
    }

    @Override
    public int getItemViewTypeForPosition(final int position) {
        return R.layout.item_user_anime_review;
    }

    public void set(final Feed feed) {
        super.set(feed.getAnimeReviews());
    }

}

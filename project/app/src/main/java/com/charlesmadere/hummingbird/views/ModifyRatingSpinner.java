package com.charlesmadere.hummingbird.views;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatSpinner;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;

import com.charlesmadere.hummingbird.models.AnimeLibraryUpdate;
import com.charlesmadere.hummingbird.models.MangaLibraryUpdate;
import com.charlesmadere.hummingbird.models.Rating;

public class ModifyRatingSpinner extends AppCompatSpinner implements
        AdapterView.OnItemSelectedListener {

    private static final Rating[] VALUES = { Rating.UNRATED, Rating.ZERO_POINT_FIVE,
            Rating.ONE, Rating.ONE_POINT_FIVE, Rating.TWO, Rating.TWO_POINT_FIVE, Rating.THREE,
            Rating.THREE_POINT_FIVE, Rating.FOUR, Rating.FOUR_POINT_FIVE, Rating.FIVE };

    private OnItemSelectedListener mListener;


    public ModifyRatingSpinner(final Context context, final AttributeSet attrs) {
        super(context, attrs);
    }

    public ModifyRatingSpinner(final Context context, final AttributeSet attrs,
            final int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public Rating getItemAtPosition(final int position) {
        return (Rating) super.getItemAtPosition(position);
    }

    @Override
    public Rating getSelectedItem() {
        return (Rating) super.getSelectedItem();
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        setAdapter(new ModifyRatingAdapter());
        setOnItemSelectedListener(this);
    }

    @Override
    public void onItemSelected(final AdapterView<?> parent, final View view, final int position,
            final long id) {
        if (mListener != null) {
            mListener.onItemSelected(this);
        }
    }

    @Override
    public void onNothingSelected(final AdapterView<?> parent) {
        // intentionally empty
    }

    public void setContent(final AnimeLibraryUpdate libraryUpdate) {
        setContent(libraryUpdate.getRating());
    }

    public void setContent(final MangaLibraryUpdate libraryUpdate) {
        setContent(libraryUpdate.getRating());
    }

    public void setContent(@Nullable Rating rating) {
        if (rating == null) {
            rating = Rating.UNRATED;
        }

        for (int position = 0; position < getCount(); ++position) {
            final Rating item = getItemAtPosition(position);

            if (rating == item) {
                setSelection(position);
                return;
            }
        }

        throw new RuntimeException("The given " + Rating.class.getSimpleName() + " (" + rating +
                ") doesn't exist in the list");
    }

    public void setOnItemSelectedListener(@Nullable final OnItemSelectedListener listener) {
        mListener = listener;
    }


    public interface OnItemSelectedListener {
        void onItemSelected(final ModifyRatingSpinner v);
    }


    private static class ModifyRatingAdapter extends BaseAdapter {
        @Override
        public int getCount() {
            return VALUES.length;
        }

        @Override
        public Rating getItem(final int position) {
            return VALUES[position];
        }

        @Override
        public long getItemId(final int position) {
            return position;
        }

        @Override
        public View getView(final int position, View convertView, final ViewGroup parent) {
            if (convertView == null) {
                convertView = RatingView.inflate(parent);
            }

            final RatingView.ViewHolder viewHolder = ((RatingView) convertView).getViewHolder();
            viewHolder.getView().setContent(getItem(position));

            return convertView;
        }
    }

}

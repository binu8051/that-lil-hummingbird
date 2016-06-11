package com.charlesmadere.hummingbird.adapters;

import android.content.Context;

import com.charlesmadere.hummingbird.R;

public abstract class BasePaginationAdapter extends BaseMultiAdapter {

    protected boolean mIsPaginating;


    public BasePaginationAdapter(final Context context) {
        super(context);
    }

    @Override
    public int getItemCount() {
        int itemCount = super.getItemCount();

        if (mIsPaginating) {
            ++itemCount;
        }

        return itemCount;
    }

    @Override
    public final int getItemViewType(final int position) {
        if (mIsPaginating) {
            return R.layout.item_progress;
        } else {
            return getItemViewTypeForPosition(position);
        }
    }

    protected abstract int getItemViewTypeForPosition(final int position);

    public boolean isPaginating() {
        return mIsPaginating;
    }

    @Override
    @SuppressWarnings("unchecked")
    public final void onBindViewHolder(final AdapterView.ViewHolder holder, final int position) {
        if (mIsPaginating && position == getItems().size()) {
            holder.getAdapterView().setContent(null);
        } else {
            super.onBindViewHolder(holder, position);
        }
    }

    public void setPaginating(final boolean paginating) {
        if (mIsPaginating == paginating) {
            return;
        }

        mIsPaginating = paginating;

        if (mIsPaginating) {
            notifyItemInserted(getItemCount());
        } else {
            notifyItemRemoved(getItemCount() + 1);
        }
    }

}

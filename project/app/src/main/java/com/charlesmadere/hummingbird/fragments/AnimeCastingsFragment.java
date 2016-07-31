package com.charlesmadere.hummingbird.fragments;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.charlesmadere.hummingbird.R;
import com.charlesmadere.hummingbird.adapters.AnimeCastingsAdapter;
import com.charlesmadere.hummingbird.misc.AnimeDigestProvider;
import com.charlesmadere.hummingbird.misc.MiscUtils;
import com.charlesmadere.hummingbird.models.AnimeDigest;
import com.charlesmadere.hummingbird.views.SpaceItemDecoration;

import butterknife.BindView;

public class AnimeCastingsFragment extends BaseFragment {

    private static final String TAG = "AnimeCastingsFragment";

    private AnimeDigestProvider mProvider;

    @BindView(R.id.recyclerView)
    RecyclerView mRecyclerView;

    @BindView(R.id.tvEmpty)
    TextView mEmpty;


    public static AnimeCastingsFragment create() {
        return new AnimeCastingsFragment();
    }

    @Override
    public String getFragmentName() {
        return TAG;
    }

    @Override
    public void onAttach(final Context context) {
        super.onAttach(context);

        final Fragment fragment = getParentFragment();
        if (fragment instanceof AnimeDigestProvider) {
            mProvider = (AnimeDigestProvider) fragment;
        } else {
            final Activity activity = MiscUtils.getActivity(context);

            if (activity instanceof AnimeDigestProvider) {
                mProvider = (AnimeDigestProvider) activity;
            }
        }

        if (mProvider == null) {
            throw new IllegalStateException(TAG + " must have a Listener");
        }
    }

    @Override
    public View onCreateView(final LayoutInflater inflater, final ViewGroup container,
            final Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        return inflater.inflate(R.layout.fragment_anime_castings, container, false);
    }

    @Override
    public void onViewCreated(final View view, final Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mRecyclerView.setHasFixedSize(true);
        SpaceItemDecoration.apply(mRecyclerView, false, R.dimen.root_padding);

        final AnimeDigest animeDigest = mProvider.getAnimeDigest();

        if (animeDigest.hasCastings()) {
            final AnimeCastingsAdapter adapter = new AnimeCastingsAdapter(getContext());
            adapter.set(animeDigest.getCastings());
            mRecyclerView.setAdapter(adapter);
            mRecyclerView.setVisibility(View.VISIBLE);
        } else {
            mEmpty.setVisibility(View.VISIBLE);
        }
    }

}

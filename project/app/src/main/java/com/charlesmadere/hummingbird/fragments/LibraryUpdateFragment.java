package com.charlesmadere.hummingbird.fragments;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import com.charlesmadere.hummingbird.R;
import com.charlesmadere.hummingbird.misc.MiscUtils;
import com.charlesmadere.hummingbird.models.LibraryEntry;
import com.charlesmadere.hummingbird.models.LibraryUpdate;
import com.charlesmadere.hummingbird.views.ModifyPublicPrivateView;
import com.charlesmadere.hummingbird.views.ModifyRatingSpinner;
import com.charlesmadere.hummingbird.views.ModifyWatchCountView;
import com.charlesmadere.hummingbird.views.ModifyWatchingStatusSpinner;

import butterknife.BindView;
import butterknife.OnClick;

public class LibraryUpdateFragment extends BaseBottomSheetDialogFragment implements
        ModifyPublicPrivateView.OnSelectionChangedListener,
        ModifyRatingSpinner.OnItemSelectedListener,
        ModifyWatchCountView.OnWatchCountChangedListener,
        ModifyWatchingStatusSpinner.OnItemSelectedListener {

    public static final String TAG = "LibraryUpdateFragment";
    private static final String KEY_LIBRARY_ENTRY = "LibraryEntry";
    private static final String KEY_LIBRARY_UPDATE = "LibraryUpdate";

    private LibraryEntry mLibraryEntry;
    private LibraryUpdate mLibraryUpdate;
    private Listener mListener;

    @BindView(R.id.cbRewatching)
    CheckBox mRewatching;

    @BindView(R.id.etPersonalNotes)
    EditText mPersonalNotes;

    @BindView(R.id.ibSave)
    ImageButton mSave;

    @BindView(R.id.modifyPublicPrivateView)
    ModifyPublicPrivateView mModifyPublicPrivateView;

    @BindView(R.id.modifyRatingSpinner)
    ModifyRatingSpinner mModifyRatingSpinner;

    @BindView(R.id.modifyWatchCountView)
    ModifyWatchCountView mModifyWatchCountView;

    @BindView(R.id.modifyWatchingStatusSpinner)
    ModifyWatchingStatusSpinner mModifyWatchingStatusSpinner;

    @BindView(R.id.tvTitle)
    TextView mTitle;


    public static LibraryUpdateFragment create(final LibraryEntry libraryEntry) {
        final Bundle args = new Bundle(1);
        args.putParcelable(KEY_LIBRARY_ENTRY, libraryEntry);

        final LibraryUpdateFragment fragment = new LibraryUpdateFragment();
        fragment.setArguments(args);

        return fragment;
    }

    @Override
    public String getFragmentName() {
        return TAG;
    }

    @Override
    public void onAttach(final Context context) {
        super.onAttach(context);
        mListener = (Listener) MiscUtils.getActivity(context);
    }

    @OnClick(R.id.ibClose)
    void onCloseClick() {
        dismiss();
    }

    @Override
    public void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        final Bundle args = getArguments();
        mLibraryEntry = args.getParcelable(KEY_LIBRARY_ENTRY);

        if (savedInstanceState != null && !savedInstanceState.isEmpty()) {
            mLibraryUpdate = savedInstanceState.getParcelable(KEY_LIBRARY_UPDATE);
        }

        if (mLibraryUpdate == null) {
            mLibraryUpdate = new LibraryUpdate();
        }
    }

    @Override
    public View onCreateView(final LayoutInflater inflater, final ViewGroup container,
            final Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        return inflater.inflate(R.layout.fragment_library_update, container, false);
    }

    @Override
    public void onItemSelected(final ModifyRatingSpinner v) {
        // TODO
    }

    @Override
    public void onItemSelected(final ModifyWatchingStatusSpinner v) {
        // TODO
    }

    @OnClick(R.id.llRewatching)
    void onRewatchingClick() {
        // TODO
    }

    @OnClick(R.id.ibSave)
    void onSaveClick() {
        mListener.onLibraryUpdateSave();
    }

    @Override
    public void onSaveInstanceState(final Bundle outState) {
        super.onSaveInstanceState(outState);

        if (mLibraryUpdate != null) {
            outState.putParcelable(KEY_LIBRARY_UPDATE, mLibraryUpdate);
        }
    }

    @Override
    public void onSelectionChanged(final ModifyPublicPrivateView v) {
        // TODO
    }

    @Override
    public void onViewCreated(final View view, final Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mTitle.setText(mLibraryEntry.getAnime().getTitle());
        mSave.setEnabled(false);

        mModifyWatchCountView.setContent(mLibraryEntry);
        mModifyWatchingStatusSpinner.setContent(mLibraryEntry);
        mModifyPublicPrivateView.setContent(mLibraryEntry);
        mModifyRatingSpinner.setContent(mLibraryEntry);

        mRewatching.setChecked(mLibraryEntry.isRewatching());
        mPersonalNotes.setText(mLibraryEntry.getNotes());

        mModifyWatchingStatusSpinner.setOnItemSelectedListener(this);
        mModifyPublicPrivateView.setOnSelectionChangedListener(this);
        mModifyRatingSpinner.setOnItemSelectedListener(this);
        mModifyWatchCountView.setOnWatchCountChangedListener(this);
    }

    @Override
    public void onWatchCountChanged(final ModifyWatchCountView v) {
        // TODO
    }

    private void update() {
        // TODO
    }


    public interface Listener {
        void onLibraryUpdateSave();
    }

}

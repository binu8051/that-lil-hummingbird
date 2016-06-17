package com.charlesmadere.hummingbird.views;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.TypedArray;
import android.os.Build;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Checkable;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.charlesmadere.hummingbird.R;
import com.charlesmadere.hummingbird.preferences.BooleanPreference;
import com.charlesmadere.hummingbird.preferences.Preference;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CheckablePreferenceView extends RelativeLayout implements
        Preference.OnPreferenceChangeListener<Boolean>, View.OnClickListener {

    private static final int CHECKABLE_TYPE_CHECKBOX = 0;
    private static final int CHECKABLE_TYPE_SWITCH_COMPAT = 1;

    private BooleanPreference mPreference;
    private CharSequence mDisabledDescriptionText;
    private CharSequence mEnabledDescriptionText;
    private CharSequence mTitleText;
    private int mCheckableType;

    @BindView(R.id.checkable)
    Checkable mCheckable;

    @BindView(R.id.tvDescription)
    TextView mDescription;

    @BindView(R.id.tvTitle)
    TextView mTitle;


    public CheckablePreferenceView(final Context context, final AttributeSet attrs) {
        super(context, attrs);
        parseAttributes(attrs);
    }

    public CheckablePreferenceView(final Context context, final AttributeSet attrs,
            final int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        parseAttributes(attrs);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public CheckablePreferenceView(final Context context, final AttributeSet attrs,
            final int defStyleAttr, final int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        parseAttributes(attrs);
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        update();
    }

    @Override
    public void onClick(final View view) {
        mPreference.toggle();
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();

        if (mPreference != null) {
            mPreference.removeListener(this);
        }
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        ButterKnife.bind(this);
        mTitle.setText(mTitleText);

        final LayoutInflater inflater = LayoutInflater.from(getContext());

        if (mCheckableType == CHECKABLE_TYPE_CHECKBOX) {
            inflater.inflate(R.layout.preference_checkbox, this);
        } else if (mCheckableType == CHECKABLE_TYPE_SWITCH_COMPAT) {
            inflater.inflate(R.layout.preference_switch, this);
        } else {
            throw new RuntimeException("mCheckableType is an illegal value: " + mCheckableType);
        }
    }

    @Override
    public void onPreferenceChange(final Preference<Boolean> preference) {
        update();
    }

    private void parseAttributes(final AttributeSet attrs) {
        final TypedArray ta = getContext().obtainStyledAttributes(attrs,
                R.styleable.CheckablePreferenceView);
        mDisabledDescriptionText = ta.getText(R.styleable.CheckablePreferenceView_disabledDescriptionText);
        mEnabledDescriptionText = ta.getText(R.styleable.CheckablePreferenceView_enabledDescriptionText);
        mTitleText = ta.getText(R.styleable.CheckablePreferenceView_titleText);
        mCheckableType = ta.getInt(R.styleable.CheckablePreferenceView_checkable_type, -1);
        ta.recycle();
    }

    public void setBooleanPreference(final BooleanPreference preference) {
        mPreference = preference;
        mPreference.addListener(this);
        update();
        setOnClickListener(this);
    }

    @Override
    public void setEnabled(final boolean enabled) {
        super.setEnabled(enabled);
        ((View) mCheckable).setEnabled(enabled);
        mDescription.setEnabled(enabled);
        mTitle.setEnabled(enabled);
    }

    private void update() {
        setEnabled(mPreference != null);

        if (isEnabled()) {
            mCheckable.setChecked(Boolean.TRUE.equals(mPreference.get()));
            mDescription.setText(mEnabledDescriptionText);
        } else {
            mDescription.setText(mDisabledDescriptionText);
        }
    }

}
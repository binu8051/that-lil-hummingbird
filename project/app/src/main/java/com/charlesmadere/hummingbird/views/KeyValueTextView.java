package com.charlesmadere.hummingbird.views;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.TextUtils;
import android.text.style.ForegroundColorSpan;
import android.util.AttributeSet;

import com.charlesmadere.hummingbird.R;
import com.charlesmadere.hummingbird.misc.MiscUtils;

public class KeyValueTextView extends TypefaceTextView {

    private ForegroundColorSpan mKeySpan;
    private ForegroundColorSpan mValueSpan;


    public KeyValueTextView(final Context context, final AttributeSet attrs) {
        super(context, attrs);
        parseAttributes(attrs);
    }

    public KeyValueTextView(final Context context, final AttributeSet attrs,
            final int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        parseAttributes(attrs);
    }

    private void parseAttributes(final AttributeSet attrs) {
        final Context context = getContext();
        final TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.KeyValueTextView);
        final int keyTextColor = ta.getColor(R.styleable.KeyValueTextView_keyTextColor,
                MiscUtils.getAttrColor(context, android.R.attr.textColorPrimary));
        final int valueTextColor = ta.getColor(R.styleable.KeyValueTextView_valueTextColor,
                MiscUtils.getAttrColor(context, android.R.attr.textColorSecondary));

        mKeySpan = new ForegroundColorSpan(keyTextColor);
        mValueSpan = new ForegroundColorSpan(valueTextColor);

        if (ta.hasValue(R.styleable.KeyValueTextView_keyText) &&
                ta.hasValue(R.styleable.KeyValueTextView_valueText)) {
            final CharSequence key = ta.getText(R.styleable.KeyValueTextView_keyText);
            final CharSequence value = ta.getText(R.styleable.KeyValueTextView_valueText);
            setText(key, value);
        }

        ta.recycle();
    }

    public void setText(@StringRes final int keyTextResId, @Nullable final CharSequence value) {
        setText(getResources().getText(keyTextResId), value);
    }

    public void setText(@Nullable final CharSequence key, @Nullable final CharSequence value) {
        if (TextUtils.isEmpty(key) && TextUtils.isEmpty(value)) {
            setText("");
            return;
        }

        final SpannableStringBuilder spannable = new SpannableStringBuilder();

        if (!TextUtils.isEmpty(key)) {
            spannable.append(key);
            spannable.setSpan(mKeySpan, 0, spannable.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        }

        if (!TextUtils.isEmpty(value)) {
            if (!TextUtils.isEmpty(spannable)) {
                spannable.append(' ');
            }

            final int length = spannable.length();
            spannable.append(value);
            spannable.setSpan(mValueSpan, length, spannable.length(),
                    Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        }

        setText(spannable);
    }

}

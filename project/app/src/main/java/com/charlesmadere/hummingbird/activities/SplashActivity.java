package com.charlesmadere.hummingbird.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.charlesmadere.hummingbird.R;

public class SplashActivity extends BaseActivity {

    private static final String CNAME = SplashActivity.class.getCanonicalName();
    private static final String TAG = "SplashActivity";
    private static final String EXTRA_FINISH = CNAME + ".Finish";


    public static Intent getLaunchIntent(final Context context) {
        return new Intent(context, SplashActivity.class)
                .putExtra(EXTRA_FINISH, true);
    }

    @Override
    public String getActivityName() {
        return TAG;
    }

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
    }

}

package com.abangfadli.shotwatchapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.abangfadli.shotwatch.ShotWatch;


public class MainActivity extends AppCompatActivity {

    private ShotWatch mShotWatch;

    private TextView mText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mText = (TextView) findViewById(R.id.text);

        mShotWatch = new ShotWatch(getContentResolver(), new ShotWatch.Listener() {
            @Override
            public void onScreenShotTaken() {
                mText.setText("Screenshot detected");
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        mShotWatch.register();
    }

    @Override
    protected void onPause() {
        super.onPause();
        mShotWatch.unregister();
    }
}

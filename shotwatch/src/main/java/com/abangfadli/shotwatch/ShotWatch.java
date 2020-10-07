package com.abangfadli.shotwatch;

import android.content.ContentResolver;
import android.database.ContentObserver;
import android.os.Handler;
import android.os.HandlerThread;
import android.provider.MediaStore;

/**
 * Created by ahmadfadli on 1/31/17.
 */

public class ShotWatch {

    private static class ShotwatchThreadSupport {
        // Only have one thread like this
        private static final HandlerThread shotwatchHandlerThread = new HandlerThread("ShotWatch");
        static {
            shotwatchHandlerThread.start();
        }
    }

    private final Handler mHandler;
    private final ContentResolver mContentResolver;
    private final ContentObserver mContentObserver;

    public ShotWatch(ContentResolver contentResolver, Listener listener) {

        mHandler = new Handler(ShotwatchThreadSupport.shotwatchHandlerThread.getLooper());
        mContentResolver = contentResolver;
        mContentObserver = new ScreenShotObserver(mHandler, listener);
    }

    public void register() {
        mContentResolver.registerContentObserver(
                MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                true,
                mContentObserver
        );
    }

    public void unregister() {
        mContentResolver.unregisterContentObserver(mContentObserver);
    }


    public interface Listener {
        void onScreenShotTaken();
    }
}

/*
 * Copyright 2016 Supasin Tatiyanupanwong
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package me.tatiyanupanwong.supasin.samples.android.lifecycling;

import android.util.Log;

import me.tatiyanupanwong.supasin.libraries.android.lifecycling.LifecycleObserver;
import me.tatiyanupanwong.supasin.libraries.android.lifecycling.LifecycleOwner;
import me.tatiyanupanwong.supasin.libraries.android.lifecycling.Lifecycling;
import me.tatiyanupanwong.supasin.libraries.android.lifecyclingx.LifecycleApplication;

/**
 * A sample to demonstrate the usage and/or behaviour of Lifecycling and LifecyclingX libraries.
 *
 * @author Supasin Tatiyanupanwong (supast49@aware.postbox.in.th)
 */
public class App extends LifecycleApplication {

    private static final String TAG = "App";


    @Override
    public void onCreate() {
        Lifecycling.enableDebugLogging(true);

        getLifecycle().addObserver(ApplicationLifecycleObserver.INSTANCE);

        super.onCreate();
    }

    @Override
    public void onTerminate() {
        getLifecycle().removeObserver(ApplicationLifecycleObserver.INSTANCE);

        super.onTerminate();
    }


    private static final class ApplicationLifecycleObserver extends LifecycleObserver {

        static final LifecycleObserver INSTANCE = new ApplicationLifecycleObserver();

        private ApplicationLifecycleObserver() {}

        @Override
        protected void onCreate(LifecycleOwner source) {
            Log.d(TAG, "Application is created!");
        }

        @Override
        protected void onStart(LifecycleOwner source) {
            Log.d(TAG, "Application is coming to the foreground!");
        }

        @Override
        protected void onStop(LifecycleOwner source) {
            Log.d(TAG, "Application is going to the background!");
        }
    }

}

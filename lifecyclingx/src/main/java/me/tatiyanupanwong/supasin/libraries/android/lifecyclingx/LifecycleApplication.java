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

package me.tatiyanupanwong.supasin.libraries.android.lifecyclingx;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;
import android.support.annotation.CallSuper;

import me.tatiyanupanwong.supasin.libraries.android.lifecycling.Lifecycle;
import me.tatiyanupanwong.supasin.libraries.android.lifecycling.LifecycleOwner;
import me.tatiyanupanwong.supasin.libraries.android.lifecycling.LifecycleRegistry;

import static me.tatiyanupanwong.supasin.libraries.android.lifecycling.Lifecycle.Event.ON_CREATE;
import static me.tatiyanupanwong.supasin.libraries.android.lifecycling.Lifecycle.Event.ON_DESTROY;
import static me.tatiyanupanwong.supasin.libraries.android.lifecycling.Lifecycle.Event.ON_START;
import static me.tatiyanupanwong.supasin.libraries.android.lifecycling.Lifecycle.Event.ON_STOP;

/**
 * {@link Lifecycle}-supported Application. Note that the events will be dispatched as follows:
 * <li>
 * {@link Lifecycle.Event#ON_CREATE} will be dispatched once during the creation of your app.
 * <li>
 * {@link Lifecycle.Event#ON_START} will be dispatched when your app is coming to the foreground.
 * <li>
 * {@link Lifecycle.Event#ON_STOP} will be dispatched when your app is going to the background.
 * <li>
 * {@link Lifecycle.Event#ON_DESTROY} will never be dispatched on production Android device.
 * <li>
 * Other lifecycle events that are not mentioned will never be dispatched.
 *
 * @author Supasin Tatiyanupanwong (supast49@aware.postbox.in.th)
 */
@SuppressWarnings("unused") // Public API
public abstract class LifecycleApplication extends Application implements LifecycleOwner {

    private final LifecycleRegistry mLifecycleRegistry = new LifecycleRegistry(this);


    @CallSuper
    @Override
    public void onCreate() {
        super.onCreate();
        mLifecycleRegistry.handleEvent(ON_CREATE);

        registerActivityLifecycleCallbacks(new ActivityLifecycleCallbacks() {
            private int mActivityCount = 0;

            @Override
            public void onActivityCreated(Activity activity, Bundle savedInstanceState) {}

            @Override
            public void onActivityStarted(Activity activity) {
                mActivityCount++;

                if (mActivityCount == 1) {
                    mLifecycleRegistry.handleEvent(ON_START);
                }
            }

            @Override
            public void onActivityResumed(Activity activity) {}

            @Override
            public void onActivityPaused(Activity activity) {}

            @Override
            public void onActivityStopped(Activity activity) {
                mActivityCount--;

                if (mActivityCount == 0) {
                    mLifecycleRegistry.handleEvent(ON_STOP);
                }
            }

            @Override
            public void onActivitySaveInstanceState(Activity activity, Bundle outState) {}

            @Override
            public void onActivityDestroyed(Activity activity) {}
        });

    }

    @CallSuper
    @Override
    public void onTerminate() {
        mLifecycleRegistry.handleEvent(ON_DESTROY);
        super.onTerminate();
    }


    @Override
    public final Lifecycle getLifecycle() {
        return mLifecycleRegistry;
    }

}

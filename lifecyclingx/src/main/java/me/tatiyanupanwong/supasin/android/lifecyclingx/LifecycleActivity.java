/*
 * Copyright (C) 2016 Supasin Tatiyanupanwong
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package me.tatiyanupanwong.supasin.android.lifecyclingx;

import android.os.Bundle;
import android.support.annotation.CallSuper;
import android.support.v7.app.AppCompatActivity;

import me.tatiyanupanwong.supasin.android.lifecycling.Lifecycle;
import me.tatiyanupanwong.supasin.android.lifecycling.LifecycleOwner;
import me.tatiyanupanwong.supasin.android.lifecycling.LifecycleRegistry;

import static me.tatiyanupanwong.supasin.android.lifecycling.Lifecycle.Event.ON_CREATE;
import static me.tatiyanupanwong.supasin.android.lifecycling.Lifecycle.Event.ON_DESTROY;
import static me.tatiyanupanwong.supasin.android.lifecycling.Lifecycle.Event.ON_PAUSE;
import static me.tatiyanupanwong.supasin.android.lifecycling.Lifecycle.Event.ON_RESUME;
import static me.tatiyanupanwong.supasin.android.lifecycling.Lifecycle.Event.ON_START;
import static me.tatiyanupanwong.supasin.android.lifecycling.Lifecycle.Event.ON_STOP;

/**
 * {@link Lifecycle}-supported AppCompatActivity. The events will be dispatched as per callback.
 *
 * @author Supasin Tatiyanupanwong (supast49@aware.postbox.in.th)
 */
@SuppressWarnings("unused") // Public API
public abstract class LifecycleActivity extends AppCompatActivity
        implements LifecycleOwner {

    private final LifecycleRegistry mLifecycleRegistry = new LifecycleRegistry(this);


    @CallSuper
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mLifecycleRegistry.handleEvent(ON_CREATE);
    }

    @CallSuper
    @Override
    protected void onStart() {
        super.onStart();
        mLifecycleRegistry.handleEvent(ON_START);
    }

    @CallSuper
    @Override
    protected void onResume() {
        super.onResume();
        mLifecycleRegistry.handleEvent(ON_RESUME);
    }

    @CallSuper
    @Override
    protected void onPause() {
        mLifecycleRegistry.handleEvent(ON_PAUSE);
        super.onPause();
    }

    @CallSuper
    @Override
    protected void onStop() {
        mLifecycleRegistry.handleEvent(ON_STOP);
        super.onStop();
    }

    @CallSuper
    @Override
    protected void onDestroy() {
        mLifecycleRegistry.handleEvent(ON_DESTROY);
        super.onDestroy();
    }


    @Override
    public final Lifecycle getLifecycle() {
        return mLifecycleRegistry;
    }

}

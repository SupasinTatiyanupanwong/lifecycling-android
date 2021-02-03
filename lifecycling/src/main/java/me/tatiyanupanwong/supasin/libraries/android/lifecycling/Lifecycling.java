/*
 * Copyright 2015-2016 Supasin Tatiyanupanwong
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

package me.tatiyanupanwong.supasin.libraries.android.lifecycling;

import android.util.Log;

/**
 * @author Supasin Tatiyanupanwong (supast49@aware.postbox.in.th)
 */
public final class Lifecycling {

    private static final String TAG = "Lifecycling";

    private static boolean sDebug = false;

    private Lifecycling() {}

    /**
     * Control whether the {@link Lifecycling}'s debugging logs are turned on.
     *
     * If enabled, you will see output in logcat as the lifecycle events being dispatched.
     */
    @SuppressWarnings("unused") // Public API
    public static void enableDebugLogging(boolean enabled) {
        sDebug = enabled;
    }

    static void logEvent(LifecycleOwner owner, Lifecycle.Event event) {
        if (sDebug) {
            String sourceStr = owner.getClass().getSimpleName()
                    + "{" + Integer.toHexString(System.identityHashCode(owner)) + "}";
            Log.d(TAG, "Dispatching event " + event.name() + " of " + sourceStr);
        }
    }

}

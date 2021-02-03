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

import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.Map;

/**
 * Extends {@link Lifecycle}'s support by forwarding its lifecycle event callbacks to this class
 * via {@link #handleEvent(Event)}.
 *
 * @author Supasin Tatiyanupanwong (supast49@aware.postbox.in.th)
 */
public final class LifecycleRegistry extends Lifecycle {

    private final Map<LifecycleObserver, EventDispatcher> mLifecycleObservers = new HashMap<>();
    private final WeakReference<LifecycleOwner> mLifecycleOwner;


    public LifecycleRegistry(LifecycleOwner owner) {
        mLifecycleOwner = new WeakReference<>(owner);
    }


    public void handleEvent(Event event) {
        final LifecycleOwner owner = mLifecycleOwner.get();

        if (owner != null) {
            Lifecycling.logEvent(owner, event);

            for (Map.Entry<?, EventDispatcher> it : mLifecycleObservers.entrySet()) {
                it.getValue().dispatchEvent(owner, event);
            }
        }
    }


    @Override
    public void addObserver(LifecycleObserver observer) {
        mLifecycleObservers.put(observer, new EventDispatcher(observer));
    }

    @Override
    public void removeObserver(LifecycleObserver observer) {
        mLifecycleObservers.remove(observer);
    }


    /**
     * Receives and dispatches any lifecycle changes.
     */
    private static final class EventDispatcher {
        private final LifecycleObserver mLifecycleObserver;

        EventDispatcher(LifecycleObserver observer) {
            mLifecycleObserver = observer;
        }

        void dispatchEvent(LifecycleOwner source, Lifecycle.Event event) {
            switch (event) {
                case ON_CREATE: {
                    mLifecycleObserver.onCreate(source);
                    break;
                }
                case ON_START: {
                    mLifecycleObserver.onStart(source);
                    break;
                }
                case ON_RESUME: {
                    mLifecycleObserver.onResume(source);
                    break;
                }
                case ON_PAUSE: {
                    mLifecycleObserver.onPause(source);
                    break;
                }
                case ON_STOP: {
                    mLifecycleObserver.onStop(source);
                    break;
                }
                case ON_DESTROY: {
                    mLifecycleObserver.onDestroy(source);
                    break;
                }
                default: {
                    throw new IllegalArgumentException("Unknown lifecycle event: " + event);
                }
            }
        }
    }

}

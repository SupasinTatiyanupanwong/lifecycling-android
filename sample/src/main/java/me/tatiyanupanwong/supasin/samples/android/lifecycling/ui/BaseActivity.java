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

package me.tatiyanupanwong.supasin.samples.android.lifecycling.ui;

import android.support.annotation.NonNull;
import android.support.v7.app.ActionBar;
import android.view.MenuItem;

import me.tatiyanupanwong.supasin.libraries.android.lifecyclingx.LifecycleActivity;

/**
 * @author Supasin Tatiyanupanwong (supast49@aware.postbox.in.th)
 */
public abstract class BaseActivity extends LifecycleActivity {

    @NonNull
    protected ActionBar getSupportActionBarNonNull() {
        ActionBar actionBar = getSupportActionBar();
        if (actionBar == null) {
            throw new NullPointerException("ActionBar has not been initialized.");
        }
        return actionBar;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

}

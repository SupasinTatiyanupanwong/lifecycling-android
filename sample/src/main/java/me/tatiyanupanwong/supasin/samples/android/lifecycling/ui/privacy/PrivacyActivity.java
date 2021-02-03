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

package me.tatiyanupanwong.supasin.samples.android.lifecycling.ui.privacy;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import me.tatiyanupanwong.supasin.samples.android.lifecycling.R;
import me.tatiyanupanwong.supasin.samples.android.lifecycling.ui.BaseActivity;

/**
 * @author Supasin Tatiyanupanwong (supast49@aware.postbox.in.th)
 */
public class PrivacyActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_privacy);

        setSupportActionBar((Toolbar) findViewById(R.id.privacy_toolbar));
        getSupportActionBarNonNull().setDisplayHomeAsUpEnabled(true);
        getSupportActionBarNonNull().setDisplayShowHomeEnabled(true);
        getSupportActionBarNonNull().setDisplayShowTitleEnabled(true);

    }

}

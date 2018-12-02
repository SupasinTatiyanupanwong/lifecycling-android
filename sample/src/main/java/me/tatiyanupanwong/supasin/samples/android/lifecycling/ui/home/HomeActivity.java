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

package me.tatiyanupanwong.supasin.samples.android.lifecycling.ui.home;

import android.app.Activity;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.ColorInt;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.View;
import android.widget.TextView;

import me.tatiyanupanwong.supasin.samples.android.lifecycling.R;
import me.tatiyanupanwong.supasin.samples.android.lifecycling.ui.BaseActivity;

/**
 * @author Supasin Tatiyanupanwong (supast49@aware.postbox.in.th)
 */
public class HomeActivity extends BaseActivity implements HomeInterface {

    private ViewHolder views;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_home);
        views = new ViewHolder(this);

        views.menuMembershipLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openPage(HomePage.MEMBERSHIP);
            }
        });

        views.menuSettingsLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openPage(HomePage.SETTINGS);
            }
        });

        openPage(HomePage.MEMBERSHIP);
    }


    @Override
    public void openPage(HomePage page) {
        int defColor = Color.GRAY;
        tintTextView(views.menuMembershipButton, defColor);
        tintTextView(views.menuSettingsButton, defColor);

        switch (page) {
            case MEMBERSHIP: {
                tintTextView(views.menuMembershipButton, getResources().getColor(R.color.primary));
                break;
            }
            case SETTINGS: {
                tintTextView(views.menuSettingsButton, getResources().getColor(R.color.primary));
                break;
            }
        }

        setFragment(page);
    }


    private void setFragment(HomePage page) {
        FragmentManager fragmentManager = getSupportFragmentManager();

        fragmentManager.executePendingTransactions();
        fragmentManager.beginTransaction()
                .replace(R.id.home_content,
                        Fragment.instantiate(this, page.fragmentClass.getName()), page.name())
                .commit();
    }


    private void tintTextView(TextView textView, @ColorInt int colorInt) {
        textView.setTextColor(colorInt);
        for (Drawable drawable : textView.getCompoundDrawables()) {
            if (drawable != null && colorInt != Color.TRANSPARENT) {
                drawable.mutate().setColorFilter(colorInt, PorterDuff.Mode.SRC_IN);
            }
        }
    }


    private static final class ViewHolder {
        final View menuMembershipLayout;
        final TextView menuMembershipButton;
        final View menuSettingsLayout;
        final TextView menuSettingsButton;

        ViewHolder(Activity activity) {
            menuMembershipLayout = activity.findViewById(R.id.menu_membership_layout);
            menuMembershipButton = (TextView) activity.findViewById(R.id.menu_membership_button);
            menuSettingsLayout = activity.findViewById(R.id.menu_settings_layout);
            menuSettingsButton = (TextView) activity.findViewById(R.id.menu_settings_button);
        }
    }

}

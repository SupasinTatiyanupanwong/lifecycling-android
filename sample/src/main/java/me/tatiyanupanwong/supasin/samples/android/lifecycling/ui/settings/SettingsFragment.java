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

package me.tatiyanupanwong.supasin.samples.android.lifecycling.ui.settings;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import me.tatiyanupanwong.supasin.samples.android.lifecycling.R;
import me.tatiyanupanwong.supasin.samples.android.lifecycling.ui.home.HomeFragment;
import me.tatiyanupanwong.supasin.samples.android.lifecycling.ui.home.HomePage;
import me.tatiyanupanwong.supasin.samples.android.lifecycling.ui.privacy.PrivacyActivity;

/**
 * @author Supasin Tatiyanupanwong (supast49@aware.postbox.in.th)
 */
public class SettingsFragment extends HomeFragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
            @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_settings, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ViewHolder views = new ViewHolder(view);

        views.profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getHomeInterface().openPage(HomePage.MEMBERSHIP);
            }
        });

        views.privacy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), PrivacyActivity.class));
            }
        });

        views.logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new AlertDialog.Builder(getActivity())
                        .setTitle(R.string.logout_title)
                        .setMessage(R.string.logout_prompt)
                        .setPositiveButton(android.R.string.ok,
                                new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        getActivity().finishAffinity();
                                    }
                                })
                        .setNegativeButton(android.R.string.cancel, null)
                        .show();
            }
        });

    }

    private static final class ViewHolder {
        final View profile;
        final View privacy;
        final View logout;

        ViewHolder(@NonNull View view) {
            profile = view.findViewById(R.id.settings_profile);
            privacy = view.findViewById(R.id.settings_privacy);
            logout = view.findViewById(R.id.settings_logout);
        }
    }

}

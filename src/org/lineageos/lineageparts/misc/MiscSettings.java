/*
 * Copyright (C) 2023 Havoc-OS
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.lineageos.lineageparts.misc;

import android.content.Context;
import android.os.Bundle;
import android.os.SystemProperties;

import androidx.preference.Preference;
import androidx.preference.PreferenceScreen;
import androidx.preference.SwitchPreference;

import org.lineageos.lineageparts.R;
import org.lineageos.lineageparts.SettingsPreferenceFragment;

public class MiscSettings extends SettingsPreferenceFragment
        implements Preference.OnPreferenceChangeListener {

    private static final String KEY_PHOTOS_SPOOF = "use_photos_spoof";
    private static final String SYS_PHOTOS_SPOOF = "persist.sys.pixelprops.gphotos";
    private SwitchPreference mPhotosSpoof;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.misc_settings);

        final PreferenceScreen prefScreen = getPreferenceScreen();

        mPhotosSpoof = (SwitchPreference) prefScreen.findPreference(KEY_PHOTOS_SPOOF);
        mPhotosSpoof.setChecked(SystemProperties.getBoolean(SYS_PHOTOS_SPOOF, true));
        mPhotosSpoof.setOnPreferenceChangeListener(this);
    }

    @Override
    public boolean onPreferenceChange(Preference preference, Object newValue) {
        if (preference == mPhotosSpoof) {
            boolean value = (Boolean) newValue;
            SystemProperties.set(SYS_PHOTOS_SPOOF, value ? "true" : "false");
        }
        return true;
    }
}

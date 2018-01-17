/*Copyright 2017 Vasili Sheshka

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

        http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.*/

package net.callrec.library.fix;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.support.v4.content.ContextCompat;

public class CallRecorderUtil {

    private static CallRecorderUtil ourInstance = new CallRecorderUtil();

    public static synchronized CallRecorderUtil getInstance() {
        if (ourInstance == null) ourInstance = new CallRecorderUtil();
        return ourInstance;
    }

    private CallRecorderUtil() {
    }

    public boolean changeAudioSourceToCall(Context context, int audioSessionId) {
        if (isPermissionGranted(context)) {
            CallRecorderFix.load();
            CallRecorderFix.startFix(audioSessionId);
            return true;
        }
        return false;
    }

    public void changeAudioSourceToMic() {
        CallRecorderFix.stopFix();
    }

    private boolean isPermissionGranted(Context context) {
        return ContextCompat.checkSelfPermission(context, Manifest.permission.MODIFY_AUDIO_SETTINGS)
                == PackageManager.PERMISSION_GRANTED;
    }
}

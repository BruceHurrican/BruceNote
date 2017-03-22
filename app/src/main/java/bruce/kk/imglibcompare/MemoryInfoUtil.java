/*
 * BruceHurrican
 * Copyright (c) 2016.
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 *
 *    This document is Bruce's individual learning the android demo, wherein the use of the code from the Internet, only to use as a learning exchanges.
 *    And where any person can download and use, but not for commercial purposes.
 *    Author does not assume the resulting corresponding disputes.
 *    If you have good suggestions for the code, you can contact BurrceHurrican@foxmail.com
 *    本文件为Bruce's个人学习android的作品, 其中所用到的代码来源于互联网，仅作为学习交流使用。
 *    任和何人可以下载并使用, 但是不能用于商业用途。
 *    作者不承担由此带来的相应纠纷。
 *    如果对本代码有好的建议，可以联系BurrceHurrican@foxmail.com
 */

package bruce.kk.imglibcompare;

import android.app.ActivityManager;
import android.content.Context;
import android.text.format.Formatter;

import com.bruceutils.utils.logdetails.LogDetails;

import static android.content.Context.ACTIVITY_SERVICE;

/**
 * Created by BruceHurrican on 17/3/16.
 */

public class MemoryInfoUtil {
    public static String memoryAvailableInfo(Context context) {
        ActivityManager activityManager = (ActivityManager) context.getSystemService(ACTIVITY_SERVICE);
        ActivityManager.MemoryInfo info = new ActivityManager.MemoryInfo();
        activityManager.getMemoryInfo(info);
        LogDetails.d(Formatter.formatFileSize(context, info.availMem));
        LogDetails.d(Formatter.formatFileSize(context, info.totalMem));
        LogDetails.i("运行时最大内存: " + Formatter.formatFileSize(context, Runtime.getRuntime().maxMemory()));
        LogDetails.i("运行时全部内存: " + Formatter.formatFileSize(context, Runtime.getRuntime().totalMemory()));
        LogDetails.i("运行时可用内存: " + Formatter.formatFileSize(context, Runtime.getRuntime().freeMemory()));
        LogDetails.i("运行时占用内存: " + Formatter.formatFileSize(context, Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory()));
        return Formatter.formatFileSize(context, info.availMem);
    }
}

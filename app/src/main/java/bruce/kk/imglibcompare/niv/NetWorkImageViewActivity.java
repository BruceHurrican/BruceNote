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
 *    This document is Bruce's individual learning the android demo, wherein the use of the code from the Internet,
 *    only to use as a learning exchanges.
 *    And where any person can download and use, but not for commercial purposes.
 *    Author does not assume the resulting corresponding disputes.
 *    If you have good suggestions for the code, you can contact BurrceHurrican@foxmail.com
 *    本文件为Bruce's个人学习android的作品, 其中所用到的代码来源于互联网，仅作为学习交流使用。
 *    任和何人可以下载并使用, 但是不能用于商业用途。
 *    作者不承担由此带来的相应纠纷。
 *    如果对本代码有好的建议，可以联系BurrceHurrican@foxmail.com
 */

package bruce.kk.imglibcompare.niv;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;
import com.android.volley.toolbox.Volley;
import com.bruceutils.base.BaseActivity;
import com.bruceutils.net.volley.VolleyBitmapCache;

import bruce.kk.imglibcompare.ImgConstant;
import bruce.kk.imglibcompare.R;
import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by BruceHurrican on 17/3/10.
 */

public class NetWorkImageViewActivity extends BaseActivity {
    private static RequestQueue queue;
    private String requestTage = "netWorkImageView";
    private ImageLoader imageLoader;
    @Bind(R.id.iv_img)
    ImageView ivImg;
    @Bind(R.id.btn_load_local)
    Button btnLoadLocal;
    @Bind(R.id.btn_load_url)
    Button btnLoadUrl;
    @Bind(R.id.btn_load_cancel)
    Button btnLoadCancel;
    @Bind(R.id.iv_niv)
    NetworkImageView ivNiv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.niv_activity);
        ButterKnife.bind(this);
        setTitle("NetWorkImageView");
        queue = Volley.newRequestQueue(getApplicationContext());
        imageLoader = new ImageLoader(queue, new VolleyBitmapCache());
    }

    @OnClick({R.id.btn_load_local, R.id.btn_load_url, R.id.btn_load_cancel})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_load_local:
//                ivNiv.setImageResource(R.mipmap.ic_loading);
                ivNiv.setDefaultImageResId(R.mipmap.ic_start);
                ivNiv.setErrorImageResId(R.mipmap.ic_failed);
                break;
            case R.id.btn_load_url:
                ivNiv.setDefaultImageResId(R.mipmap.ic_start);
                ivNiv.setErrorImageResId(R.mipmap.ic_failed);
                ivNiv.setImageUrl(ImgConstant.IMG_URL, imageLoader);
                break;
            case R.id.btn_load_cancel:
                queue.cancelAll(requestTage);
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
    }

    public static void startGlideActivity(Context context) {
        context.startActivity(new Intent(context, NetWorkImageViewActivity.class));
    }
}

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

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.bruceutils.base.BaseActivity;

import bruce.kk.imglibcompare.cube.CubeActivity;
import bruce.kk.imglibcompare.fresco.FrescoActivity;
import bruce.kk.imglibcompare.glide.GlideActivity;
import bruce.kk.imglibcompare.imageloader.ImageLoaderActivity;
import bruce.kk.imglibcompare.niv.NetWorkImageViewActivity;
import bruce.kk.imglibcompare.picasso.PicassoActivity;
import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends BaseActivity {

    @Bind(R.id.btn_glide)
    Button btnGlide;
    @Bind(R.id.btn_cube)
    Button btnCube;
    @Bind(R.id.btn_imageloader)
    Button btnImageloader;
    @Bind(R.id.btn_niv)
    Button btnNiv;
    @Bind(R.id.btn_picasso)
    Button btnPicasso;
    @Bind(R.id.btn_fresco)
    Button btnFresco;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.btn_glide, R.id.btn_cube, R.id.btn_imageloader, R.id.btn_niv, R.id.btn_picasso, R.id.btn_fresco})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_glide:
                GlideActivity.startGlideActivity(MainActivity.this);
                break;
            case R.id.btn_cube:
                CubeActivity.startGlideActivity(MainActivity.this);
                break;
            case R.id.btn_imageloader:
                ImageLoaderActivity.startGlideActivity(MainActivity.this);
                break;
            case R.id.btn_niv:
                NetWorkImageViewActivity.startGlideActivity(MainActivity.this);
                break;
            case R.id.btn_picasso:
                PicassoActivity.startGlideActivity(MainActivity.this);
                break;
            case R.id.btn_fresco:
                FrescoActivity.startGlideActivity(MainActivity.this);
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
    }
}

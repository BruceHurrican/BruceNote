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

package bruce.kk.imglibcompare.cube;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.bruceutils.base.BaseActivity;

import bruce.kk.imglibcompare.ImgConstant;
import bruce.kk.imglibcompare.R;
import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import in.srain.cube.Cube;
import in.srain.cube.image.CubeImageView;
import in.srain.cube.image.ImageLoader;
import in.srain.cube.image.ImageLoaderFactory;
import in.srain.cube.image.impl.DefaultImageLoadHandler;

/**
 * Created by BruceHurrican on 17/3/10.
 */

public class CubeActivity extends BaseActivity {
    @Bind(R.id.btn_load_local)
    Button btnLoadLocal;
    @Bind(R.id.btn_load_url)
    Button btnLoadUrl;
    @Bind(R.id.btn_load_cancel)
    Button btnLoadCancel;
    @Bind(R.id.iv_img)
    ImageView ivImg;
    @Bind(R.id.iv_cube)
    CubeImageView ivCube;

    private ImageLoader imageLoader;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cube_activity);
        ButterKnife.bind(this);
        setTitle("Cube");
        Cube.onCreate(getApplication());
        imageLoader = ImageLoaderFactory.create(CubeActivity.this);
    }

    @OnClick({R.id.btn_load_local, R.id.btn_load_url, R.id.btn_load_cancel})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_load_local:
//                ivCube.setImageResource(R.mipmap.ic_failed); // 没有效果
                ivCube.setBackgroundResource(R.mipmap.ic_failed);
                break;
            case R.id.btn_load_url:
                // 自定义监听器
//                imageLoader.setImageLoadHandler(new ImageLoadHandler() {
//                    @Override
//                    public void onLoading(ImageTask imageTask, CubeImageView cubeImageView) {
//                        LogDetails.d("图片加载中...");
//                        cubeImageView.setImageResource(R.mipmap.ic_loading);
//                    }
//
//                    @Override
//                    public void onLoadFinish(ImageTask imageTask, CubeImageView cubeImageView, BitmapDrawable drawable) {
//                        LogDetails.d("图片加载结束");
//                        cubeImageView.setImageDrawable(drawable);
//                    }
//
//                    @Override
//                    public void onLoadError(ImageTask imageTask, CubeImageView imageView, int errorCode) {
//                        LogDetails.d("图片失败错误码: %d", errorCode);
//                        imageView.setImageResource(R.mipmap.ic_failed);
//                    }
//                });
                // 默认监听器
                DefaultImageLoadHandler defaultImageLoadHandler = new DefaultImageLoadHandler(CubeActivity.this);
                defaultImageLoadHandler.setErrorResources(R.mipmap.ic_failed);
                defaultImageLoadHandler.setImageFadeIn(true);
                defaultImageLoadHandler.setResizeImageViewAfterLoad(false);
                defaultImageLoadHandler.setLoadingResources(R.mipmap.ic_loading2);
                defaultImageLoadHandler.setImageRounded(true, 30);
                imageLoader.setImageLoadHandler(defaultImageLoadHandler);
                ivCube.loadImage(imageLoader, ImgConstant.IMG_URL);
                break;
            case R.id.btn_load_cancel:
                ivCube.clearDrawable();
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
    }

    public static void startGlideActivity(Context context) {
        context.startActivity(new Intent(context, CubeActivity.class));
    }
}

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

package bruce.kk.imglibcompare.fresco;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Animatable;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.bruceutils.base.BaseActivity;
import com.bruceutils.utils.LogUtils;
import com.bruceutils.utils.logdetails.LogDetails;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.controller.BaseControllerListener;
import com.facebook.drawee.controller.ControllerListener;
import com.facebook.drawee.drawable.ProgressBarDrawable;
import com.facebook.drawee.generic.GenericDraweeHierarchy;
import com.facebook.drawee.generic.GenericDraweeHierarchyBuilder;
import com.facebook.drawee.generic.RoundingParams;
import com.facebook.drawee.interfaces.DraweeController;
import com.facebook.drawee.view.SimpleDraweeView;

import bruce.kk.imglibcompare.ImgConstant;
import bruce.kk.imglibcompare.R;
import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by BruceHurrican on 17/3/10.
 */

public class FrescoActivity extends BaseActivity {
    @Bind(R.id.btn_load_local)
    Button btnLoadLocal;
    @Bind(R.id.btn_load_url)
    Button btnLoadUrl;
    @Bind(R.id.btn_load_gif)
    Button btnLoadGif;
    @Bind(R.id.iv_img)
    ImageView ivImg;
    @Bind(R.id.fresco_view)
    SimpleDraweeView frescoView;
    private ControllerListener listener;
    private RoundingParams roundingParams;
    private GenericDraweeHierarchyBuilder builder;
    private GenericDraweeHierarchy hierarchy;
    private DraweeController controller;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Fresco.initialize(this);
        setContentView(R.layout.fresco_activity);
        ButterKnife.bind(this);
        setTitle("Fresco");

        roundingParams = new RoundingParams();
        roundingParams.setCornersRadius(30);

        builder = new GenericDraweeHierarchyBuilder(getResources());
        hierarchy = builder
                .setProgressBarImage(new ProgressBarDrawable())
                .setFailureImage(R.mipmap.ic_failed)
                .setFadeDuration(1500)
                .setPlaceholderImage(R.mipmap.ic_launcher)
                .setRetryImage(R.mipmap.ic_start)
                .setRoundingParams(roundingParams)
                .build();
        frescoView.setHierarchy(hierarchy);

        listener = new BaseControllerListener(){
            @Override
            public void onSubmit(String id, Object callerContext) {
                super.onSubmit(id, callerContext);
                LogUtils.d("===onSubmit===");
            }

            @Override
            public void onFinalImageSet(String id, Object imageInfo, Animatable animatable) {
                super.onFinalImageSet(id, imageInfo, animatable);
                LogUtils.d("===onFinalImageSet===" + imageInfo);
            }

            @Override
            public void onIntermediateImageSet(String id, Object imageInfo) {
                super.onIntermediateImageSet(id, imageInfo);
                LogUtils.d("===onIntermediateImageSet===" + imageInfo);
            }

            @Override
            public void onIntermediateImageFailed(String id, Throwable throwable) {
                super.onIntermediateImageFailed(id, throwable);
                LogUtils.d("===onIntermediateImageFailed===" + throwable);
            }

            @Override
            public void onFailure(String id, Throwable throwable) {
                super.onFailure(id, throwable);
                LogUtils.d("===onFailure===" + throwable);
            }

            @Override
            public void onRelease(String id) {
                super.onRelease(id);
                LogUtils.d("===onRelease===");
            }
        };
    }

    @OnClick({R.id.btn_load_local, R.id.btn_load_url, R.id.btn_load_gif})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_load_local:
                Uri tempUri = Uri.parse("res://" + getPackageName() + "/" + R.mipmap.ic_start);
                LogDetails.d("tempUri: " + tempUri);
                frescoView.setImageURI(tempUri);
                break;
            case R.id.btn_load_url:
                // 方法一 加载网络资源
//                Uri uri = Uri.parse(ImgConstant.IMG_URL);
//                frescoView.setImageURI(uri);
                // 方法二 加载资源
                controller = Fresco.newDraweeControllerBuilder()
                                   .setUri(ImgConstant.IMG_URL)
                                   .setTapToRetryEnabled(true)
                                   .setAutoPlayAnimations(true)
                                   .setControllerListener(listener)
                                   .build();
                frescoView.setController(controller);
                break;
            case R.id.btn_load_gif:
                controller = Fresco.newDraweeControllerBuilder()
                                   .setUri(ImgConstant.GIF_URL)
                                   .setTapToRetryEnabled(true)
                                   .setAutoPlayAnimations(true)
                                   .setControllerListener(listener)
                                   .build();
                frescoView.setController(controller);
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
    }

    public static void startGlideActivity(Context context) {
        context.startActivity(new Intent(context, FrescoActivity.class));
    }
}

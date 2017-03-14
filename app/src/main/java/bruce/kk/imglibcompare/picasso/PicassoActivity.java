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

package bruce.kk.imglibcompare.picasso;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.PixelFormat;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.bruceutils.base.BaseActivity;
import com.bruceutils.utils.logdetails.LogDetails;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Transformation;

import bruce.kk.imglibcompare.ImgConstant;
import bruce.kk.imglibcompare.R;
import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by BruceHurrican on 17/3/10.
 */

public class PicassoActivity extends BaseActivity {
    @Bind(R.id.btn_load_local)
    Button btnLoadLocal;
    @Bind(R.id.btn_load_url)
    Button btnLoadUrl;
    @Bind(R.id.btn_load_cancel)
    Button btnLoadCancel;
    @Bind(R.id.iv_img)
    ImageView ivImg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.img_activity);
        ButterKnife.bind(this);
        setTitle("Picasso");
    }

    @OnClick({R.id.btn_load_local, R.id.btn_load_url, R.id.btn_load_cancel})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_load_local:
                Picasso.with(PicassoActivity.this)
                       .load(R.mipmap.ic_loading)
                       .into(ivImg);
                break;
            case R.id.btn_load_url:
                // 方法一
//                Picasso.with(PicassoActivity.this)
//                       .load(ImgConstant.IMG_URL)
//                       .resize(80, 80)
//                       .error(R.mipmap.ic_failed)
//                       .into(ivImg);
                // 方法二
                Picasso.Builder builder = new Picasso.Builder(PicassoActivity.this);
                builder.listener(new Picasso.Listener() {
                    @Override
                    public void onImageLoadFailed(Picasso picasso, Uri uri, Exception exception) {
                        LogDetails.d("加载图片失败 exception: " + exception);
                    }
                });
                builder.build()
                       .load(ImgConstant.IMG_URL)
//                       .load("http://dd.com/ssss.jpg")
                       .placeholder(R.mipmap.ic_loading2)
                       .error(R.mipmap.ic_failed)
                       // 通过 tansform 设置圆角图片
                       .transform(new Transformation() {
                           @Override
                           public Bitmap transform(Bitmap source) {
                               RoundedBitmapDrawable drawable = RoundedBitmapDrawableFactory.create(getResources(), source);
                               drawable.setCornerRadius(50);
//                             drawable.setCircular(true); // 设置圆形头像使用
                               // 获取 宽高
                               int width = drawable.getIntrinsicWidth();
                               int height = drawable.getIntrinsicHeight();
                               // 获取颜色格式
                               Bitmap.Config config = drawable.getOpacity() != PixelFormat.OPAQUE ? Bitmap.Config.ARGB_8888 : Bitmap.Config.RGB_565;
                               // 建立对应 bitmap
                               Bitmap bitmap = Bitmap.createBitmap(width, height, config);
                               if (bitmap != source) {
                                   source.recycle();
                               }
                               Canvas canvas = new Canvas(bitmap);
                               drawable.setBounds(0, 0, width, height);
                               drawable.draw(canvas);
                               return bitmap;
                           }

                           @Override
                           public String key() {
                               return "rounded";
                           }
                       })
                       .into(ivImg);
                break;
            case R.id.btn_load_cancel:
                Picasso.with(PicassoActivity.this).cancelRequest(ivImg);
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
    }

    public static void startGlideActivity(Context context) {
        context.startActivity(new Intent(context, PicassoActivity.class));
    }
}

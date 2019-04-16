package com.example.administrator.myappgit.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestBuilder;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.signature.ObjectKey;
import com.example.administrator.myappgit.BuildConfig;
import com.example.administrator.myappgit.R;
import com.example.administrator.myappgit.ui.GlideCircleTransform;

import java.io.File;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ChangeHeadIconActivity extends BaseActivity {

    @BindView(R.id.iv)
    ImageView mIv;
    @BindView(R.id.bt)
    Button mBt;
    protected static final int CHOOSE_PICTURE = 0;
    protected static final int TAKE_PICTURE = 1;
    private static final int CROP_SMALL_PICTURE = 2;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_head_icon);
        ButterKnife.bind(this);

    }

    @Override
    protected void onResume() {
        super.onResume();
        RequestBuilder<Drawable> load = null;
        if (new File(ChangeHeadIconActivity.this.getExternalCacheDir(), "headship_icon.jpg").exists()) {
            load = Glide.with(this).load(new File(ChangeHeadIconActivity.this.getExternalCacheDir(), "headship_icon.jpg"));
        } else {
            load = Glide.with(this).load(R.drawable.d_1);

        }
        load.apply(new RequestOptions().error(R.drawable.information)
                .signature(new ObjectKey(System.currentTimeMillis()))
                .placeholder(R.drawable.information)
                .centerCrop()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .transform(new GlideCircleTransform(this)))
                .into(mIv);
    }

    @OnClick(R.id.bt)
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.bt:
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                String[] items = {"选择本地照片", "拍照"};
                builder.setNegativeButton("取消", null);
                builder.setItems(items, new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        switch (which) {
                            case CHOOSE_PICTURE: // 选择本地照片
                                Intent intentFromGallery = new Intent();
                                // 设置文件类型
                                intentFromGallery.setType("image/*");
                                intentFromGallery.setAction(Intent.ACTION_PICK);
                                startActivityForResult(intentFromGallery, CHOOSE_PICTURE);
                                break;
                            case TAKE_PICTURE: // 拍照

                                Intent intentFromCapture = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                                Uri uri;
                                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
//                                    intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
                                    uri = FileProvider.getUriForFile(ChangeHeadIconActivity.this, BuildConfig.APPLICATION_ID + ".provider",
                                            new File(ChangeHeadIconActivity.this.getExternalCacheDir(), "headship.png"));
                                } else {
                                    uri = Uri.fromFile(new File(ChangeHeadIconActivity.this.getExternalCacheDir(), "headship.png"));
                                }
                                intentFromCapture.putExtra(MediaStore.EXTRA_OUTPUT, uri);

                                startActivityForResult(intentFromCapture, TAKE_PICTURE);

                                break;
                        }
                    }
                });
                builder.create().show();
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) { // 如果返回码是可以用的
            switch (requestCode) {
                case TAKE_PICTURE:
                    startPhotoZoom(null); // 开始对图片进行裁剪处理
                    break;
                case CHOOSE_PICTURE:
                    startPhotoZoom(data.getData()); // 开始对图片进行裁剪处理
                    break;
                case CROP_SMALL_PICTURE:
                    if (data != null) {
                        setImageToView(data); // 让刚才选择裁剪得到的图片显示在界面上
                    }
                    break;
            }
        }
    }

    private long currentTimeMillis;

    /**
     * 裁剪图片方法实现
     *
     * @param uri
     */
    protected void startPhotoZoom(Uri uri) {
        if (uri == null) {
            Log.i("tag", "The uri is not exist.");
            //判断是否是AndroidN以及更高的版本
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                uri = FileProvider.getUriForFile(this, BuildConfig.APPLICATION_ID + ".provider",
                        new File(ChangeHeadIconActivity.this.getExternalCacheDir(), "headship.png"));

            } else {
                uri = Uri.fromFile(new File(ChangeHeadIconActivity.this.getExternalCacheDir(), "headship.png"));
            }
        }

        currentTimeMillis = System.currentTimeMillis();

        Intent intent = new Intent("com.android.camera.action.CROP");
        intent.setDataAndType(uri, "image/*");
        intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
        intent.addFlags(Intent.FLAG_GRANT_WRITE_URI_PERMISSION);
        intent.putExtra("crop", "true"); // crop=true 有这句才能出来最后的裁剪页面.
        intent.putExtra("aspectX", 1); // 这两项为裁剪框的比例
        intent.putExtra("aspectY", 1); //
        intent.putExtra("outputX", 320);
        intent.putExtra("outputY", 320);
        intent.putExtra("return-data", true);


//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
////                                    intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
//            uri_output = FileProvider.getUriForFile(ChangeHeadIconActivity.this, BuildConfig.APPLICATION_ID + ".provider",
//                    new File(ConstantValues.CACHE_DIR, "headship_" + currentTimeMillis + ".jpg"));
//        } else {
//            uri_output = Uri.parse("file://" + "/" + ConstantValues.CACHE_DIR + "/" + "headship_" + currentTimeMillis + ".jpg");
//        }
//
//        intent.putExtra("output", uri_output); // 输出地址

//        intent.putExtra("output", Uri.fromFile(new File(ConstantValues.CACHE_DIR, "headship_" + currentTimeMillis + ".jpg"))); // 输出地址


//        Uri uri_output = Uri.parse("file://" + "/" + Environment.getExternalStorageDirectory() + "/SpongeCityFile" + "/" + "headship_" + currentTimeMillis + ".jpg");
        new File(ChangeHeadIconActivity.this.getExternalCacheDir(), "headship_icon.jpg").deleteOnExit();
        Uri uri_output = Uri.parse("file://" + "/" + ChangeHeadIconActivity.this.getExternalCacheDir() + "/" + "headship_icon.jpg");
        intent.putExtra("output", uri_output); // 输出地址


        intent.putExtra("outputFormat", Bitmap.CompressFormat.JPEG.toString()); // 返回格式
        intent.putExtra("scale", true);// 裁剪时是否保留图片的比例，这里的比例是1:1
        intent.putExtra("noFaceDetection", true); // no face detection
        intent.putExtra("scaleUpIfNeeded", true);// 如果小于要求输出大小，就放大
        startActivityForResult(intent, CROP_SMALL_PICTURE);
    }

    /**
     * 保存裁剪之后的图片数据
     */
    protected void setImageToView(Intent data) {
//        File file = new File(ChangeHeadIconActivity.this.getExternalCacheDir(), "headship_" + currentTimeMillis + ".jpg");
        File file = new File(ChangeHeadIconActivity.this.getExternalCacheDir(), "headship_icon.jpg");
        if (file.exists()) {
            Glide.with(this).load(file)
                    .apply(new RequestOptions().error(R.drawable.information)
                            .placeholder(R.drawable.information)
                            .centerCrop()
                            .diskCacheStrategy(DiskCacheStrategy.ALL)
                            .transform(new GlideCircleTransform(this)))
                    .into(mIv);
//            headShipFile = file;
        }
    }

}

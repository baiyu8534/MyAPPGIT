package com.example.administrator.myappgit.activity;

import android.app.Activity;
import android.app.WallpaperManager;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.Target;
import com.example.administrator.myappgit.BuildConfig;
import com.example.administrator.myappgit.R;
import com.example.administrator.myappgit.app.AppConstant;
import com.example.administrator.myappgit.utils.FileUtil;
import com.example.administrator.myappgit.utils.UIUtil;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.ExecutionException;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 文件名：ShowImageActivity
 * 描述：显示图片
 * 作者：白煜
 * 时间：2017/9/18 0018
 * 版权：
 */

public class ShowImageActivity extends BaseActivity {

    @BindView(R.id.iv)
    ImageView mIv;
    private String mImageUrl;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        // FIXME: 2017/9/18 0018 要加教程遮罩，长按保存，设置桌面
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_iamge_layout);
        ButterKnife.bind(this);
        initData();
        initView();
        initViewListener();
    }

    private void initViewListener() {
        mIv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    ShowImageActivity.this.finishAfterTransition();
                } else {
                    ShowImageActivity.this.finish();
                }
            }
        });
        mIv.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
                builder.setTitle("保存和设置桌面背景");
                builder.setMessage("保存和设置桌面背景");
                builder.setNegativeButton(R.string.save, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        new AsyncTask<Object, Object, File>() {
                            @Override
                            protected File doInBackground(Object... params) {
                                File file = null;
                                try {
                                    file = Glide.with(mContext)
                                            .download(mImageUrl)
                                            .submit(Target.SIZE_ORIGINAL, Target.SIZE_ORIGINAL)
                                            .get();
                                } catch (InterruptedException e) {
                                    e.printStackTrace();
                                } catch (ExecutionException e) {
                                    e.printStackTrace();
                                }
                                return file;
                            }

                            @Override
                            protected void onPostExecute(File file) {
                                //图库文件夹
                                File pictureFolder = Environment.getExternalStoragePublicDirectory(Environment
                                        .DIRECTORY_PICTURES).getAbsoluteFile();
                                String time = System.currentTimeMillis() + "";
                                if (BuildConfig.DEBUG) Log.d("ShowImageActivity++time", time);
                                String fileName = time + ".jpg";
                                File newFile = new File(pictureFolder, fileName);
                                FileUtil.copyFile(file, newFile);

                                //通知图库更新
                                sendBroadcast(new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE,
                                        Uri.fromFile(new File(newFile.getPath()))));

                                UIUtil.showMessageDialog((Activity) mContext, "以保存", AppConstant.ICON_TYPE_SUCCESS);
                            }
                        }.execute();
                    }
                });
                builder.setNeutralButton(R.string.cancel, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                builder.setPositiveButton(R.string.save_add_set, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        new AsyncTask<Object, Object, File>() {
                            @Override
                            protected File doInBackground(Object... params) {
                                File file = null;
                                try {
                                    file = Glide.with(mContext)
                                            .download(mImageUrl)
                                            .submit(Target.SIZE_ORIGINAL, Target.SIZE_ORIGINAL)
                                            .get();
                                } catch (InterruptedException e) {
                                    e.printStackTrace();
                                } catch (ExecutionException e) {
                                    e.printStackTrace();
                                }
                                return file;
                            }

                            @Override
                            protected void onPostExecute(File file) {
                                //图库文件夹
                                File pictureFolder = Environment.getExternalStoragePublicDirectory(Environment
                                        .DIRECTORY_PICTURES).getAbsoluteFile();
                                String time = System.currentTimeMillis() + "";
                                if (BuildConfig.DEBUG) Log.d("ShowImageActivity++time", time);
                                String fileName = time + ".jpg";
                                File newFile = new File(pictureFolder, fileName);
                                FileUtil.copyFile(file, newFile);

                                //通知图库更新
                                sendBroadcast(new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE,
                                        Uri.fromFile(new File(newFile.getPath()))));

                                WallpaperManager mWallManager = WallpaperManager.getInstance(mContext);
                                try {
                                    mWallManager.setBitmap(BitmapFactory.decodeFile(newFile.getPath()));
                                } catch (IOException e) {
                                    e.printStackTrace();
                                    UIUtil.showMessageDialog((Activity) mContext, "以保存\n设置壁纸失败！", AppConstant.ICON_TYPE_SUCCESS);
                                    return;
                                }
                                UIUtil.showMessageDialog((Activity) mContext, "以保存并设置为壁纸！", AppConstant.ICON_TYPE_SUCCESS);
                            }
                        }.execute();
                    }
                });
                builder.show();
                return true;
            }
        });
    }

    private void initView() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            mIv.setTransitionName("iv_item");
        }
        Glide.with(mContext).load(mImageUrl)
                .apply(new RequestOptions()
                        .diskCacheStrategy(DiskCacheStrategy.ALL)
                        .centerCrop())
                //加载缩略图，缩略图先加载完就显示，否则不显示
                .into(mIv);
    }

    private void initData() {
        mImageUrl = getIntent().getStringExtra("imageUrl");
    }
}

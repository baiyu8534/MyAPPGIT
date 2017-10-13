package com.example.administrator.myappgit.utils;

/**
 * 文件名：DownloadUtil
 * 描述：下载工具
 * 作者：白煜
 * 时间：2017/10/13 0013
 * 版权：
 */

public class DownloadUtil {

    public void download(final String url) {
//        new AsyncTask() {
//            @Override
//            protected Object doInBackground(Object[] params) {
//                File file = null;
//                try {
//                    FutureTarget future = Glide
//                            .with(ImageActivity.this)
//                            .load(url)
//                            .downloadOnly(Target.SIZE_ORIGINAL, Target.SIZE_ORIGINAL);
//                    file = (File) future.get();
//                    // 首先保存图片
//                    File pictureFolder = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES).getAbsoluteFile();
//                    File appDir = new File(pictureFolder ,"Beauty");
//                    if (!appDir.exists()) {
//                        appDir.mkdirs();
//                    }
//                    String fileName = System.currentTimeMillis() + ".jpg";
//                    File destFile = new File(appDir, fileName);
//                    FileUtil.copy(file, destFile);
//                    // 最后通知图库更新
//                    sendBroadcast(new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE,
//                            Uri.fromFile(new File(destFile.getPath()))));
//                } catch (Exception e) {
//                    Log.e(TAG, e.getMessage());
//                }
//                return file;
//            }
//
//            @Override
//            protected void onPostExecute(Object o) {
//                Toast.makeText(ImageActivity.this, "saved in Pictures/GankBeauty", Toast.LENGTH_SHORT).show();
//            }
//        }.execute();
    }

}

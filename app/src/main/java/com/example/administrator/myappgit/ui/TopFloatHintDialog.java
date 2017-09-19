package com.example.administrator.myappgit.ui;

import android.app.Dialog;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.StyleRes;
import android.support.v4.content.ContextCompat;
import android.text.TextUtils;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.administrator.myappgit.R;

/**
 * 文件名：TopFloatHintDialog
 * 描述：顶层悬浮dialog
 * 作者：白煜
 * 时间：2017/9/19 0019
 * 版权：
 */

public class TopFloatHintDialog extends Dialog {
    public TopFloatHintDialog(@NonNull Context context) {
        this(context, R.style.TopFloatHintDialog);
    }

    public TopFloatHintDialog(@NonNull Context context, @StyleRes int themeResId) {
        super(context, themeResId);
        setCanceledOnTouchOutside(false);
        setCancelable(false);
    }

    protected TopFloatHintDialog(@NonNull Context context, boolean cancelable, @Nullable OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);

    }

    public static class Builder {

        /**
         * 显示 Loading 图标
         */
        public static final int ICON_TYPE_LOADING = 1;
        /**
         * 显示成功图标
         */
        public static final int ICON_TYPE_SUCCESS = 2;
        /**
         * 显示失败图标
         */
        public static final int ICON_TYPE_FAIL = 3;
        /**
         * 显示信息图标
         */
        public static final int ICON_TYPE_INFO = 4;

        private Context mContext;
        private int iconType;
        private CharSequence message;

        public Builder(Context context) {
            mContext = context;
        }

        public Builder setIconType(int iconType) {
            this.iconType = iconType;
            return this;
        }

        public Builder setMessage(CharSequence message) {
            this.message = message;
            return this;
        }

        public TopFloatHintDialog create() {
            TopFloatHintDialog dialog = new TopFloatHintDialog(mContext);
            dialog.setContentView(R.layout.dialog_top_float_layout);
            ViewGroup content = (ViewGroup) dialog.findViewById(R.id.ll_content);

            //在下面这种情况下，后台的activity不会被遮盖，也就是只会遮盖此dialog大小的部分
            WindowManager.LayoutParams attributes = dialog.getWindow().getAttributes();
            attributes.gravity = Gravity.CENTER;
            attributes.dimAmount = 0.0f; // 去背景遮盖
            dialog.getWindow().setAttributes(attributes);

            switch (iconType) {
                case ICON_TYPE_LOADING:

                    break;
                case ICON_TYPE_SUCCESS:
                case ICON_TYPE_FAIL:
                case ICON_TYPE_INFO:
                    ImageView imageView = new ImageView(mContext);
                    LinearLayout.LayoutParams imageViewLP = new LinearLayout.LayoutParams(LinearLayout.LayoutParams
                            .WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                    imageView.setLayoutParams(imageViewLP);

                    if (iconType == ICON_TYPE_SUCCESS) {
                        imageView.setImageDrawable(mContext.getResources().getDrawable(R.mipmap.qmui_icon_notify_done));
                    } else if (iconType == ICON_TYPE_FAIL) {
                        imageView.setImageDrawable(mContext.getResources().getDrawable(R.mipmap.qmui_icon_notify_error));
                    } else {
                        imageView.setImageDrawable(mContext.getResources().getDrawable(R.mipmap.qmui_icon_notify_info));
                    }

                    content.addView(imageView);
                    break;
            }

            TextView tipView = new TextView(mContext);
            LinearLayout.LayoutParams tipViewLP = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT);

            tipView.setLayoutParams(tipViewLP);

            tipView.setEllipsize(TextUtils.TruncateAt.END);
            tipView.setGravity(Gravity.CENTER);
            tipView.setMaxLines(2);
            tipView.setTextColor(ContextCompat.getColor(mContext, R.color.colorWhite));
            tipView.setTextSize(TypedValue.COMPLEX_UNIT_SP, 14);
            tipView.setText(message);
            content.addView(tipView);

            return dialog;
        }
    }
}

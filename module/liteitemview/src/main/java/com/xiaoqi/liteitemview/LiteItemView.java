package com.xiaoqi.liteitemview;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.support.constraint.ConstraintLayout;
import android.support.constraint.Group;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;

public class LiteItemView extends ConstraintLayout {

    private ConstraintLayout mCl; // 根约束布局
    private ImageView mIvLeft; // 左侧图标
    private ImageView mIvRight; // 右侧图标
    private TextView mTvLeft; // 左侧文字
    private TextView mTvRight; // 右侧文字
    private Switch mSw; // 右侧选择按钮
    private View mV; // 分割线

    private Group mGpLeft; // 左侧控件群组
    private Group mGpRight; // 右侧控件群组

    private OnLiteItemViewClickListener mOnLiteItemViewClickListener; // 点击监听
    private OnLiteItemCheckChangeListener mOnLiteItemCheckChangeListener; // 选择按钮切换监听

    public LiteItemView(Context context) {
        this(context, null);
    }

    public LiteItemView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public LiteItemView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context);
        customizeView(context, attrs);
    }

    private void initView(Context context) {
        final View view = View.inflate(context, R.layout.layout, this);
        mCl = view.findViewById(R.id.cl);
        mIvLeft = view.findViewById(R.id.iv_left);
        mIvRight = view.findViewById(R.id.iv_right);
        mTvLeft = view.findViewById(R.id.tv_left);
        mTvRight = view.findViewById(R.id.tv_right);
        mSw = view.findViewById(R.id.sw);
        mGpLeft = view.findViewById(R.id.gp_left);
        mGpRight = view.findViewById(R.id.gp_right);

        mV = view.findViewById(R.id.v);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mOnLiteItemViewClickListener != null) {
                    mOnLiteItemViewClickListener.onClick(view);
                }
            }
        });

        mSw.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (mOnLiteItemCheckChangeListener != null ) {
                    mOnLiteItemCheckChangeListener.onCheckedChanged(buttonView, isChecked);
                }
            }
        });
    }

    private void customizeView(Context context, AttributeSet attrs) {
        TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.LiteItemView);
        int n = array.getIndexCount();
        for (int i = 0; i < n; i++) {
            int attr = array.getIndex(i);
            if (attr == R.styleable.LiteItemView_leftIcon) {
                mIvLeft.setImageDrawable(array.getDrawable(attr));
            } else if (attr == R.styleable.LiteItemView_rightIcon) {
                mIvRight.setImageDrawable(array.getDrawable(attr));
            } else if (attr == R.styleable.LiteItemView_leftText) {
                mTvLeft.setText(array.getString(attr));
            } else if (attr == R.styleable.LiteItemView_leftTextSize) {
                mTvLeft.setTextSize(TypedValue.COMPLEX_UNIT_SP, (int) array.getDimension(attr, 16));
            } else if (attr == R.styleable.LiteItemView_leftTextColor) {
                mTvLeft.setTextColor(array.getColor(attr, Color.GRAY));
            } else if (attr == R.styleable.LiteItemView_rightText) {
                mTvRight.setText(array.getString(attr));
            } else if (attr == R.styleable.LiteItemView_rightTextSize) {
                mTvRight.setTextSize(TypedValue.COMPLEX_UNIT_SP, (int) array.getDimension(attr, 12));
            } else if (attr == R.styleable.LiteItemView_rightTextColor) {
                mTvRight.setTextColor(array.getColor(attr, Color.GRAY));
            } else if (attr == R.styleable.LiteItemView_isShowLeftText) {
                mTvLeft.setVisibility(array.getBoolean(attr, true) ? VISIBLE : INVISIBLE); // 默认显示左侧文字
            } else if (attr == R.styleable.LiteItemView_isShowRightText) {
                mTvRight.setVisibility(array.getBoolean(attr, false) ? VISIBLE : INVISIBLE); // 默认不显示右侧文字
            } else if (attr == R.styleable.LiteItemView_isShowLeftIcon) {
                mIvLeft.setVisibility(array.getBoolean(attr, true) ? VISIBLE : GONE); // 默认显示左侧图标
            } else if (attr == R.styleable.LiteItemView_rightStyle) {
                int rightStyle = array.getInt(attr, 0);
                switch (rightStyle) {
                    case 0:
                        mIvRight.setVisibility(VISIBLE);
                        break;
                    case 1:
                        mIvRight.setVisibility(GONE);
                        break;
                    case 2:
                        mSw.setVisibility(VISIBLE);
                        mGpRight.setVisibility(GONE);
                        break;
                }
            }
        }
        array.recycle();
    }

    public void setLeftIcon(Drawable drawable) {
        mIvLeft.setImageDrawable(drawable);
    }

    public void setLeftIcon(int resId) {
        mIvLeft.setImageResource(resId);
    }

    public void setLeftIconVisible(boolean visible) {
        mIvLeft.setVisibility(visible ? VISIBLE : GONE);
    }

    public void setRightIcon(Drawable drawable) {
        mIvRight.setImageDrawable(drawable);
    }

    public void setRightIcon(int resId) {
        mIvRight.setImageResource(resId);
    }

    public void setLeftText(String str) {
        mTvLeft.setText(str);
    }

    public String getLeftText() {
        return mTvLeft.getText().toString().trim();
    }

    public void setLeftTextSize(float sizeInSp) {
        mTvLeft.setTextSize(TypedValue.COMPLEX_UNIT_SP, sizeInSp);
    }

    public void setLeftTextColor(int color) {
        mTvLeft.setTextColor(color);
    }

    public void setLeftTextVisible(boolean visible) {
        mTvLeft.setVisibility(visible ? VISIBLE : INVISIBLE);
    }

    public void setRightText(String str) {
        mTvRight.setText(str);
    }

    public String getRightText() {
        return mTvRight.getText().toString().trim();
    }

    public void setRightTextSize(float sizeInSp) {
        mTvRight.setTextSize(TypedValue.COMPLEX_UNIT_SP, sizeInSp);
    }

    public void setRightTextColor(int color) {
        mTvRight.setTextColor(color);
    }

    public void setRightTextVisible(boolean visible) {
        mTvRight.setVisibility(visible ? VISIBLE : INVISIBLE);
    }

    public void setHeight(int sizeInDp) {
        ConstraintLayout.LayoutParams params = new LayoutParams(LayoutParams.MATCH_PARENT, (int) dp2px(sizeInDp));
        mCl.setLayoutParams(params);
    }

    public void setSeparatorLineColor(int color) {
        mV.setBackgroundColor(color);
    }

    public void setOnLiteItemViewClick(OnLiteItemViewClickListener listener) {
        mOnLiteItemViewClickListener = listener;
    }

    public void setOnLiteItemCheckChangeListener(OnLiteItemCheckChangeListener listener) {
        mOnLiteItemCheckChangeListener = listener;
    }

    public interface OnLiteItemViewClickListener extends OnClickListener {
    }

    public interface OnLiteItemCheckChangeListener extends CompoundButton.OnCheckedChangeListener {
    }

    private float px2dp(int sizeIdPx) {
        float scale = this.getResources().getDisplayMetrics().density;
        return sizeIdPx / scale + 0.5f;
    }

    private float dp2px(int sizeInDp) {
        float scale = this.getResources().getDisplayMetrics().density;
        return sizeInDp * scale + 0.5f;
    }
}

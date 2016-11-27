package com.feijian.xdrawableview.widget;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.TextView;

import com.feijian.xdrawableview.R;

/**
 * Created by Administrator on 2016/11/27.
 */

public class TextViewDrawable extends TextView {

    int mTopWidth, mTopHeight, mBottomWidth, mBottomHeight, mLeftWidth, mLeftHeight, mRightWidth, mRightHeight;
    Drawable mDrawableTop, mDrawableBottom, mDrawableLeft, mDrawableRight;
    Context mContext;
    final int mDefValue = 20;  //默认高宽

    public TextViewDrawable(Context context) {
        super(context);
        mContext = context;
    }

    public TextViewDrawable(Context context, AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
        applyDrawable(attrs);
    }

    public TextViewDrawable(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mContext = context;
        applyDrawable(attrs);
    }

    private void applyDrawable(AttributeSet attrs) {
        if (attrs != null) {
            float scale = mContext.getResources().getDisplayMetrics().density;
            TypedArray a = mContext.obtainStyledAttributes(attrs, R.styleable.TextViewDrawable);
            int n = a.getIndexCount();
            for (int i = 0; i < n; i++) {
                int attr = a.getIndex(i);
                switch (attr) {
                    case R.styleable.TextViewDrawable_drawableTopWidth:
                        mTopWidth = (int) (a.getDimension(attr, mDefValue) * scale + 0.5f);
                        break;
                    case R.styleable.TextViewDrawable_drawableTopHeight:
                        mTopHeight = (int) (a.getDimension(attr, mDefValue) * scale + 0.5f);
                        break;
                    case R.styleable.TextViewDrawable_drawableBottomWidth:
                        mBottomWidth = (int) (a.getDimension(attr, mDefValue) * scale + 0.5f);
                        break;
                    case R.styleable.TextViewDrawable_drawableBottomHeight:
                        mBottomHeight = (int) (a.getDimension(attr, mDefValue) * scale + 0.5f);
                        break;
                    case R.styleable.TextViewDrawable_drawableLeftWidth:
                        mLeftWidth = (int) (a.getDimension(attr, mDefValue) * scale + 0.5f);
                        Log.e("TextViewDrawable", "mLeftWidth=" + mLeftWidth + ";time=" + System.currentTimeMillis());
                        break;
                    case R.styleable.TextViewDrawable_drawableLeftHeight:
                        mLeftHeight = (int) (a.getDimension(attr, mDefValue) * scale + 0.5f);
                        Log.e("TextViewDrawable", "mLeftHeight=" + mLeftHeight);
                        break;
                    case R.styleable.TextViewDrawable_drawableRightWidth:
                        mRightWidth = (int) (a.getDimension(attr, mDefValue) * scale + 0.5f);
                        break;
                    case R.styleable.TextViewDrawable_drawableRightHeight:
                        mRightHeight = (int) (a.getDimension(attr, mDefValue) * scale + 0.5f);
                        break;

                    case R.styleable.TextViewDrawable_drawableTop:
                        mDrawableTop = a.getDrawable(attr);
                        break;
                    case R.styleable.TextViewDrawable_drawableBottom:
                        mDrawableBottom = a.getDrawable(attr);
                        break;
                    case R.styleable.TextViewDrawable_drawableLeft:
                        mDrawableLeft = a.getDrawable(attr);
                        break;
                    case R.styleable.TextViewDrawable_drawableRight:
                        mDrawableRight = a.getDrawable(attr);
                        break;
                    default:
                        break;
                }
            }
            a.recycle();
            setCompoundDrawablesWithIntrinsicBounds(mDrawableLeft, mDrawableTop, mDrawableRight, mDrawableBottom);
        }
    }

    @Override
    public void setCompoundDrawablesWithIntrinsicBounds(Drawable left, Drawable top, Drawable right, Drawable bottom) {
        if (left != null) {
            Log.e("TextViewDrawable", "width=" + (mLeftWidth <= 0 ? left.getIntrinsicWidth() : mLeftWidth) + ";time=" + System.currentTimeMillis());
            Log.e("TextViewDrawable", "height=" + (mLeftHeight <= 0 ? left.getMinimumHeight() : mLeftHeight));
            left.setBounds(0, 0, mLeftWidth <= 0 ? left.getIntrinsicWidth() : mLeftWidth, mLeftHeight <= 0 ? left.getMinimumHeight() : mLeftHeight);
        }
        if (right != null) {
            right.setBounds(0, 0, mRightWidth <= 0 ? right.getIntrinsicWidth() : mRightWidth, mRightHeight <= 0 ? right.getMinimumHeight() : mRightHeight);
        }
        if (top != null) {
            top.setBounds(0, 0, mTopWidth <= 0 ? top.getIntrinsicWidth() : mTopWidth, mTopHeight <= 0 ? top.getMinimumHeight() : mTopHeight);
        }
        if (bottom != null) {
            bottom.setBounds(0, 0, mBottomWidth <= 0 ? bottom.getIntrinsicWidth() : mBottomWidth, mBottomHeight <= 0 ? bottom.getMinimumHeight()
                    : mBottomHeight);
        }
        setCompoundDrawables(left, top, right, bottom);
    }
}

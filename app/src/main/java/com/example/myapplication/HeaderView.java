package com.example.myapplication;

import android.animation.ValueAnimator;
import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

public class HeaderView extends LinearLayout implements RefreshListener{
    public int state_prepare = 0;
    View content;
    int maxHeight = 300;
    int minRefreshHeight= 100;
    int state;
    public HeaderView(Context context) {
        this(context, null);
    }

    public HeaderView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public void init(Context context) {
        LayoutParams layoutParams = new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        setLayoutParams(layoutParams);
         content = LayoutInflater.from(context).inflate(R.layout.header_view,null);
        addView(content, new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 1));
        setGravity(Gravity.BOTTOM);
    }

    public void setVisibleHeight(int height) {
        if(height <= 1) {
            height = 1;
        }
        if(height > maxHeight) {
            height = maxHeight;
        }

        LayoutParams lp = (LayoutParams) content.getLayoutParams();
        lp.height = height;
        content.setLayoutParams(lp);
    }

    public int getVisibleHeight() {
        LayoutParams lp = (LayoutParams) content.getLayoutParams();
        return lp.height;

    }

    public void onMove(float offset) {
        int top = 0;
        if(offset > 0 ) {
            //下拉操作 且 已经拉到顶部 修改topview的高度
            setVisibleHeight((int) offset + getVisibleHeight());
            onRefreshPrepare();
        }
        if(offset < 0) {
            //下拉操作
            layout(getLeft(), 0, getRight(), getHeight());
            setVisibleHeight((int) offset + getVisibleHeight());

        }
        if(getVisibleHeight() == 0) {
            state = 1;
        }

    }

    public int getMinRefreshHeight() {
        return minRefreshHeight;
    }

    public void smoothScrollTo(int destHeight) {
//        Log.i("bottom", ""+getBottom());
//        Log.i("top", ""+getTop());
//        Log.i("bottom - top", ""+ (getBottom() - getTop()));
//        Log.i("getVisiableHeight", ""+ getVisibleHeight());
//        Log.i("getRealHeight", ""+ content.getHeight());
        ValueAnimator valueAnimator = ValueAnimator.ofInt(getVisibleHeight(), destHeight);
        valueAnimator.setDuration(300).start();
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                setVisibleHeight((int)valueAnimator.getAnimatedValue());
               // layout(getLeft(), 0, getRight(), getHeight());
            }
        });
    }


    @Override
    public void onRefreshPrepare() {
        state = state_prepare;
    }

    public int getState() {
        return state;
    }


}

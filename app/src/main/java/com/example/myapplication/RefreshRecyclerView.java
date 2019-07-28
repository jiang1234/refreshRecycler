package com.example.myapplication;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

public class RefreshRecyclerView extends RecyclerView {
    private HeaderWrapAdapter headerWrapAdapter;
    float lastY = -1;
    float lastX = -1;
    boolean isDrag = false;
    boolean isRefresh=false;

    float dY = -1;
    float sumOffset = 0;
    HeaderView view;

    public RefreshRecyclerView(Context context) {
        this(context, null);
    }

    public RefreshRecyclerView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public void setAdapter(Adapter adapter) {
        headerWrapAdapter = new HeaderWrapAdapter(adapter);
        super.setAdapter(headerWrapAdapter);
    }

    public void addRefreshHeader(View view) {
        headerWrapAdapter.addHeader(view);
        this.view = (HeaderView) view;
    }


    @Override
    public boolean onTouchEvent(MotionEvent e) {
        Log.i("event",e.getAction()+"");

        switch (e.getAction()) {
            case MotionEvent.ACTION_DOWN:
         //       Log.i("DOWN","down");
                if(lastX == -1 && lastY == -1) {
                    lastY = e.getRawY();
                    lastX = e.getRawX();
                }


                break;
            case MotionEvent.ACTION_CANCEL:
         //       Log.i("ACTION_CANCEL","ACTION_CANCEL");
            case MotionEvent.ACTION_UP:
         //       Log.i("ACTION_UP","ACTION_UP");
                lastY  = -1;
                lastX = -1;
                if( view.getVisibleHeight() > 1) {
                    view.smoothScrollTo(1);
                    isRefresh = false;
                    return true;
                }
                break;
            case MotionEvent.ACTION_MOVE:
           //     Log.i("ACTION_MOVE","ACTION_MOVE");

                float y = e.getRawY();
                float x = e.getRawX();
                float dy = y - lastY;
                float dx = x - lastX;
                sumOffset += dy;
                lastX = x;
                lastY = y;

                Log.i("isRefresh", isRefresh+"");
                Log.i("dy",dy +"");
              //  if(Math.abs(dy) > Math.abs(dx)) {
                    if(dy > 0 && isOnTop()) {
                        //处于顶部 下拉操作 显示刷新header

                        view.onMove(dy);
                        isRefresh = true;


                    }

                    if ((int)dy <= 0 && isRefresh) {
                        //上滑操作 已显示刷新header

                      // smoothScrollBy(0,-(int)dy);
                         view.onMove(dy);
                      return true;
                    }
            //    }
                Log.i("condition", ((int)dy <= 0 && isRefresh && Math.abs(dy) > Math.abs(dx)) +"");
                break;
        }
        return super.onTouchEvent(e);

    }

    /**
     * 判断是否到达顶部，topview的高度不能为0， 否则判断会出错
     * @return
     */
    public boolean isOnTop() {
        return !canScrollVertically(-1);
    }
}

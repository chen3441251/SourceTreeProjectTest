package com.webview.demo.circleviewdemo;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

/**
 * @ Creator     :     chenchao
 * @ CreateDate  :     2018/1/8 0008 17:47
 * @ Description :     CircleViewDemo
 */

public class CircleView extends View {

    private Paint mPaint;
    private int mColor;

    public CircleView(Context context) {
        this(context,null);
    }

    public CircleView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,-1);
    }

    public CircleView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.CircleView);
        mColor = typedArray.getColor(R.styleable.CircleView_circlecolor, -1);
        typedArray.recycle();
        initData();
    }

    private void initData() {
        //初始化画笔
        mPaint = new Paint();
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setStrokeWidth(20f);
//        mPaint.setColor(Color.RED);
        mPaint.setColor(mColor);

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //获取view的宽和高,并且支持padding属性
        int paddingleft=getPaddingLeft();
        int paddingRight=getPaddingRight();
        int paddingTop=getPaddingTop();
        int paddingBottom=getPaddingBottom();
        int width = getWidth()-paddingleft-paddingRight;
        int height = getHeight()-paddingBottom-paddingTop;
        int r=Math.min(width,height)/2;
        canvas.drawCircle(width/2+paddingleft,height/2+paddingTop,r,mPaint);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        //父view规划的大小
        int widthSpec = MeasureSpec.getSize(widthMeasureSpec);
        int heightSpec = MeasureSpec.getSize(heightMeasureSpec);
        int minWidth=300;
        int minHeight=300;
        if(getLayoutParams().height== ViewGroup.LayoutParams.WRAP_CONTENT&&getLayoutParams().width==ViewGroup.LayoutParams.WRAP_CONTENT){
            setMeasuredDimension(minWidth,minHeight);
        }else if(getLayoutParams().height== ViewGroup.LayoutParams.WRAP_CONTENT){
            //高度是自适应
            setMeasuredDimension(widthSpec,minHeight);
        }else if(getLayoutParams().width==ViewGroup.LayoutParams.WRAP_CONTENT){
            //宽度自适应
            setMeasuredDimension(minWidth,heightSpec);
        }
    }
}

package com.fanqilong.banner4recyclerview;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.LayerDrawable;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.widget.LinearLayout;

import java.util.ArrayList;

/**
 * 通用的指示器
 */
public class CommIndicatorView extends LinearLayout {

    private Context context;
    private int selectedIndicatorColor = 0xffff0000;
    private int unSelectedIndicatorColor = 0x88888888;
    private int IndicatorHeight = 6;
    private int IndicatorWidth = 6;
    private int indicatorSpace = 3;
    private int indicatorMargin = 10;

    private ArrayList<View> dotViews = new ArrayList<>();

    private int dotHeight = 12;
    private Shape indicatorShape;
    private Drawable unSelectedDrawable;
    private Drawable selectedDrawable;

    private enum Shape {
        rect, oval
    }

    public CommIndicatorView(Context context) {
        this(context, null);
    }

    public CommIndicatorView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CommIndicatorView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        this.context = context;
        dotHeight = DeviceUtils.dip2px(context, dotHeight);
        setGravity(Gravity.CENTER_HORIZONTAL);


        TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.CommIndicatorView);
        selectedIndicatorColor = array.getColor(R.styleable.CommIndicatorView_selectedColor, selectedIndicatorColor);
        unSelectedIndicatorColor = array.getColor(R.styleable.CommIndicatorView_unSelectedColor, unSelectedIndicatorColor);
        IndicatorHeight = (int) array.getDimension(R.styleable.CommIndicatorView_IndicatorHeight, IndicatorHeight);
        IndicatorWidth = (int) array.getDimension(R.styleable.CommIndicatorView_IndicatorWidth, IndicatorWidth);
//        <enum name="rect" value="0" />
//        <enum name="oval" value="1" />
        int shape = array.getInt(R.styleable.CommIndicatorView_Shape, Shape.oval.ordinal());
        for (Shape shape1 : Shape.values()) {
            if (shape1.ordinal() == shape) {
                indicatorShape = shape1;
                break;
            }
        }

        indicatorSpace = (int) array.getDimension(R.styleable.CommIndicatorView_IndicatorSpace, indicatorSpace);
        indicatorMargin = (int) array.getDimension(R.styleable.CommIndicatorView_IndicatorMargin, indicatorMargin);

        array.recycle();

        LayerDrawable unSelectedLayerDrawable;
        LayerDrawable selectedLayerDrawable;

        GradientDrawable selectedGradientDrawable = new GradientDrawable();
        GradientDrawable unSelectedGradientDrawable = new GradientDrawable();
        switch (indicatorShape) {
            case rect:
                unSelectedGradientDrawable.setShape(GradientDrawable.RECTANGLE);
                selectedGradientDrawable.setShape(GradientDrawable.RECTANGLE);
                break;
            case oval:
                unSelectedGradientDrawable.setShape(GradientDrawable.OVAL);
                selectedGradientDrawable.setShape(GradientDrawable.OVAL);
                break;
        }

        unSelectedGradientDrawable.setColor(unSelectedIndicatorColor);
        unSelectedGradientDrawable.setSize(IndicatorWidth, IndicatorHeight);
        unSelectedLayerDrawable = new LayerDrawable(new Drawable[]{unSelectedGradientDrawable});
        unSelectedDrawable = unSelectedLayerDrawable;


        selectedGradientDrawable.setColor(selectedIndicatorColor);
        selectedGradientDrawable.setSize(IndicatorWidth, IndicatorHeight);
        selectedLayerDrawable = new LayerDrawable(new Drawable[]{selectedGradientDrawable});
        selectedDrawable = selectedLayerDrawable;

    }

    public void init(int count) {
        dotViews.clear();
        removeAllViews();
        for (int i = 0; i < count; i++) {

            LayoutParams layoutParams = new LayoutParams(IndicatorWidth, IndicatorWidth);
            View view = new View(context);

            if (i == 0) {
                view.setBackground(selectedDrawable);
            } else {
                view.setBackground(unSelectedDrawable);
            }
            layoutParams.setMargins(indicatorSpace, indicatorSpace, indicatorSpace, indicatorSpace);
            view.setLayoutParams(layoutParams);
            addView(view);
            dotViews.add(view);
        }
    }


    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
    }

    public void selectTo(int position) {
        for (View iv : dotViews) {
            iv.setBackground(unSelectedDrawable);
        }
        dotViews.get(position).setBackground(selectedDrawable);
    }


    public void selectTo(int startPosition, int targetPosition) {
        View startView = dotViews.get(startPosition);
        View targetView = dotViews.get(targetPosition);
        startView.setBackground(unSelectedDrawable);
        targetView.setBackground(selectedDrawable);
    }

}   

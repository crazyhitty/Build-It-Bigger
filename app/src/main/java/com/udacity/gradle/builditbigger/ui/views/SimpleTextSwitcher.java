package com.udacity.gradle.builditbigger.ui.views;

import android.content.Context;
import android.content.res.TypedArray;
import android.os.Build;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextSwitcher;
import android.widget.TextView;

import com.udacity.gradle.builditbigger.R;

/**
 * Author: Kartik Sharma
 * Created on: 7/31/2016 , 4:25 PM
 * Project: FinalProject
 */

public class SimpleTextSwitcher extends TextSwitcher {

    // programmatically generated views
    private String mText;
    private int mTextAppearance;


    /**
     * {@link android.widget.ViewSwitcher.ViewFactory} Containing a {@link TextView} which will be
     * further used by {@link TextSwitcher}.
     */
    private ViewFactory mFactory = new ViewFactory() {
        @SuppressWarnings("deprecation")
        @Override
        public View makeView() {
            TextView textView = new TextView(getContext());
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                textView.setTextAppearance(mTextAppearance);
            } else {
                textView.setTextAppearance(getContext(), mTextAppearance);
            }
            return textView;
        }
    };

    public SimpleTextSwitcher(Context context) {
        super(context);
        initializeView();
    }

    public SimpleTextSwitcher(Context context, AttributeSet attrs) {
        super(context, attrs);
        initializeAttributes(attrs);
        initializeView();
    }

    /**
     * Sets the local properties based on the {@link AttributeSet} provided via xml or constructors.
     *
     * @param attributeSet Attributes containing properties like text or textAppearance
     */
    private void initializeAttributes(AttributeSet attributeSet){
        TypedArray typedArray=getContext().getTheme().obtainStyledAttributes(attributeSet, R.styleable.SimpleTextSwitcher, 0, 0);
        try{
            mText = typedArray.getString(R.styleable.SimpleTextSwitcher_switcherText);
            mTextAppearance = typedArray.getResourceId(R.styleable.SimpleTextSwitcher_switcherTextAppearance, android.R.style.TextAppearance_Medium);
        }finally {
            typedArray.recycle();
        }
    }

    public void setSwitcherText(String text) {
        mText = text;
        setText(text);
    }

    public void setSwitcherTextAppearance(int textAppearance) {
        this.mTextAppearance = textAppearance;
        invalidate();
    }

    /**
     * Initialize the default view.
     * This includes setting a {@link android.widget.ViewSwitcher.ViewFactory} containing a
     * {@link TextView}. Also adding a fade in and fade out animation whenever the text is switched.
     */
    private void initializeView(){
        setFactory(mFactory);

        Animation in = AnimationUtils.loadAnimation(getContext(),
                android.R.anim.fade_in);
        Animation out = AnimationUtils.loadAnimation(getContext(),
                android.R.anim.fade_out);
        setInAnimation(in);
        setOutAnimation(out);

        setCurrentText(mText);
    }
}

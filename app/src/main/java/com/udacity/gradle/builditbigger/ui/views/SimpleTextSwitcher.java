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
    private String mText;
    private int mTextAppearance;

    public SimpleTextSwitcher(Context context) {
        super(context);
        initializeView();
    }

    public SimpleTextSwitcher(Context context, AttributeSet attrs) {
        super(context, attrs);
        initializeAttributes(attrs);
        initializeView();

    }

    private void initializeAttributes(AttributeSet attributeSet){
        TypedArray typedArray=getContext().getTheme().obtainStyledAttributes(attributeSet, R.styleable.SimpleTextSwitcher, 0, 0);
        try{
            mText=typedArray.getString(R.styleable.SimpleTextSwitcher_text);
            mTextAppearance=typedArray.getResourceId(R.styleable.SimpleTextSwitcher_textAppearance, android.R.style.TextAppearance_Medium);
        }finally {
            typedArray.recycle();
        }
    }

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

    private ViewFactory mFactory = new ViewFactory() {
        @Override
        public View makeView() {
            TextView textView = new TextView(getContext());
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                textView.setTextAppearance(mTextAppearance);
            }else {
                textView.setTextAppearance(getContext(), mTextAppearance);
            }
            return textView;
        }
    };
}

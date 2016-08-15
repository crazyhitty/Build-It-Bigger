package com.udacity.gradle.builditbigger.ui.views;

import android.animation.LayoutTransition;
import android.content.Context;
import android.content.res.TypedArray;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ProgressBar;

import com.udacity.gradle.builditbigger.R;

/**
 * Author: Kartik Sharma
 * Created on: 7/31/2016 , 4:59 PM
 * Project: FinalProject
 */

public class LoadingView extends LinearLayout {
    private boolean mLoadingStatus = false;
    private int mProgressBarStyle;
    private String mText;
    private int mTextAppearance;

    // programmatically generated views
    private SimpleTextSwitcher mSimpleTextSwitcher;
    private ProgressBar mProgressBar;

    public LoadingView(Context context) {
        super(context);
        initializeView();
    }

    public LoadingView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initializeAttributes(attrs);
        initializeView();
    }

    public LoadingView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initializeAttributes(attrs);
        initializeView();
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public LoadingView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        initializeAttributes(attrs);
        initializeView();
    }

    public boolean isLoadingStatus() {
        return mLoadingStatus;
    }

    public void setLoadingStatus(boolean loadingStatus) {
        this.mLoadingStatus = loadingStatus;
        mProgressBar.setVisibility(loadingStatus ? View.VISIBLE : View.GONE);
    }

    public int getProgressBarStyle() {
        return mProgressBarStyle;
    }

    public void setProgressBarStyle(int progressBarStyle) {
        this.mProgressBarStyle = progressBarStyle;
    }

    public String getLoadingText() {
        return mText;
    }

    public void setLoadingText(String text) {
        this.mText = text;
        mSimpleTextSwitcher.setText(text);
    }

    public int getLoadingTextAppearance() {
        return mTextAppearance;
    }

    public void setLoadingTextAppearance(int textAppearance) {
        this.mTextAppearance = textAppearance;
    }

    /**
     * Sets the local properties based on the {@link AttributeSet} provided via xml or constructors.
     *
     * @param attributeSet Attributes containing properties like loadingStatus,
     *                     progressBarStyle, loadingText or loadingTextAppearance
     */
    private void initializeAttributes(AttributeSet attributeSet) {
        TypedArray typedArray = getContext().getTheme().obtainStyledAttributes(attributeSet, R.styleable.LoadingView, 0, 0);
        try {
            mLoadingStatus = typedArray.getBoolean(R.styleable.LoadingView_loadingStatus, false);
            mProgressBarStyle = typedArray.getInt(R.styleable.LoadingView_progressBarStyle, android.R.attr.progressBarStyleSmall);
            mText = typedArray.getString(R.styleable.LoadingView_loadingText);
            mTextAppearance = typedArray.getInt(R.styleable.LoadingView_loadingTextAppearance, android.R.style.TextAppearance_Small);
        } finally {
            typedArray.recycle();
        }
    }

    /**
     * Initialize the default view.
     * This includes adding a {@link ProgressBar} and {@link SimpleTextSwitcher} into the parent
     * view {@link LinearLayout} with horizontal orientation.
     */
    private void initializeView() {
        // initialize progress bar
        mProgressBar = new ProgressBar(getContext(), null, mProgressBarStyle);
        mProgressBar.setIndeterminate(true);
        mProgressBar.setVisibility(mLoadingStatus ? View.VISIBLE : View.GONE);
        LayoutParams layoutParamsProgressBar = new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);
        layoutParamsProgressBar.setMarginEnd(8);
        mProgressBar.setLayoutParams(layoutParamsProgressBar);

        // initialize text view
        mSimpleTextSwitcher = new SimpleTextSwitcher(getContext());
        mSimpleTextSwitcher.setText(mText);
        mSimpleTextSwitcher.setSwitcherTextAppearance(mTextAppearance);
        LayoutParams layoutParamsTextView = new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);
        mSimpleTextSwitcher.setLayoutParams(layoutParamsTextView);

        // set linear layout properties
        setOrientation(HORIZONTAL);
        setGravity(Gravity.CENTER_VERTICAL);

        // set layout transition
        setLayoutTransition(new LayoutTransition());

        // add views to the linear layout
        addView(mProgressBar);
        addView(mSimpleTextSwitcher);
    }
}

package ru.nikartm.support.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import ru.nikartm.support.R;

import static ru.nikartm.support.constant.Constants.DEFAULT_COLOR;
import static ru.nikartm.support.constant.Constants.DEFAULT_TEXT_SIZE;
import static ru.nikartm.support.constant.Constants.VISIBLE_FACTOR;

/**
 * @author Ivan V on 20.03.2018.
 * @version 1.0
 */
public class CollapsingTitleLayout extends LinearLayout {

    private String title;
    private String subtitle;
    private float titleSize;
    private float subtitleSize;
    private int titleColor;
    private int subtitleColor;

    @BindView(R.id.tv_title)
    protected TextView tvTitle;
    @BindView(R.id.tv_subtitle)
    protected TextView tvSubtitle;

    private float density;
    private float oldScale;
    private AppBarLayout appBar;

    public CollapsingTitleLayout(Context context) {
        super(context);
        bindView(context);
    }

    public CollapsingTitleLayout(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initAttrs(attrs);
        bindView(context);
    }

    private void initAttrs(AttributeSet attrs) {
        TypedArray typedArray = getContext().obtainStyledAttributes(attrs, R.styleable.CollapsingTitleLayout);
        title = typedArray.getString(R.styleable.CollapsingTitleLayout_ctl_textTitle);
        titleSize = typedArray.getDimension(R.styleable.CollapsingTitleLayout_ctl_titleTextSize, DEFAULT_TEXT_SIZE);
        titleColor = typedArray.getColor(R.styleable.CollapsingTitleLayout_ctl_titleColor, DEFAULT_COLOR);
        subtitle = typedArray.getString(R.styleable.CollapsingTitleLayout_ctl_textSubtitle);
        subtitleSize = typedArray.getDimension(R.styleable.CollapsingTitleLayout_ctl_subtitleTextSize, DEFAULT_TEXT_SIZE);
        subtitleColor = typedArray.getColor(R.styleable.CollapsingTitleLayout_ctl_subtitleColor, DEFAULT_COLOR);
        typedArray.recycle();
    }

    private void initTitles() {
        defineSizes();
        tvTitle.setText(title);
        tvTitle.setTextSize(titleSize);
        tvTitle.setTextColor(titleColor);
        tvSubtitle.setText(subtitle);
        tvSubtitle.setTextSize(subtitleSize);
        tvSubtitle.setTextColor(subtitleColor);
    }

    private void defineSizes() {
        titleSize = titleSize / density;
        subtitleSize = subtitleSize / density;
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        appBar = (AppBarLayout) getParent().getParent();
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        ButterKnife.bind(this);
        initTitles();
    }

    private void bindView(Context context) {
        density = context.getResources().getDisplayMetrics().density;
        setWillNotDraw(false);
        setOrientation(LinearLayout.VERTICAL);
        LayoutInflater.from(context).inflate(R.layout.title_layout, this, true);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        animateTitles();
    }

    private void animateTitles() {
        float scale = calculateScale();
        float scaleRate = scale * (scale * 2) / 2;
        float factor = Math.round(scaleRate * 100f) / 100f;
        if (factor < VISIBLE_FACTOR) {
            tvTitle.setVisibility(INVISIBLE);
            tvSubtitle.setVisibility(INVISIBLE);
        } else if (oldScale != scale) {
            oldScale = scale;
            tvTitle.setVisibility(VISIBLE);
            tvSubtitle.setVisibility(VISIBLE);
            tvTitle.setTextSize(TypedValue.COMPLEX_UNIT_SP, titleSize * factor);
            tvSubtitle.setTextSize(TypedValue.COMPLEX_UNIT_SP, subtitleSize * factor);
            tvTitle.setAlpha(factor);
            tvSubtitle.setAlpha(factor);
        }
        invalidate();
    }

    private int getAppBarScrollHeight() {
        final int total = appBar.getHeight();
        return total + appBar.getBottom();
    }

    final float calculateScale() {
        float currentHeight = getAppBarScrollHeight();
        float scaleRate = 0f;
        if (currentHeight != 0f) {
            scaleRate = (currentHeight / appBar.getHeight()) - 1f;
        }
        return scaleRate;
    }

    public TextView getTitle() {
        return tvTitle;
    }

    public TextView getSubtitle() {
        return tvSubtitle;
    }

    public CollapsingTitleLayout setText(String title, String subTitle) {
        this.tvTitle.setText(title);
        this.tvSubtitle.setText(subTitle);
        invalidate();
        return this;
    }

    public CollapsingTitleLayout setTitle(String title) {
        tvTitle.setText(title);
        invalidate();
        return this;
    }

    public CollapsingTitleLayout setSubTitle(String subtitle) {
        tvSubtitle.setText(subtitle);
        invalidate();
        return this;
    }

    public CollapsingTitleLayout setTitleSize (float size) {
        titleSize = size;
        invalidate();
        return this;
    }

    public CollapsingTitleLayout setSubTitleSize (float size) {
        subtitleSize = size;
        invalidate();
        return this;
    }

    public CollapsingTitleLayout setTitleColor(int colorId) {
        tvTitle.setTextColor(colorId);
        invalidate();
        return this;
    }

    public CollapsingTitleLayout setSubTitleColor(int colorId) {
        tvSubtitle.setTextColor(colorId);
        invalidate();
        return this;
    }
}
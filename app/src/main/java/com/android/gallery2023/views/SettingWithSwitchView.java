package com.android.gallery2023.views;

import android.content.Context;
import android.content.res.TypedArray;
import android.os.Build;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import androidx.appcompat.widget.SwitchCompat;

import com.android.gallery2023.R;
import com.android.gallery2023.util.preferences.Prefs;

import org.horaapps.liz.ThemeHelper;
import org.horaapps.liz.Themed;
import org.horaapps.liz.ThemedActivity;
import org.horaapps.liz.ui.ThemedIcon;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by darken (darken@darken.eu) on 04.03.2017.
 */
public class SettingWithSwitchView extends FrameLayout implements View.OnClickListener, Themed {

    private final String iconString;
    private final String preferenceKey;
    @StringRes
    private final int titleRes;
    @StringRes
    private final int captionRes;
    private final boolean defaultValue;
    @BindView(R.id.icon)
    ThemedIcon icon;
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.caption)
    TextView caption;
    @BindView(R.id.toggle)
    SwitchCompat toggle;
    @Nullable
    private OnClickListener clickListener;

    public SettingWithSwitchView(Context context) {
        this(context, null);
    }

    public SettingWithSwitchView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SettingWithSwitchView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        setBackgroundResource(R.drawable.ripple);

        LayoutInflater inflater = LayoutInflater.from(getContext());
        inflater.inflate(R.layout.view_setting_switch, this);

        TypedArray a = getContext().obtainStyledAttributes(attrs, R.styleable.SettingWithSwitchView);
        iconString = a.getString(R.styleable.SettingWithSwitchView_settingIcon);
        final int prefKeyRes = a.getResourceId(R.styleable.SettingWithSwitchView_settingPreferenceKey, 0);
        if (prefKeyRes == 0) throw new IllegalArgumentException("Invalid preference reference");
        preferenceKey = getResources().getString(prefKeyRes);
        titleRes = a.getResourceId(R.styleable.SettingWithSwitchView_settingTitle, 0);
        captionRes = a.getResourceId(R.styleable.SettingWithSwitchView_settingCaption, 0);
        defaultValue = a.getBoolean(R.styleable.SettingWithSwitchView_settingDefaultValue, false);
        int minimumApi = 0;//a.getInteger(R.styleable.SettingWithSwitchView_settingMinApi, 0);
        a.recycle();

        if (Build.VERSION.SDK_INT < minimumApi) setVisibility(GONE);
    }

    @Override
    protected void onFinishInflate() {
        ButterKnife.bind(this);

        icon.setIcon(icon.getIcon().icon(iconString));
        title.setText(titleRes);
        caption.setText(captionRes);
        toggle.setChecked(isChecked());
        super.setOnClickListener(this);

        /*
        setPadding((int) getResources().getDimension(R.dimen.medium_spacing), 0,(int) getResources().getDimension(R.dimen.medium_spacing), 0);
        setMinimumHeight((int) getResources().getDimension(R.dimen.listitem_height_twoline));
        */
        super.onFinishInflate();
    }

    @Override
    public void setOnClickListener(@Nullable OnClickListener clickListener) {
        this.clickListener = clickListener;
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        if (!isInEditMode()) refreshTheme(((ThemedActivity) getContext()).getThemeHelper());
    }

    @Override
    public void onClick(View view) {
        toggle();
        refreshTheme(((ThemedActivity) getContext()).getThemeHelper());
        if (clickListener != null) clickListener.onClick(this);
    }

    @Override
    public void refreshTheme(ThemeHelper themeHelper) {
        themeHelper.setSwitchCompactColor(toggle, themeHelper.getAccentColor());
    }

    public boolean isChecked() {
        return Prefs.getToggleValue(preferenceKey, defaultValue);
    }

    public void toggle() {
        Prefs.setToggleValue(preferenceKey, !isChecked());
        boolean checked = isChecked();
        toggle.setChecked(checked);
    }

}

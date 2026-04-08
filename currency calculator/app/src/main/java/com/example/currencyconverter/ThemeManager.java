package com.example.currencyconverter;

import android.content.Context;
import android.content.SharedPreferences;

public class ThemeManager {

    private static final String PREF_NAME = "theme_pref";
    private static final String KEY_THEME = "isDark";

    public static void setDarkMode(Context context, boolean isDark) {
        SharedPreferences prefs = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        prefs.edit().putBoolean(KEY_THEME, isDark).apply();
    }

    public static boolean isDarkMode(Context context) {
        SharedPreferences prefs = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        return prefs.getBoolean(KEY_THEME, false);
    }
}
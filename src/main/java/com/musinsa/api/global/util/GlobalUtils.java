package com.musinsa.api.global.util;

import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Map;

public class GlobalUtils {

    private static final Map<String, String> CATEGORY_MAP = new HashMap<>() {{
        put("상의", "상의");
        put("아우터", "아우터");
        put("바지", "바지");
        put("스니커즈", "스니커즈");
        put("가방", "가방");
        put("모자", "모자");
        put("양말", "양말");
        put("액세서리", "액세서리");
    }};

    public static final String getNumberCommaFormat(Number number) {
        DecimalFormat decimalFormat = new DecimalFormat("#,###");
        return decimalFormat.format(number);
    }

    public static final boolean isExistsCategoryName(String categoryName) {
        return CATEGORY_MAP.containsKey(categoryName);
    }
}

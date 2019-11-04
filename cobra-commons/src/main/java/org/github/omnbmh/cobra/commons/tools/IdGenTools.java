package org.github.omnbmh.cobra.commons.tools;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Stack;
import java.util.UUID;

/**
 * ID 生成工具
 */
public final class IdGenTools {
    // 5位数的箱子
    private static Stack<String> fiveNumberBox = new Stack<>();
    private static SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmssSSS");

    static {
        init();
    }

    private static void init() {
        fiveNumberBox.clear();
        for (int i = (int) Math.pow(10, 5) - 1; i > 0; i--) {
            fiveNumberBox.push(String.format("%05d", i));
        }
    }

    public static String uuid() {
        return UUID.randomUUID().toString().replaceAll("-", "");
    }

    /**
     * 支持每豪秒10wId的生成 22位 日期+5位数字
     */
    public static String getId() {
        String t = sdf.format(new Date());
        if (fiveNumberBox.size() < 100) {
            init();
        }
        String rdmStr = fiveNumberBox.pop();
        return t + rdmStr;
    }
}

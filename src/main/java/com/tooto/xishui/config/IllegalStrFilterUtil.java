package com.tooto.xishui.config;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Tooto
 * @className IllegalStrFilterUtil
 * @Description 非法字符正则表达式判断
 * @since 2019/4/14 15:57
 */
public class IllegalStrFilterUtil {
    public static final String REGX = "[ _`~!@#$%^&*()+=|{}':;',\\[\\].<>?~！@#￥%……&*（）——+|{}【】‘；：”“’。，、？]|\n|\r|\t"; // 正则表达式 包含特殊字符

    /**
     * 判断是否含有特殊字符
     * @param str
     * @return true为包含，false为不包含
     */
    public static boolean isSpecialChar(String str) {
        if (str == null || str.trim().length() == 0) {
            return false;
        }
        Pattern p = Pattern.compile(REGX);
        Matcher m = p.matcher(str);
        return m.find();
    }
}

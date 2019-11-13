package com.youguu.threads.test;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Test {

    /**
     * 判断字符是否是中文
     *
     * @param c 字符
     * @return 是否是中文
     */
    public static boolean isChinese(char c) {
        Character.UnicodeBlock ub = Character.UnicodeBlock.of(c);
        return ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS
                || ub == Character.UnicodeBlock.CJK_COMPATIBILITY_IDEOGRAPHS
                || ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS_EXTENSION_A
                || ub == Character.UnicodeBlock.GENERAL_PUNCTUATION
                || ub == Character.UnicodeBlock.CJK_SYMBOLS_AND_PUNCTUATION
                || ub == Character.UnicodeBlock.HALFWIDTH_AND_FULLWIDTH_FORMS;
    }

    /**
     * 判断字符串是否是乱码
     *
     * @param strName 字符串
     * @return 是否是乱码
     */
    public static boolean isMessyCode(String strName) {
        Pattern p = Pattern.compile("\\s*|t*|r*|n*");
        Matcher m = p.matcher(strName);
        String after = m.replaceAll("");
        String temp = after.replaceAll("\\p{P}", "");
        char[] ch = temp.trim().toCharArray();
        float chLength = ch.length;
        float count = 0;
        for (int i = 0; i < ch.length; i++) {
            char c = ch[i];
            if (!Character.isLetterOrDigit(c)) {
                if (!isChinese(c)) {
                    count = count + 1;
                }
            }
        }
        float result = count / chLength;
        return result > 0.4;

    }

    /**
     * 判断是否含有特殊字符
     *
     * @param str
     * @return true为包含，false为不包含
     */
    public static boolean isSpecialChar(String str) {
        String regEx = "[ _`~!@#$%^&*()+=|{}':;',\\[\\].<>/?~！@#￥%……&*（）——+|{}【】‘；：”“’。，、？]|\n|\r|\t";
        Pattern p = Pattern.compile(regEx);
        Matcher m = p.matcher(str);
        return m.find();
    }
    public static final String DEFAULT_QUERY_REGEX = "[!$^&*+=|{}';'\",<>/?~！#￥%……&*——|{}【】‘；：”“'。，、？\uF06C]";

    /**
     * 判断查询参数中是否以特殊字符开头，如果以特殊字符开头则返回true，否则返回false
     *
     * @param value
     * @return
     * @see {@link #getQueryRegex()}
     * @see {@link #DEFAULT_QUERY_REGEX}
     */
    public boolean specialSymbols(String value) {
        Pattern pattern = Pattern.compile(getQueryRegex());
        Matcher matcher = pattern.matcher(value);

        char[] specialSymbols = getQueryRegex().toCharArray();

        boolean isStartWithSpecialSymbol = false; // 是否以特殊字符开头
        for (int i = 0; i < specialSymbols.length; i++) {
            char c = specialSymbols[i];
            if (value.indexOf(c) == 0) {
                isStartWithSpecialSymbol = true;
                break;
            }
        }

        value = matcher.replaceAll("");

        System.out.println(value);

        return matcher.find() && isStartWithSpecialSymbol;
    }


    /**
     * 获取查询过滤的非法字符
     *
     * @return
     */
    protected String getQueryRegex() {
        return DEFAULT_QUERY_REGEX;
    }

    public static void main(String[] args) {

        String strs = "  啊   \uF06C近几年，    阿斯蒂2332芬\n阿斯a\rsdf蒂 芬";//  \s*|t*|r*|n*

        System.out.println(strs);

        String regEx = "[\uF06C]|\\s";

        Matcher m = Pattern.compile(regEx).matcher(strs);

        System.out.println(m.find());

        strs = m.replaceAll("");

        System.out.println(strs);


        //System.out.println(strs.replaceAll("\\s",""));

     /*   String str = "\uF06C影响公司股价的因素很多。公司将更专注于主营业务，\n做好客货运输组织，为  旅客和货主提供更好\t的产品和服务。更加注重运输结构的持续调整和优化，更加注重内部效率的提升，更加注重成本支出过程中的管控，为股东创造价值。谢谢关注，祝生活愉快！";

        System.out.println(str);

        Test test = new Test();

        boolean b = test.isSpecialChar(str);

        System.out.println(b);*/

       /* System.out.println(isMessyCode(str));

        //System.out.println(isMessyCode("Ã©Å¸Â©Ã©Â¡ÂºÃ¥Â¹Â³"));
        System.out.println(isMessyCode("你好"));*/
    }
}

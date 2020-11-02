package cn.mkh.blog.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {

    /**
     *  将日期转换为字符串
     * @param date 传入的日期
     * @param patt  传入的格式化字符串
     * @return 格式化后的字符串
     */
    public static String dateToString(Date date,String patt){
        SimpleDateFormat format = new SimpleDateFormat(patt);
        return format.format(date);
    }

    /**
     *
     * @param str 日期字符串
     * @param patt 转换格式
     * @return 转换后的日期类型
     * @throws ParseException
     */
    public static Date stringToDate(String str,String patt) throws ParseException {
        SimpleDateFormat format = new SimpleDateFormat(patt);
        return format.parse(str);
    }
}

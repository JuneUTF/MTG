package com.juneutf.mtg.config.vender;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import org.springframework.stereotype.Component;

/**
 * このクラスは、日付を日本語で曜日に変換します。
 */
@Component
public class GetIntoDay {

    /**
     * 日付を日本語で曜日に変換します。
     *
     * @param plan_day 変換する日付
     * @return 日本語での曜日
     */
    public String setDay(String plan_day,String date_day) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", Locale.JAPAN);
            Date date = sdf.parse(plan_day);
            SimpleDateFormat thuSdf = new SimpleDateFormat("E", Locale.JAPAN);
            return thuSdf.format(date);
        } catch (Exception e) {
            e.printStackTrace();
            return date_day;
        }
    }
}

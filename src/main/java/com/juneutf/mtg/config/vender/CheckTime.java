package com.juneutf.mtg.config.vender;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.springframework.stereotype.Component;

/**
 * {@code PlanModel} クラスは、現在の比較です。
 */
@Component
public class CheckTime {
    /**
     * プランの終了時間と現在の時間を比較して、プランの終了時間が現在の時間より後であるかどうかを判定します。
     *@param プランの計画日
     *@param プランの終了時間
     * @return プランの終了時間が現在の時間より後であれば  true、それ以外の場合は  false
     */
	public boolean checkTimeEnd(String date_plan,String time_end) {
		// 現在の時間を取得
        LocalDateTime currentTime = LocalDateTime.now();
        // PlanModel の日付と終了時間を LocalDateTime に変換
        LocalDateTime planDateTime = LocalDateTime.parse(date_plan + "T" + time_end,DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm"));
        // プランの終了時間が現在の時間より後であるかを判定
        return planDateTime.isAfter(currentTime);
	}
}

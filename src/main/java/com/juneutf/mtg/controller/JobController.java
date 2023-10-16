package com.juneutf.mtg.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.juneutf.mtg.model.JobModel;
import com.juneutf.mtg.model.SearchModel;
import com.juneutf.mtg.service.JobService;
import com.juneutf.mtg.service.PlanService;
import com.juneutf.mtg.service.SearchService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/kk")
public class JobController {
    @Autowired
    private SearchService searchService;
    @Autowired 
    private PlanService planService;
    @Autowired 
    private JobService jobService;
    @Autowired
    private SimpMessagingTemplate messagingTemplate;
    
    /**
     * 予約内容画面を取得するGETメソッド。
     *
     * @param model モデルオブジェクト
     * @param jobModel 予約モデル
     * @return 予約内容画面のテンプレート名
     */
    @GetMapping("/job")
    public String getJob(Model model, JobModel jobModel) {
        try {
            // IDを検証
            // IDがない場合、今日より予約内容を取得
            if (jobModel.getId() == 0) {
                ArrayList<JobModel> job = jobService.selectJob();
                model.addAttribute("job", job);
                return "job/job";
            } else {
                // IDがある場合、IDで予約内容を取得
                ArrayList<JobModel> job = jobService.selectJobById(jobModel.getId());
                // 取得した予約内容の比較
                // 予約内容がある
                if (job.size() != 0) {
                    // 内容を画面に渡す
                    model.addAttribute("job", job);
                    return "job/job";
                } else {
                    // 予約内容がない場合はエラー画面を表示
                    log.warn("ID:" + jobModel.getId() + "として予約内容が取得できません。");
                    return "error";
                }
            }
        } catch (Exception e) {
            log.warn("エラーが発生しました。");
            return "error";
        }
    }

    /**
     * 予約内容編集画面を取得するGETメソッド。
     *
     * @param model モデルオブジェクト
     * @param jobModel 予約モデル
     * @return 予約内容編集画面のテンプレート名
     */
    @GetMapping("job/edit")
    public String getEditID(Model model, JobModel jobModel) {
        try {
            // IDを検証
            // IDがない場合、予約内容に遷移
            if (jobModel.getId() == 0) {
                return "redirect:/kk/job";
            } else {
                // IDがある場合、IDで予約内容を取得
                ArrayList<JobModel> job = jobService.selectJobById(jobModel.getId());
                // 取得した予約内容の比較
                // 予約内容がある
                if (job.size() != 0) {
                    // 内容を画面に渡す
                    model.addAttribute("job", job);
                    return "job/edit";
                } else {
                    log.warn("編集予約内容機能でIDとして予約内容を取得する際、エラーが発生します。");
                    return "error";
                }
            }
        } catch (Exception e) {
            log.warn("編集予約内容機能はエラーが発生します。");
            return "error";
        }
    }

    /**
     * 予約内容をアップデートするPOSTメソッド。
     *
     * @param jobModel 予約モデル
     * @param model モデルオブジェクト
     * @return リダイレクトまたはエラーページのテンプレート名
     */
    @PostMapping("job")
    public String postEditID(JobModel jobModel, Model model) {
        // データベース内の予約内容が変更された結果
        int jobUpdate = jobService.updateJobById(jobModel);
        log.info("予約内容の編集情報：" + jobModel);
        // 予約内容が正常にアップデートされ、IDの上で画面に遷移
        if (jobUpdate == 1) {
            // Websocket行動
            ArrayList<JobModel> job = planService.selectPlan();
            messagingTemplate.convertAndSend("/job/notification", job);
            return "redirect:/kk/job?id=" + jobModel.getId();
        } else {
            log.warn("編集予約内容機能はエラーが発生します。");
            return "error";
        }
    }

    /**
     * 予約内容を削除するPOSTメソッド。
     *
     * @param jobModel 予約モデル
     * @param model モデルオブジェクト
     * @return リダイレクトまたはエラーページのテンプレート名
     */
    @PostMapping("job/delete")
    public String postDeleteID(JobModel jobModel, Model model) {
        // データベース内の予約内容が変更された結果
        int jobUpdate = jobService.deleteJobById(jobModel);
        log.info("予約内容の削除情報：" + jobModel);
        // 予約内容が正常に削除され、IDの上で画面に遷移
        if (jobUpdate == 1) {
            // Websocket行動
            ArrayList<JobModel> job = planService.selectPlan();
            messagingTemplate.convertAndSend("/job/notification", job);
            return "redirect:/kk/job";
        } else {
            return "redirect:/kk/job?id=" + jobModel.getId();
        }
    }

    /**
     * 予約内容を復元するPOSTメソッド。
     *
     * @param jobModel 予約モデル
     * @param model モデルオブジェクト
     * @return リダイレクトまたはエラーページのテンプレート名
     */
    @PostMapping("job/restore")
    public String postRestoreID(JobModel jobModel, Model model) {
        // データベース内の予約内容が変更された結果
        int jobUpdate = jobService.restoreJobById(jobModel);
        log.info("予約内容の復元情報：" + jobModel);
        // 予約内容が正常に復元され、IDの上で画面に遷移
        if (jobUpdate == 1) {
            // Websocket行動
            ArrayList<JobModel> job = planService.selectPlan();
            messagingTemplate.convertAndSend("/job/notification", job);
            return "redirect:/kk/job";
        } else {
            return "redirect:/kk/job?id=" + jobModel.getId();
        }
    }

    /**
     * 予約内容の検索を実行するPOSTメソッド。
     *
     * @param searchModel 検索モデル
     * @param model モデルオブジェクト
     * @return 検索結果画面のテンプレート名またはエラーページのテンプレート名
     */
    @PostMapping("job/search")
    public String postSearch(SearchModel searchModel, Model model) {
        try {
            ArrayList<JobModel> job = searchService.selectSearch(searchModel);
            model.addAttribute("job", job);
            return "job/search";
        } catch (Exception e) {
            log.warn("検索機能はエラーが発生します。");
            return "error";
        }
    }
}

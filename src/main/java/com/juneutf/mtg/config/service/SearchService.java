package com.juneutf.mtg.config.service;

import java.util.ArrayList;

import com.juneutf.mtg.model.JobModel;
import com.juneutf.mtg.model.SearchModel;

/**
 * SearchService インターフェースは、検索に関連するサービスメソッドを提供します。
 */
public interface SearchService {
    
    /**
     * 指定された検索条件に基づいてジョブを検索します。
     *
     * @param model 検索条件を指定するモデル
     * @return 検索結果のジョブのリスト
     */
    ArrayList<JobModel> selectSearch(SearchModel model);
}

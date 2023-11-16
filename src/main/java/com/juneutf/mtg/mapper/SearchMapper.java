package com.juneutf.mtg.mapper;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Mapper;

import com.juneutf.mtg.model.JobModel;
import com.juneutf.mtg.model.SearchModel;

/**
 * 検索操作のためのマッパーインタフェースです。
 */
@Mapper
public interface SearchMapper {
    /**
     * 指定された検索モデルに基づいて、ジョブモデルのリストを取得します。
     *
     * @param SearchModel 検索条件を表すSearchModel
     * @return 検索結果としてのJobModelのリスト
     */
    ArrayList<JobModel> selectSearch(SearchModel model);
}

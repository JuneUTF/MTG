package com.juneutf.mtg.iml;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.juneutf.mtg.mapper.SearchMapper;
import com.juneutf.mtg.model.JobModel;
import com.juneutf.mtg.model.SearchModel;
import com.juneutf.mtg.service.SearchService;

/**
 * このクラスは、検索関連のサービスを提供する実装です。
 */
@Service
public class SearchImpl implements SearchService {
    @Autowired
    private SearchMapper mapper;

    /**
     * 指定された検索条件に一致する予約内容モデルのリストを返します。
     *
     * @param model 検索条件を含む SearchModelオブジェクト
     * @return 検索結果として得られた JobModelのリスト
     */
    @Override
    public ArrayList<JobModel> selectSearch(SearchModel model) {
        return mapper.selectSearch(model);
    }
}

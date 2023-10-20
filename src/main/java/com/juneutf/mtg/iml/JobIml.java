package com.juneutf.mtg.iml;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.juneutf.mtg.mapper.JobMapper;
import com.juneutf.mtg.model.JobModel;
import com.juneutf.mtg.service.JobService;

/**
 * JobServiceの実装クラスです。
 */
@Service
public class JobIml implements JobService {
    @Autowired
    private JobMapper mapper;

    /**
     * 指定されたIDに対応する予約内容を選択します。
     *
     * @param id 選択する予約内容のID
     * @return 選択された予約内容のリスト
     */
    @Override
    public ArrayList<JobModel> selectJobById(int id) {
        return mapper.selectJobById(id);
    }

    /**
     * 指定された予約内容を更新します。
     *
     * @param jobModel 更新する予約内容の情報を含むモデル
     * @return 更新が成功した場合の結果コード
     */
    @Override
    public int updateJobById(JobModel jobModel) {
        return mapper.updateJobById(jobModel);
    }

    /**
     * 指定された予約内容を削除します。
     *
     * @param jobModel 削除する予約内容の情報を含むモデル
     * @return 削除が成功した場合の結果コード
     */
    @Override
    public int deleteJobById(JobModel jobModel) {
        return mapper.deleteJobById(jobModel);
    }

    /**
     * 指定された予約内容を復元します。
     *
     * @param jobModel 復元する予約内容の情報を含むモデル
     * @return 復元が成功した場合の結果コード
     */
    @Override
    public int restoreJobById(JobModel jobModel) {
        return mapper.restoreJobById(jobModel);
    }
}

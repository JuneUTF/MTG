package com.juneutf.mtg.mapper;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Mapper;

import com.juneutf.mtg.model.JobModel;

/**
 * このインターフェースは、JobModel オブジェクトに関連するデータベース操作を定義します。
 */
@Mapper
public interface JobMapper {

    /**
     * 指定された ID に対応する JobModel オブジェクトを取得します。
     *
     * @param id 取得する JobModel の ID
     * @return ID に対応する JobModel オブジェクトのリスト
     */
    ArrayList<JobModel> selectJobById(int id);

    /**
     * 指定された JobModel オブジェクトの情報を更新します。
     *
     * @param jobModel 更新する JobModel オブジェクト
     * @return 更新が成功した場合は 1 以上の整数値、失敗した場合は 0
     */
    int updateJobById(JobModel jobModel);

    /**
     * 指定された JobModel オブジェクトの情報を削除します。
     *
     * @param jobModel 削除する JobModel オブジェクト
     * @return 削除が成功した場合は 1 以上の整数値、失敗した場合は 0
     */
    int deleteJobById(JobModel jobModel);

    /**
     * 削除された JobModel オブジェクトを復元します。
     *
     * @param jobModel 復元する JobModel オブジェクト
     * @return 復元が成功した場合は 1 以上の整数値、失敗した場合は 0
     */
    int restoreJobById(JobModel jobModel);

    /**
     * すべての JobModel オブジェクトを取得します。
     *
     * @return すべての JobModel オブジェクトのリスト
     */
    ArrayList<JobModel> selectJob();
}

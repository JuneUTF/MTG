package com.juneutf.mtg.service;

import java.util.ArrayList;

import com.juneutf.mtg.model.JobModel;

/**
 * JobService インターフェースは、内容(内容)に関連するサービスメソッドを提供します。
 */
public interface JobService {
    
    /**
     * 指定されたIDに対応する内容(内容)(内容)を取得します。
     *
     * @param id 取得する内容(内容)のID
     * @return 指定されたIDに対応する内容(内容)のリスト
     */
    ArrayList<JobModel> selectJobById(int id);
    
    /**
     * 内容(内容)を更新します。
     *
     * @param jobModel 更新する内容(内容)のモデル
     * @return 内容(内容)が正常に更新された場合は1、それ以外の場合は0を返します。
     */
    int updateJobById(JobModel jobModel);
    
    /**
     * 内容(内容)を削除します。
     *
     * @param jobModel 削除する内容(内容)のモデル
     * @return 内容(内容)が正常に削除された場合は1、それ以外の場合は0を返します。
     */
    int deleteJobById(JobModel jobModel);
    
    /**
     * 内容(内容)を復元します。
     *
     * @param jobModel 復元する内容(内容)のモデル
     * @return 内容(内容)が正常に復元された場合は1、それ以外の場合は0を返します。
     */
    int restoreJobById(JobModel jobModel);
}


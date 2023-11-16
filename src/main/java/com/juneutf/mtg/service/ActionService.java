package com.juneutf.mtg.service;

/**
 * ActionService インターフェースは、アクションに関するサービスメソッドを提供します。
 */
public interface ActionService {
    
    /**
     * 指定されたIDに対応する完成状況を更新します。
     *
     * @param id 更新するアクションのID
     * @return アクションが正常に更新された場合は1、それ以外の場合は0を返します。
     */
    int actionUpdateById(int id);
}

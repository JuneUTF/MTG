package com.juneutf.mtg.mapper;

import org.apache.ibatis.annotations.Mapper;

/**
 * ActionMapper インタフェースは、アクションに関するデータベース操作を提供します。
 */
@Mapper
public interface ActionMapper {
    /**
     * 指定されたIDに対応するアクションをデータベースで更新します。
     *
     * @param id 更新対象のアクションのID
     * @return 更新が成功した場合は 1 を返し、失敗した場合は 0 を返します。
     */
    int actionUpdateById(int id);
}

package com.juneutf.mtg.iml;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.juneutf.mtg.mapper.ActionMapper;
import com.juneutf.mtg.service.ActionService;

/**
 * ActionService インターフェースの実装クラスです。
 * このクラスはSpringのServiceとしてマークされています。
 */
@Service
public class ActionImpl implements ActionService {
    @Autowired
    private ActionMapper mapper;

    /**
     * 指定されたIDに対応するアクションの更新を行います。
     *
     * @param id 更新対象のアクションのID
     * @return 更新が成功した場合は1、失敗した場合は0を返します。
     */
    @Override
    public int actionUpdateById(int id) {
        return mapper.actionUpdateById(id);
    }
}

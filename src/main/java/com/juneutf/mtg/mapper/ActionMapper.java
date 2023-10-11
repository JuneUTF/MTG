package com.juneutf.mtg.mapper;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ActionMapper {
	int actionUpdateById(int id);
}

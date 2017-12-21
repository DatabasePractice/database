package webproject.mapper;

import org.apache.ibatis.annotations.Mapper;

import webproject.model.PageData;
@Mapper
public interface ChargeMapper {
	void insert(PageData pd);
}

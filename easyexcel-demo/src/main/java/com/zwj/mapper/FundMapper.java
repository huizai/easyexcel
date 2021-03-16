package com.zwj.mapper;
import java.util.List;

import org.apache.ibatis.annotations.Select;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zwj.entity.Fund;


public interface FundMapper extends BaseMapper<Fund> {
	 @Select("select  distinct (fundcode) from fund")
	 List<String> selectDistinctFundCodeList();

}
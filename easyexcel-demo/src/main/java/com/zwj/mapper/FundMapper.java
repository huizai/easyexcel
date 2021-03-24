package com.zwj.mapper;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Select;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zwj.entity.Fund;


public interface FundMapper extends BaseMapper<Fund> {
	 @Select("select  distinct (fundcode) from fund")
	 List<String> selectDistinctFundCodeList();


	@Select("select  distinct (fundcode) , fundname from fund")
	List<Map<String,String>> selectDistinctFundCodeNameList();

}
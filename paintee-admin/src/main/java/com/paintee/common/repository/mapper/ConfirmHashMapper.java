package com.paintee.common.repository.mapper;

import com.paintee.common.repository.entity.ConfirmHash;
import com.paintee.common.repository.entity.ConfirmHashExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ConfirmHashMapper {
    int countByExample(ConfirmHashExample example);

    int deleteByExample(ConfirmHashExample example);

    int deleteByPrimaryKey(Integer seq);

    int insert(ConfirmHash record);

    int insertSelective(ConfirmHash record);

    List<ConfirmHash> selectByExample(ConfirmHashExample example);

    ConfirmHash selectByPrimaryKey(Integer seq);

    int updateByExampleSelective(@Param("record") ConfirmHash record, @Param("example") ConfirmHashExample example);

    int updateByExample(@Param("record") ConfirmHash record, @Param("example") ConfirmHashExample example);

    int updateByPrimaryKeySelective(ConfirmHash record);

    int updateByPrimaryKey(ConfirmHash record);
}
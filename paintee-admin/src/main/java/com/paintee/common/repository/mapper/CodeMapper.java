package com.paintee.common.repository.mapper;

import com.paintee.common.repository.entity.Code;
import com.paintee.common.repository.entity.CodeExample;
import com.paintee.common.repository.entity.CodeKey;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface CodeMapper {
    int countByExample(CodeExample example);

    int deleteByExample(CodeExample example);

    int deleteByPrimaryKey(CodeKey key);

    int insert(Code record);

    int insertSelective(Code record);

    List<Code> selectByExample(CodeExample example);

    Code selectByPrimaryKey(CodeKey key);

    int updateByExampleSelective(@Param("record") Code record, @Param("example") CodeExample example);

    int updateByExample(@Param("record") Code record, @Param("example") CodeExample example);

    int updateByPrimaryKeySelective(Code record);

    int updateByPrimaryKey(Code record);
}
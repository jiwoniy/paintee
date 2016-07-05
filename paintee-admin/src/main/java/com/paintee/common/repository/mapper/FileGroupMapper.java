package com.paintee.common.repository.mapper;

import com.paintee.common.repository.entity.FileGroup;
import com.paintee.common.repository.entity.FileGroupExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface FileGroupMapper {
    int countByExample(FileGroupExample example);

    int deleteByExample(FileGroupExample example);

    int deleteByPrimaryKey(Long seq);

    int insert(FileGroup record);

    int insertSelective(FileGroup record);

    List<FileGroup> selectByExample(FileGroupExample example);

    FileGroup selectByPrimaryKey(Long seq);

    int updateByExampleSelective(@Param("record") FileGroup record, @Param("example") FileGroupExample example);

    int updateByExample(@Param("record") FileGroup record, @Param("example") FileGroupExample example);

    int updateByPrimaryKeySelective(FileGroup record);

    int updateByPrimaryKey(FileGroup record);
}
package com.paintee.common.repository.mapper;

import com.paintee.common.repository.entity.TuesdayPaintingPost;
import com.paintee.common.repository.entity.TuesdayPaintingPostExample;
import com.paintee.common.repository.entity.TuesdayPaintingPostKey;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TuesdayPaintingPostMapper {
    int countByExample(TuesdayPaintingPostExample example);

    int deleteByExample(TuesdayPaintingPostExample example);

    int deleteByPrimaryKey(TuesdayPaintingPostKey key);

    int insert(TuesdayPaintingPost record);

    int insertSelective(TuesdayPaintingPost record);

    List<TuesdayPaintingPost> selectByExample(TuesdayPaintingPostExample example);

    TuesdayPaintingPost selectByPrimaryKey(TuesdayPaintingPostKey key);

    int updateByExampleSelective(@Param("record") TuesdayPaintingPost record, @Param("example") TuesdayPaintingPostExample example);

    int updateByExample(@Param("record") TuesdayPaintingPost record, @Param("example") TuesdayPaintingPostExample example);

    int updateByPrimaryKeySelective(TuesdayPaintingPost record);

    int updateByPrimaryKey(TuesdayPaintingPost record);
}
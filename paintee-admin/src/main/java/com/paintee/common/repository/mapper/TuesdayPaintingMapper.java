package com.paintee.common.repository.mapper;

import com.paintee.common.repository.entity.TuesdayPainting;
import com.paintee.common.repository.entity.TuesdayPaintingExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TuesdayPaintingMapper {
    int countByExample(TuesdayPaintingExample example);

    int deleteByExample(TuesdayPaintingExample example);

    int deleteByPrimaryKey(Integer seq);

    int insert(TuesdayPainting record);

    int insertSelective(TuesdayPainting record);

    List<TuesdayPainting> selectByExample(TuesdayPaintingExample example);

    TuesdayPainting selectByPrimaryKey(Integer seq);

    int updateByExampleSelective(@Param("record") TuesdayPainting record, @Param("example") TuesdayPaintingExample example);

    int updateByExample(@Param("record") TuesdayPainting record, @Param("example") TuesdayPaintingExample example);

    int updateByPrimaryKeySelective(TuesdayPainting record);

    int updateByPrimaryKey(TuesdayPainting record);
}
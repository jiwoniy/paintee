package com.paintee.common.repository.mapper;

import com.paintee.common.repository.entity.Painting;
import com.paintee.common.repository.entity.PaintingExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface PaintingMapper {
    int countByExample(PaintingExample example);

    int deleteByExample(PaintingExample example);

    int deleteByPrimaryKey(Integer seq);

    int insert(Painting record);

    int insertSelective(Painting record);

    List<Painting> selectByExample(PaintingExample example);

    Painting selectByPrimaryKey(Integer seq);

    int updateByExampleSelective(@Param("record") Painting record, @Param("example") PaintingExample example);

    int updateByExample(@Param("record") Painting record, @Param("example") PaintingExample example);

    int updateByPrimaryKeySelective(Painting record);

    int updateByPrimaryKey(Painting record);
}
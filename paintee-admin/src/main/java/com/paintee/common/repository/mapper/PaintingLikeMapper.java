package com.paintee.common.repository.mapper;

import com.paintee.common.repository.entity.PaintingLike;
import com.paintee.common.repository.entity.PaintingLikeExample;
import com.paintee.common.repository.entity.PaintingLikeKey;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface PaintingLikeMapper {
    int countByExample(PaintingLikeExample example);

    int deleteByExample(PaintingLikeExample example);

    int deleteByPrimaryKey(PaintingLikeKey key);

    int insert(PaintingLike record);

    int insertSelective(PaintingLike record);

    List<PaintingLike> selectByExample(PaintingLikeExample example);

    PaintingLike selectByPrimaryKey(PaintingLikeKey key);

    int updateByExampleSelective(@Param("record") PaintingLike record, @Param("example") PaintingLikeExample example);

    int updateByExample(@Param("record") PaintingLike record, @Param("example") PaintingLikeExample example);

    int updateByPrimaryKeySelective(PaintingLike record);

    int updateByPrimaryKey(PaintingLike record);
}
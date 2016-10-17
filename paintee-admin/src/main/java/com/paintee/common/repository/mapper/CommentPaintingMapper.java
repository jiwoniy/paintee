package com.paintee.common.repository.mapper;

import com.paintee.common.repository.entity.CommentPainting;
import com.paintee.common.repository.entity.CommentPaintingExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface CommentPaintingMapper {
    int countByExample(CommentPaintingExample example);

    int deleteByExample(CommentPaintingExample example);

    int deleteByPrimaryKey(Integer seq);

    int insert(CommentPainting record);

    int insertSelective(CommentPainting record);

    List<CommentPainting> selectByExample(CommentPaintingExample example);

    CommentPainting selectByPrimaryKey(Integer seq);

    int updateByExampleSelective(@Param("record") CommentPainting record, @Param("example") CommentPaintingExample example);

    int updateByExample(@Param("record") CommentPainting record, @Param("example") CommentPaintingExample example);

    int updateByPrimaryKeySelective(CommentPainting record);

    int updateByPrimaryKey(CommentPainting record);
}
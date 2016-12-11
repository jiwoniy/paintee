package com.paintee.common.repository.mapper;

import com.paintee.common.repository.entity.PromotionCode;
import com.paintee.common.repository.entity.PromotionCodeExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface PromotionCodeMapper {
    int countByExample(PromotionCodeExample example);

    int deleteByExample(PromotionCodeExample example);

    int deleteByPrimaryKey(Integer seq);

    int insert(PromotionCode record);

    int insertSelective(PromotionCode record);

    List<PromotionCode> selectByExample(PromotionCodeExample example);

    PromotionCode selectByPrimaryKey(Integer seq);

    int updateByExampleSelective(@Param("record") PromotionCode record, @Param("example") PromotionCodeExample example);

    int updateByExample(@Param("record") PromotionCode record, @Param("example") PromotionCodeExample example);

    int updateByPrimaryKeySelective(PromotionCode record);

    int updateByPrimaryKey(PromotionCode record);
}

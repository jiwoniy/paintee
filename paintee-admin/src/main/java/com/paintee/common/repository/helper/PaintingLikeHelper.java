package com.paintee.common.repository.helper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.paintee.common.repository.entity.vo.LikeUserVO;
import com.paintee.common.repository.mapper.PaintingLikeMapper;

public interface PaintingLikeHelper extends PaintingLikeMapper {
	/**
	 @fn selectLikeUserList
	 @brief 함수 간략한 설명 : 그림의 구매 정보를 업데이트
	 @remark
	 - 함수의 상세 설명 : 그림의 구매 카운트 및 구매자 카운트 정보를 증가시킨다.
	 @param paintingId 
	 @param userId 
	*/
	public List<LikeUserVO> selectLikeUserList(@Param("paintingId") String paintingId, @Param("userId") String userId);
}
/**
@file FollowService.java
@section 파일생성정보
|    항  목       |      내  용       |
| :-------------: | -------------   |
| File name | FollowService.java |    
| Package | com.paintee.mobile.follow.service |    
| Project name | paintee-admin |    
| Type name | FollowService |    
| Company | Paintee | 
| Create Date | 2016 2016. 3. 4. 오후 11:24:07 |
| Author | Administrator |
| File Version | v1.0 |
*/
package com.paintee.mobile.follow.service;

import java.util.List;
import java.util.Map;

import com.paintee.common.repository.entity.Follow;
import com.paintee.common.repository.entity.vo.FollowSearchVO;
import com.paintee.common.repository.entity.vo.FollowVO;

/**
@class FollowService
com.paintee.mobile.follow.service \n
   ㄴ FollowService.java
 @section 클래스작성정보
    |    항  목       |      내  용       |
    | :-------------: | -------------   |
    | Company | Paintee |
    | Author | Administrator |
    | Date | 2016. 3. 4. 오후 11:24:07 |
    | Class Version | v1.0 |
    | 작업자 | Administrator |
 @section 상세설명
 - follow service
*/
public interface FollowService {

	/**
	 @fn getFollowPaintingInfo
	 @brief 함수 간략한 설명 : 인덱스 페이지의 follow 정보를 조회
	 @remark
	 - 함수의 상세 설명 : 로그인한 사용자의 following 한 사용자들의 업로드 및 구매 그림 정보를 조회한다.
	 @param search
	 @return 
	*/
	public Map<String, Object> getFollowPaintingInfo(FollowSearchVO search);

	/**
	 @fn getFollowCount
	 @brief 함수 간략한 설명 : 팔로잉과 팔로워의 카운트
	 @remark
	 - 함수의 상세 설명 : 로그인 사용자의 팔로잉과 팔로워의 카운트를 조회한다.
	 @param search
	 @return 
	*/
	public FollowVO getFollowCount(FollowSearchVO search);

	/**
	 @fn getFollowsList
	 @brief 함수 간략한 설명 : 로그인한 사용자를 팔로우한 사용자 목록 
	 @remark
	 - 함수의 상세 설명 :  로그인한 사용자를 팔로우한 사용자 목록
	 @param search
	 @return 
	*/
	public List<FollowVO> getFollowsList(FollowSearchVO search);
	
	/**
	 @fn getFollowsList
	 @brief 함수 간략한 설명 : 로그인한 사용자가 팔로우한 사용자 목록 
	 @remark
	 - 함수의 상세 설명 :  로그인한 사용자를 팔로우한 사용자 목록
	 @param search
	 @return 
	 */
	public List<FollowVO> getFollowingList(FollowSearchVO search);

	/**
	 @fn addFollows
	 @brief 함수 간략한 설명 : 로그인 사용자의 follow를 추가
	 @remark
	 - 함수의 상세 설명 : 로그인한 사용자의 follow를 추가
	 @param search 
	*/
	public void addFollows(Follow follow);

	/**
	 @fn delFollows
	 @brief 함수 간략한 설명 : 로그인 사용자의 follow 대상자를 삭제
	 @remark
	 - 함수의 상세 설명 : 로그인 사용자의 follow 대상자를 삭제
	 @param follow 
	*/
	public void delFollows(Follow follow);
}

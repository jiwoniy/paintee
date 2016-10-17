/**
@file CommentService.java
@section 파일생성정보
|    항  목       |      내  용       |
| :-------------: | -------------   |
| File name | CommentService.java |    
| Package | com.paintee.mobile.comment.service |    
| Project name | paintee-admin |    
| Type name | CommentService |    
| Company | Paintee | 
| Create Date | 2016 2016. 10. 9. 오후 9:50:36 |
| Author | Administrator |
| File Version | v1.0 |
*/
package com.paintee.mobile.comment.service;

import com.paintee.common.repository.entity.CommentPainting;
import com.paintee.mobile.support.obejct.LoginedUserVO;

/**
@class CommentService
com.paintee.mobile.comment.service \n
   ㄴ CommentService.java
 @section 클래스작성정보
    |    항  목       |      내  용       |
    | :-------------: | -------------   |
    | Company | Paintee |
    | Author | Administrator |
    | Date | 2016. 10. 9. 오후 9:50:36 |
    | Class Version | v1.0 |
    | 작업자 | Administrator |
 @section 상세설명
 - 그림 코멘트에 대한 service
*/
public interface CommentService {

	/**
	 @fn createCommentPainting
	 @brief 함수 간략한 설명 : 그림 코멘트 정보 생성
	 @remark
	 - 함수의 상세 설명 : 그림 코멘트 정보 생성
	 @param commentPainting
	 @param loginedUserVO
	 @return 
	 @throws Exception 
	*/
	public CommentPainting createCommentPainting(CommentPainting commentPainting, LoginedUserVO loginedUserVO) throws Exception;
}

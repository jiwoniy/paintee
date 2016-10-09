/**
@file CommentServiceImpl.java
@section 파일생성정보
|    항  목       |      내  용       |
| :-------------: | -------------   |
| File name | CommentServiceImpl.java |    
| Package | com.paintee.mobile.comment.service |    
| Project name | paintee-admin |    
| Type name | CommentServiceImpl |    
| Company | Paintee | 
| Create Date | 2016 2016. 10. 9. 오후 9:50:36 |
| Author | Administrator |
| File Version | v1.0 |
*/
package com.paintee.mobile.comment.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.paintee.common.repository.entity.CommentPainting;
import com.paintee.common.repository.entity.User;
import com.paintee.common.repository.helper.CommentPaintingHelper;
import com.paintee.common.repository.helper.UserHelper;
import com.paintee.mobile.support.obejct.LoginedUserVO;

/**
@class CommentServiceImpl
com.paintee.mobile.painting.service \n
   ㄴ CommentServiceImpl.java
 @section 클래스작성정보
    |    항  목       |      내  용       |
    | :-------------: | -------------   |
    | Company | Paintee |
    | Author | Administrator |
    | Date | 2016. 3. 2. 오후 10:59:36 |
    | Class Version | v1.0 |
    | 작업자 | Administrator |
 @section 상세설명
 - 그림 코멘트에 대한 service 구현채
*/
@Service(value="com.paintee.mobile.comment.service.CommentServiceImpl")
public class CommentServiceImpl implements CommentService {
	@Autowired
	private CommentPaintingHelper commentPaintingHelper;
	
	@Autowired
	private UserHelper userHelper;

	/* (non-Javadoc)
	 * @see com.paintee.mobile.comment.service.CommentService#createCommentPainting(com.paintee.common.repository.entity.CommentPainting, com.paintee.mobile.support.obejct.LoginedUserVO)
	 */
	@Transactional
	public CommentPainting createCommentPainting(CommentPainting commentPainting, LoginedUserVO loginedUserVO) throws Exception {
		commentPainting.setUserId(loginedUserVO.getUserId());
		commentPainting.setCreatedDate(new Date());

		commentPaintingHelper.insert(commentPainting);

		User updateCommentCount = new User();
		updateCommentCount.setUserId(loginedUserVO.getUserId());

		userHelper.updateByPrimaryKeySelective(updateCommentCount);

		return commentPainting;
	}
}

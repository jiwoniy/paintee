/**
@file TestServiceImpl.java
@section 파일생성정보
|    항  목       |      내  용       |
| :-------------: | -------------   |
| File name | TestServiceImpl.java |    
| Package | com.paintee.admin.test.service |    
| Project name | paintee-admin |    
| Type name | TestServiceImpl |    
| Company | Paintee | 
| Create Date | 2016 2016. 2. 27. 오후 6:16:52 |
| Author | Administrator |
| File Version | v1.0 |
*/
package com.paintee.admin.test.service;

import org.springframework.stereotype.Service;

/**
@class TestServiceImpl
com.paintee.admin.test.service \n
   ㄴ TestServiceImpl.java
 @section 클래스작성정보
    |    항  목       |      내  용       |
    | :-------------: | -------------   |
    | Company | Paintee |
    | Author | Administrator |
    | Date | 2016. 2. 27. 오후 6:16:52 |
    | Class Version | v1.0 |
    | 작업자 | Administrator |
 @section 상세설명
 - 상세설명 은 여기에 기입해 주세요.
 -# 여기는 리스트로 표시됩니다.
*/
@Service(value="com.paintee.admin.test.service.TestServiceImpl")
public class TestServiceImpl implements TestService {
//	@Autowired
//	private FollowHelper followHelper;

	/**
	 @fn 
	 @brief (Override method) 함수 간략한 설명 :
	 @remark
	 - 오버라이드 함수의 상세 설명 : 
	 @see com.paintee.admin.test.service.TestService#totalCount()
	*/
	public int totalCount() {
		//Example 사용예제
//		FollowExample followExample = new FollowExample();
//		FollowExample.Criteria where = followExample.createCriteria();
//		followHelper.selectByExample(followExample);

//		return followHelper.selectTotalCount();
		return 1;
	}
}

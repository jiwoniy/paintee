/**
@file ConfirmHashHelper.java
@section 파일생성정보
|    항  목       |      내  용       |
| :-------------: | -------------   |
| File name | ConfirmHashHelper.java |    
| Package | com.paintee.common.repository.helper |    
| Project name | paintee-admin |    
| Type name | ConfirmHashHelper |    
| Company | Paintee | 
| Create Date | 2016 2016. 3. 13. 오후 5:41:47 |
| Author | Administrator |
| File Version | v1.0 |
*/
package com.paintee.common.repository.helper;

import com.paintee.common.repository.mapper.ConfirmHashMapper;

/**
@class ConfirmHashHelper
com.paintee.common.repository.helper \n
   ㄴ ConfirmHashHelper.java
 @section 클래스작성정보
    |    항  목       |      내  용       |
    | :-------------: | -------------   |
    | Company | Paintee |
    | Author | Administrator |
    | Date | 2016. 3. 13. 오후 5:41:47 |
    | Class Version | v1.0 |
    | 작업자 | Administrator |
 @section 상세설명
 - 회원가입 후 계정활성화를 위한 hash 정보 helper
*/
public interface ConfirmHashHelper extends ConfirmHashMapper {

}

/**
@file PromotionCodeHelper.java
@section 파일생성정보
|    항  목       |      내  용       |
| :-------------: | -------------   |
| File name | PromotionCodeHelper.java |    
| Package | com.paintee.common.repository.helper |    
| Project name | paintee-admin |    
| Type name | PromotionCodeHelper |    
| Company | Paintee | 
| Create Date | 2016 2016. 3. 3. 오후 11:09:41 |
| Author | Administrator |
| File Version | v1.0 |
*/
package com.paintee.common.repository.helper;

import java.util.List;

import com.paintee.common.repository.entity.PromotionCode;
import com.paintee.common.repository.entity.User;
import com.paintee.common.repository.entity.vo.PromotionCodeSearchVO;
import com.paintee.common.repository.entity.vo.PromotionCodeVO;
import com.paintee.common.repository.entity.vo.PurchaseSearchVO;
import com.paintee.common.repository.mapper.PromotionCodeMapper;

/**
@class PromotionCodeHelper
com.paintee.common.repository.helper \n
   ㄴ PromotionCodeHelper.java
 @section 클래스작성정보
    |    항  목      |      내  용      |
    | :-----------: | -------------  |
    |   Company     |       Paintee  |
    | Author | Administrator |
    | Date | 2016. 12. 3. 오후 11:09:41 |
    | Class Version | v1.0 |
    | 작업자 | Yeuntaek |
 @section 상세설명
 - 프로모션 코드 관련 helper
*/
public interface PromotionCodeHelper extends PromotionCodeMapper {

	public void updatePromotionCodeInfo(PromotionCode code);
	
	public List<PromotionCodeVO>  selectPromotionCodeList(PromotionCodeSearchVO search);

	public Integer selectPromotionCodeListCount(PromotionCodeSearchVO search);
}

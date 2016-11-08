/**
@file BaseObject.java
@section 파일생성정보
|    항  목       |      내  용       |
| :-------------: | -------------   |
| File name | BaseObject.java |    
| Package | com.paintee.common.object |    
| Project name | paintee-admin |    
| Type name | BaseObject |    
| Company | Paintee | 
| Create Date | 2016 2016. 3. 9. 오후 9:30:12 |
| Author | Administrator |
| File Version | v1.0 |
*/
package com.paintee.common.object;

import java.io.Serializable;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
@class BaseObject
com.paintee.common.object \n
   ㄴ BaseObject.java
 @section 클래스작성정보
    |    항  목       |      내  용       |
    | :-------------: | -------------   |
    | Company | Paintee |
    | Author | Administrator |
    | Date | 2016. 3. 9. 오후 9:30:12 |
    | Class Version | v1.0 |
    | 작업자 | Administrator |
 @section 상세설명
 - 최상위 object
*/
public class BaseObject implements Serializable {
	private static final long serialVersionUID = -7755819761913855999L;

	public String toString() {
		return ToStringBuilder.reflectionToString(this,	ToStringStyle.MULTI_LINE_STYLE);
	}

	public boolean equals(Object o) {
		return EqualsBuilder.reflectionEquals(this, o);
	}

	public int hashCode() {
		return HashCodeBuilder.reflectionHashCode(this);
	}
}

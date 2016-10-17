/**
@file FilePathGenerator.java
@section 파일생성정보
|    항  목       |      내  용       |
| :-------------: | -------------   |
| File name | FilePathGenerator.java |    
| Package | com.paintee.common.file.service |    
| Project name | paintee-admin |    
| Type name | FilePathGenerator |    
| Company | Paintee | 
| Create Date | 2016 2016. 3. 1. 오후 11:03:56 |
| Author | Administrator |
| File Version | v1.0 |
*/
package com.paintee.common.file.service;

import java.util.Calendar;
import java.util.Date;

import org.apache.commons.lang3.time.FastDateFormat;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
@class FilePathGenerator
com.paintee.common.file.service \n
   ㄴ FilePathGenerator.java
 @section 클래스작성정보
    |    항  목       |      내  용       |
    | :-------------: | -------------   |
    | Company | Paintee |
    | Author | Administrator |
    | Date | 2016. 3. 1. 오후 11:03:56 |
    | Class Version | v1.0 |
    | 작업자 | Administrator |
 @section 상세설명
 - 파일 경로 생성기
*/
@Component(value="com.paintee.common.file.service.FilePathGenerator")
public class FilePathGenerator {
	private final static Logger logger = LoggerFactory.getLogger(FilePathGenerator.class);

	@Value("#{config['common.upload.file.baseLocation'] }")
	private String baseLocation;

	@Value("#{config['common.upload.file.fileSeparator'] }")
	private String fileSeparator;

	/**
	 * @return
	 * @fn generateFilPath
	 * @brief 함수 간략한 설명 : 년월일에 해당하는 file path 생성
	 * @remark - 함수의 상세 설명 : 년월일에 해당하는 file path 를 생성한다. 예) d:/baselocation/2016/02/17/
	 */
	public String generateFilPath() {
		
		return generateFilPath(null);
	}

	/**
	 * @param middlePath
	 * @return
	 * @fn generateFilPath
	 * @brief 함수 간략한 설명 : 파일 저장 경로 생성(baselocation 제외)
	 * @remark - 함수의 상세 설명 : 파일 저장 경로 생성(baselocation 제외)
	 */
	public String generateFilPath(String middlePath) {
		StringBuilder filePath = new StringBuilder();

		Date today = new Date();

//		filePath.append(baseLocation).append(fileSeparator);
//		filePath.append(fileSeparator);

		try {
			if (middlePath != null && middlePath.trim().length() > 0)
				filePath.append(middlePath).append(fileSeparator);

			filePath.append(getFormatDate(today, "yyyy")).append(fileSeparator);
			filePath.append(getFormatDate(today, "MM")).append(fileSeparator);
			filePath.append(getFormatDate(today, "dd")).append(fileSeparator);
		} catch (Exception e) {
			logger.error("Exception: [\n{}\n]", e);
		}

		return filePath.toString();
	}

	/**
	 @fn getAbsoluteFilPath
	 @brief 함수 간략한 설명 : baselocation 을 포함하여 첨부파일 절대 경로 생성
	 @remark
	 - 함수의 상세 설명 : baselocation 을 포함하여 첨부파일 절대 경로를 생성한다.
	 @param filePath
	 @return 
	*/
	public String getAbsoluteFilPath(String filePath) {
		StringBuilder absoluteFilePath = new StringBuilder();
		absoluteFilePath.append(baseLocation).append(filePath);
		
		return absoluteFilePath.toString();
	}

	public String getFormatDate(Date dateObj, String pattern) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(dateObj);

		FastDateFormat fdf = FastDateFormat.getInstance(pattern);

		return fdf.format(cal);
	}
}

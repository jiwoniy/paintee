/**
@file UnauthorizedException.java
@section 파일생성정보
|	항  목	   |	  내  용	   |
| :-------------: | -------------   |
| File name | UnauthorizedException.java |	
| Package | com.paintee.mobile.support.exception |	
| Project name | paintee-admin |	
| Type name | UnauthorizedException |	
| Company | Paintee | 
| Create Date | 2016 2016. 3. 8. 오후 12:49:18 |
| Author | Administrator |
| File Version | v1.0 |
*/
package com.paintee.mobile.support.exception;

/**
@class UnauthorizedException
com.paintee.mobile.support.exception \n
   ㄴ UnauthorizedException.java
 @section 클래스작성정보
	|	항  목	   |	  내  용	   |
	| :-------------: | -------------   |
	| Company | Paintee |
	| Author | Administrator |
	| Date | 2016. 3. 8. 오후 12:49:18 |
	| Class Version | v1.0 |
	| 작업자 | Administrator |
 @section 상세설명
 - 인증오류
*/
public class UnauthorizedException extends Exception {
	private static final long serialVersionUID = 1087575476711344661L;

	/**
	@brief 결과 코드
	*/
	private String errorNo;
	/**
	@brief 결과 메세지
	*/
	private String errorMsg;

	/**
	 @fn 
	 @brief 생성자 간략 설명 : 
	 @remark
	 - 생성자 상세 설명 : 
	 @param errorNo 
	~~~~~~~~~~~~~{.java}
	 // 핵심코드
	~~~~~~~~~~~~~
	*/
	public UnauthorizedException(String errorNo) {
		this.errorNo = errorNo;
	}

	/**
	 @fn 
	 @brief 생성자 간략 설명 : 
	 @remark
	 - 생성자 상세 설명 : 
	 @param errorNo
	 @param errorMsg 
	~~~~~~~~~~~~~{.java}
	 // 핵심코드
	~~~~~~~~~~~~~
	*/
	public UnauthorizedException(String errorNo, String errorMsg) {
		super(errorMsg);
		this.errorNo = errorNo;
		this.errorMsg = errorMsg;
	}

	/**
	 @fn 
	 @brief 생성자 간략 설명 : 
	 @remark
	 - 생성자 상세 설명 : 
	 @param errorNo
	 @param cause 
	~~~~~~~~~~~~~{.java}
	 // 핵심코드
	~~~~~~~~~~~~~
	*/
	public UnauthorizedException(String errorNo, Throwable cause) {
		super(cause);
		this.errorNo = errorNo;
	}

	/**
	 @fn 
	 @brief 생성자 간략 설명 : 
	 @remark
	 - 생성자 상세 설명 : 
	 @param errorNo
	 @param errorMsg
	 @param cause 
	~~~~~~~~~~~~~{.java}
	 // 핵심코드
	~~~~~~~~~~~~~
	*/
	public UnauthorizedException(String errorNo, String errorMsg, Throwable cause) {
		super(errorMsg, cause);
		this.errorNo = errorNo;
		this.errorMsg = errorMsg;
	}

	/**
	 @fn 
	 @brief 생성자 간략 설명 : 
	 @remark
	 - 생성자 상세 설명 : 
	 @param cause 
	~~~~~~~~~~~~~{.java}
	 // 핵심코드
	~~~~~~~~~~~~~
	*/
	public UnauthorizedException(Throwable cause) {
		super(cause);
	}

	/**
	 @fn 
	 @brief 생성자 간략 설명 : 
	 @remark
	 - 생성자 상세 설명 : 
	 @param message
	 @param cause
	 @param enableSuppression
	 @param writableStackTrace 
	~~~~~~~~~~~~~{.java}
	 // 핵심코드
	~~~~~~~~~~~~~
	*/
	protected UnauthorizedException(String message, Throwable cause,
							  boolean enableSuppression,
							  boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	/**
	 @fn getErrorNo
	 @brief 함수 간략한 설명 : 결과 코드 조회
	 @remark
	 - 함수의 상세 설명 : 결과 코드 조회
	 @return 
	*/
	public String getErrorNo() {
		return errorNo;
	}

	/**
	 @fn setErrorNo
	 @brief 함수 간략한 설명 : 결과 코드 입력
	 @remark
	 - 함수의 상세 설명 : 결과 코드 입력
	 @param resultNo 
	*/
	public void setErrorNo(String errorNo) {
		this.errorNo = errorNo;
	}

	/**
	 @fn getErrorMsg
	 @brief 함수 간략한 설명 : 결과 메세지 입력
	 @remark
	 - 함수의 상세 설명 : 결과 메세지 입력
	 @return 
	*/
	public String getErrorMsg() {
		return errorMsg;
	}

	/**
	 @fn setErrorMsg
	 @brief 함수 간략한 설명 : 결과 메세지 입력
	 @remark
	 - 함수의 상세 설명 : 결과 메세지 입력
	 @param errorMsg 
	*/
	public void setErrorMsg(String errorMsg) {
		this.errorMsg = errorMsg;
	}
}

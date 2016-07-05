/**
@file LoginServiceImpl.java
@section 파일생성정보
|    항  목       |      내  용       |
| :-------------: | -------------   |
| File name | LoginServiceImpl.java |    
| Package | com.paintee.mobile.auth.service |    
| Project name | paintee-admin |    
| Type name | LoginServiceImpl |    
| Company | Paintee | 
| Create Date | 2016 2016. 3. 6. 오후 2:03:13 |
| Author | Administrator |
| File Version | v1.0 |
*/
package com.paintee.mobile.auth.service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.paintee.common.mail.HtmlContentBuilder;
import com.paintee.common.mail.MailService;
import com.paintee.common.repository.entity.Login;
import com.paintee.common.repository.entity.LoginExample;
import com.paintee.common.repository.entity.User;
import com.paintee.common.repository.entity.UserExample;
import com.paintee.common.repository.entity.vo.UserLoginVO;
import com.paintee.common.repository.helper.LoginHelper;
import com.paintee.common.repository.helper.UserHelper;
import com.paintee.common.util.PasswordGenerator;
import com.paintee.common.util.Sha512Encrypt;

/**
@class LoginServiceImpl
com.paintee.mobile.auth.service \n
   ㄴ LoginServiceImpl.java
 @section 클래스작성정보
    |    항  목       |      내  용       |
    | :-------------: | -------------   |
    | Company | Paintee |
    | Author | Administrator |
    | Date | 2016. 3. 6. 오후 2:03:13 |
    | Class Version | v1.0 |
    | 작업자 | Administrator |
 @section 상세설명
 - 사용자 인증관련 service 구현채
*/
@Service(value="com.paintee.mobile.auth.service.LoginServiceImpl")
public class LoginServiceImpl implements LoginService {
	private final static Logger logger = LoggerFactory.getLogger(LoginServiceImpl.class);

	@Autowired
	private UserHelper userHelper;
	
	@Autowired
	private LoginHelper loginHelper;

	@Value("#{config['common.login.hash.expireDay'] }")
	private int expireDay;

	@Autowired
	private PasswordGenerator passwordGenerator;

	@Autowired
	private HtmlContentBuilder htmlContentBuilder;

	@Autowired
	private MailService mailService;

	/**
	 @fn 
	 @brief (Override method) 함수 간략한 설명 : 사용자 인증
	 @remark
	 - 오버라이드 함수의 상세 설명 : email 과 passwod 를 사용하여 사용자 인증을 한다.
	 @see com.paintee.mobile.auth.service.LoginService#login(com.paintee.common.repository.entity.vo.UserLoginVO)
	*/
	public Map<String, Object> login(UserLoginVO userLoginVO) {
		Map<String, Object> resultMap = new HashMap<>();

		//사용자 정보 조회
		UserExample userExamplem = new UserExample();
		UserExample.Criteria where = userExamplem.createCriteria();
		where.andEmailEqualTo(userLoginVO.getEmail());
		where.andPasswordEqualTo(Sha512Encrypt.hash(userLoginVO.getPassword()));
		where.andProviderIdEqualTo("PAINTEE");
		where.andUserStatusEqualTo("N");

		List<User> userList = userHelper.selectByExample(userExamplem);

		if(userList == null || userList.size() == 0) {
			resultMap.put("errorNo", 404);
		} else {
			User userInfo = userList.get(0);

			DateTime toDate = new DateTime();
			DateTime expireDate = toDate.plusDays(expireDay);

			String loginWord = userInfo.getUserId()+userLoginVO.getAccessGubun()+expireDate.getMillis();

			Login login = new Login();
			login.setUserId(userInfo.getUserId());
			login.setAccessGubun(userLoginVO.getAccessGubun());
			login.setHash(Sha512Encrypt.hash(loginWord));
			login.setExpireDate(expireDate.toDate());

			loginHelper.insert(login);
			logger.debug("login:"+login);

			resultMap.put("errorNo", 0);
			resultMap.put("email", userInfo.getEmail());
			resultMap.put("name", userInfo.getName());
			resultMap.put("userId", userInfo.getUserId());
			resultMap.put("location", userInfo.getLocation());
			resultMap.put("language", userInfo.getLanguage());
			resultMap.put("providerId", userInfo.getProviderId());
			resultMap.put("hash", login.getHash());
		}

		return resultMap;
	}

	/**
	 @fn 
	 @brief (Override method) 함수 간략한 설명 : 소셜 사용자 인증
	 @remark
	 - 오버라이드 함수의 상세 설명 : email 과 providerId, accessToken 을 사용하여 사용자 인증을 한다.
	 @see com.paintee.mobile.auth.service.LoginService#loginSocial(com.paintee.common.repository.entity.vo.UserLoginVO)
	*/
	public Map<String, Object> loginSocial(UserLoginVO userLoginVO) {
		Map<String, Object> resultMap = new HashMap<>();

		//사용자 정보 조회
		UserExample userExamplem = new UserExample();
		UserExample.Criteria where = userExamplem.createCriteria();
		where.andEmailEqualTo(userLoginVO.getEmail());
		where.andProviderIdEqualTo(userLoginVO.getProviderId());
		where.andUserStatusEqualTo("N");

		List<User> userList = userHelper.selectByExample(userExamplem);

		if(userList == null || userList.size() == 0) {
			resultMap.put("errorNo", 404);
		} else {
			User userInfo = userList.get(0);

			DateTime toDate = new DateTime();
			DateTime expireDate = toDate.plusDays(expireDay);

			String loginWord = userInfo.getUserId()+userLoginVO.getAccessGubun()+expireDate.getMillis();

			Login login = new Login();
			login.setUserId(userInfo.getUserId());
			login.setAccessGubun(userLoginVO.getAccessGubun());
			login.setHash(Sha512Encrypt.hash(loginWord));
			login.setExpireDate(expireDate.toDate());

			loginHelper.insert(login);
			logger.debug("login:"+login);

			resultMap.put("errorNo", 0);
			resultMap.put("email", userInfo.getEmail());
			resultMap.put("name", userInfo.getName());
			resultMap.put("userId", userInfo.getUserId());
			resultMap.put("location", userInfo.getLocation());
			resultMap.put("language", userInfo.getLanguage());
			resultMap.put("providerId", userInfo.getProviderId());
			resultMap.put("hash", login.getHash());
		}

		return resultMap;
	}

	/**
	 @fn 
	 @brief (Override method) 함수 간략한 설명 : hash 에 대한 유효성 검증
	 @remark
	 - 오버라이드 함수의 상세 설명 : hash 에 대한 유효성 검증
	 @see com.paintee.mobile.auth.service.LoginService#hashCheck(java.lang.String)
	*/
	public boolean hashCheck(String painteeHash) {
		boolean result = false;

		logger.debug("painteeHash:{}", painteeHash);

		LoginExample loginExample = new LoginExample();
		LoginExample.Criteria where = loginExample.createCriteria();
		where.andHashEqualTo(painteeHash);
		where.andExpireDateGreaterThanOrEqualTo(new Date());

		List<Login> loginList = loginHelper.selectByExample(loginExample);

		if(loginList != null && loginList.size() > 0) {
			result = true;
		}

		return result;
	}

	/**
	 @fn 
	 @brief (Override method) 함수 간략한 설명 : hash 정보를 사용하여 해당 사용자 정보 조회
	 @remark
	 - 오버라이드 함수의 상세 설명 : hash 정보를 사용하여 해당 사용자 정보 조회
	 @see com.paintee.mobile.auth.service.LoginService#getUser(java.lang.String)
	*/
	public User getUser(String painteeHash) {
		User user = null;

		LoginExample loginExample = new LoginExample();
		LoginExample.Criteria where = loginExample.createCriteria();
		where.andHashEqualTo(painteeHash);
		where.andExpireDateGreaterThanOrEqualTo(new Date());

		List<Login> loginList = loginHelper.selectByExample(loginExample);

		if(loginList != null && loginList.size() > 0) {
			Login login = loginList.get(0);

			user = userHelper.selectByPrimaryKey(login.getUserId());
		}

		return user;
	}

	/**
	 @fn 
	 @brief (Override method) 함수 간략한 설명 :
	 @remark
	 - 오버라이드 함수의 상세 설명 : 
	 @see com.paintee.mobile.auth.service.LoginService#resetpassword(com.paintee.common.repository.entity.vo.UserLoginVO)
	*/
	@Transactional
	public Map<String, Object> resetpassword(UserLoginVO userLoginVO) throws Exception {
		Map<String, Object> resultMap = new HashMap<>();

		String tempPasswordPlainText = passwordGenerator.randomPassword();

		UserExample userExample = new UserExample();
		UserExample.Criteria where = userExample.createCriteria();
		where.andEmailEqualTo(userLoginVO.getEmail());
		where.andUserStatusEqualTo("N");

		User user = new User();
		user.setPassword(Sha512Encrypt.hash(tempPasswordPlainText));

		int count = userHelper.updateByExampleSelective(user, userExample);

		if(count > 0) {
			//임시비밀번호 메일 발송
			ResetPasswordMailVO resetPasswordMailVO = new ResetPasswordMailVO();
			resetPasswordMailVO.setPassword(tempPasswordPlainText);
			resetPasswordMailVO.setTitle("SignUp Title");
			resetPasswordMailVO.setSenderName("paintee");
	
			mailService.sendMail(user.getEmail(), "SignUp confirm", htmlContentBuilder.getResetPasswordMail(resetPasswordMailVO));
		}

		resultMap.put("errorNo", 0);

		return resultMap;
	}
}

/**
@file Application.java
@section 파일생성정보
|    항  목       |      내  용       |
| :-------------: | -------------   |
| File name | Application.java |    
| Package | com.paintee.admin.batch |    
| Project name | paintee-batch |    
| Type name | Application |    
| Company | Paintee | 
| Create Date | 2016 2016. 3. 31. 오후 11:40:51 |
| Author | Administrator |
| File Version | v1.0 |
*/
package com.paintee.admin.batch;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
@class Application
com.paintee.admin.batch \n
   ㄴ Application.java
 @section 클래스작성정보
    |    항  목       |      내  용       |
    | :-------------: | -------------   |
    | Company | Paintee |
    | Author | Administrator |
    | Date | 2016. 3. 31. 오후 11:40:51 |
    | Class Version | v1.0 |
    | 작업자 | Administrator |
 @section 상세설명
 - 발송 이후 상태변화없이 30일 경과되면 자동으로 '완료'로 변경 하는 batch
*/
public class Application {
	static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
	static final String DB_URL = "jdbc:mysql://painteeinstance.cc6gw4slet6s.ap-northeast-2.rds.amazonaws.com:3306/paintee";

	static final String USERNAME = "painteeAdmin";
	static final String PASSWORD = "paintee0402";

	public void process() {
		Connection conn = null;
		Statement stmt = null;

		try {
			Class.forName(JDBC_DRIVER);
			conn = DriverManager.getConnection(DB_URL,USERNAME,PASSWORD);

			stmt = conn.createStatement();

			StringBuilder updateQuery = new StringBuilder();

			updateQuery.append(" UPDATE TB_PURCHASE ");
			updateQuery.append(" SET purchase_status = '99' ");
			updateQuery.append(" WHERE seq IN ( ");
			updateQuery.append(" SELECT ");
			updateQuery.append(" seq ");
			updateQuery.append(" FROM ( ");
			updateQuery.append(" SELECT ");
			updateQuery.append(" seq, (TO_DAYS(now()) - TO_DAYS(status_update_date)) AS lasted_date ");
			updateQuery.append(" FROM TB_PURCHASE  ");
			updateQuery.append(" WHERE purchase_status = '2' ");
			updateQuery.append(" ) B ");
			updateQuery.append(" WHERE B.lasted_date >= 30 ");
			updateQuery.append(" ) ");

			stmt.executeUpdate(updateQuery.toString());

		} catch(SQLException se1) {
			se1.printStackTrace();
		} catch(Exception ex) {
			ex.printStackTrace();
		} finally {
			try {
				if(stmt != null)
					stmt.close();
			} catch(SQLException se2) {
			}
			try {
				if(conn != null)
					conn.close();
			} catch(SQLException se) {
				se.printStackTrace();
			}
		}
	}

	public static void main(String args[]) {
		Application application = new Application();
		application.process();
	}
}

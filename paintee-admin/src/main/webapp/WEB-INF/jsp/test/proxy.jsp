<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ page session="false"%>
<%@ page import="java.net.*, java.io.*" %>

<%

try{
	String reqUrl = request.getParameter("url");
	String line = "";

	 System.out.println("reqUrl : "+reqUrl);
	 
	 URL url = new URL(reqUrl);
	 HttpURLConnection conn = (HttpURLConnection) url.openConnection();
	 
	 conn.setDoOutput(true);
	 conn.setUseCaches(false);
	 conn.setRequestMethod(request.getMethod());
	 
	 System.out.println("request.getMethod(): "+request.getMethod());
	 
	 
	 response.setContentType(conn.getContentType());
	 
	 BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream(),
			"UTF-8" ));
	 
	 while((line = rd.readLine()) != null){
		 
		 System.out.println(line);
		 out.println(line);
	 }
       rd.close();
       }catch(Exception e){
    	   
    	   response.setStatus(500);
       }
%>



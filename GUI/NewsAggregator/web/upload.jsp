<%-- 
    Document   : upload
    Created on : Nov 29, 2014, 4:14:51 PM
    Author     : adwisatya
--%>
<%@page import ="gui.engine" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%
	String textInput = request.getParameter("textInput");
	if(textInput.isEmpty()){
		if(request.getParameter("file").isEmpty()){

		}else{
			out.println("Label berita:" + engine.getLabel(textInput));
		}
	}
%>

<%--
 * layout.jsp
 *
 * Copyright (C) 2016 Universidad de Sevilla
 * 
 * The use of this project is hereby constrained to the conditions of the 
 * TDG Licence, a copy of which you may download from 
 * http://www.tdg-seville.info/License.html
 --%>

<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security"	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <title><tiles:insertAttribute name="title" /></title>
	<base
			href="${pageContext.request.scheme}://${pageContext.request.serverName}:${pageContext.request.serverPort}${pageContext.request.contextPath}/" />


	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

<link rel="shortcut icon" href="favicon.ico"/> 


<link rel="stylesheet" href="assets/style.css" type="text/css">
	<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/rateYo/2.2.0/jquery.rateyo.min.css">
    <link rel="stylesheet" href="assets/chosen.css" type="text/css">
	<script src="assets/js/jquery.js" type="text/javascript"></script>
    <script src="assets/js/chosen.js"type="text/javascript" ></script>
    <script src="assets/js/app.js" type="text/javascript"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/rateYo/2.2.0/jquery.rateyo.min.js" type="text/javascript"></script>
<title><tiles:insertAttribute name="title" />${title}</title>
	<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<script type="text/javascript">
	function relativeRedir(loc) {
		var b = document.getElementsByTagName('base');
		if (b && b[0] && b[0].href) {
	  		if (b[0].href.substr(b[0].href.length - 1) == '/' && loc.charAt(0) == '/')
	    	loc = loc.substr(1);
	  		loc = b[0].href + loc;
		}
		window.location.replace(loc);
	}
</script>

</head>

<body>
<div class="wrapper">
		<tiles:insertAttribute name="header" />
	<main>
		<tiles:insertAttribute name="body" />
		<jstl:if test="${message != null}">
			<br />
			<span class="error"><spring:message code="${message}" /></span>
		</jstl:if>
	</main>
		<tiles:insertAttribute name="footer" />
</div>
</body>
</html>
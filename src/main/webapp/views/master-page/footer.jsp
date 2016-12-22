<%--
 * footer.jsp
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

<jsp:useBean id="date" class="java.util.Date" />

<footer>
	<article>
		<ul>
			<li><h3><i class="fa fa-list"></i> <spring:message code="sections"/> </h3></li>
			<li><a href="${contextPath}/recipe/list.do"><spring:message code="recipes"/> </a></li>
			<li><a href="${contextPath}/contest/list.do"><spring:message code="contests"/></a></li>
            <li><a href="${contextPath}/category/list.do"><spring:message code="categories"/></a></li>
		</ul>
	</article>
	<article>
		<ul>
			<li><h3><i class="fa fa-user"></i> <spring:message code="user"/> </h3></li>
			<li><a href="${contextPath}/user/list.do" class="icon"><spring:message code="list"/> </a></li>
			<li><a href="${contextPath}/actor/edit.do"><spring:message code="profile" /> </a></li>
			<li><a href="${contextPath}/message/list.do"><spring:message code="messages" /></a></li>
			<li><a href="${contextPath}/security/login.do"><spring:message code="login"/></a></li>
			<li><a href="${contextPath}/register.do"><spring:message code="register"/> </a></li>
			<li><a href="${contextPath}/security/j_spring_security_logout" > <spring:message code="logout"/></a></li>
			<security:authorize access="hasAnyRole('ADMIN')">
				<li><a href="${contextPath}/administrator/index.do"><spring:message code="administratorpanel"/></a></li>
			</security:authorize>
		</ul>
	</article>
	<article>
		<ul>
			<li><h3><i class="fa fa-language"></i> <spring:message code="language"/> </h3></li>
			<li><a href="?language=es"><spring:message code="spanish"/></a></li>
			<li><a href="?language=en"><spring:message code="english"/></a></li>
		</ul>
	</article>
	<div class="copyright">Copyright <i class="fa fa-copyright"></i> 2016 ACME, Inc.</div>
</footer>
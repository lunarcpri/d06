<%--
 * header.jsp
 *
 * Copyright (C) 2016 Universidad de Sevilla
 * 
 * The use of this project is hereby constrained to the conditions of the 
 * TDG Licence, a copy of which you may download from 
 * http://www.tdg-seville.info/License.html
 --%>

<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security"	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>

<header>
    <h1><a href="#">Acme Pad Thai</a></h1>
    <article class="search-section">
        <form name="masterpage-search-form" method="get" action="${contextPath}/recipe/list.do">
        <input type="search" name="query" placeholder="<spring:message code="search"/> ..."/>
        <input class="button" type="submit" value="<spring:message code="search"/>"/>
        </form>

    </article>
    <article class="user-section">
        <ul>
            <li><a href="${contextPath}/user/list.do" class="icon"><i class="fa fa-user"></i> <spring:message code="account"/> </a>
                <ul>
                    <li><a href="${contextPath}/user/list.do"><spring:message code="listofusers"/></a></li>
                    <security:authorize access="hasAnyRole('NUTRITIONIST','USER')">
                        <li><a href="${contextPath}/userornutritionist/list.do"><spring:message code="followedrecipes"/></a></li>
                    </security:authorize>
                    <security:authorize access="isAuthenticated()">
                        <li><a href="${contextPath}/actor/edit.do?edit=personal"> <spring:message code="profile"/> </a></li>
                        <li><a href="${contextPath}/message/list.do"><spring:message code="messages"/></a></li>
                        <security:authorize access="hasAnyRole('NUTRITIONIST')">
                            <li><a href="${contextPath}/nutritionist/curriculum/list.do"> <spring:message code="managecurriculum" /></a></li>
                            <li><a href="${contextPath}/nutritionist/ingredient/list.do"><spring:message code="manageingredients" /> </a></li>
                            <li><a href="${contextPath}/nutritionist/property/list.do"><spring:message code="manageproperties" /> </a></li>
                        </security:authorize>

                        <li><a href="${contextPath}/security/j_spring_security_logout" > <spring:message code="logout"/></a></li>
                    </security:authorize>
                    <security:authorize access="!isAuthenticated()">
                        <li><a href="${contextPath}/security/login.do"><spring:message code="login"/></a></li>
                        <li><a href="${contextPath}/register.do"><spring:message code="register"/></a></li>
                    </security:authorize>
                </ul></li>
            <li><a href="${contextPath}/contest/list.do" class="icon"><i class="fa fa-trophy"></i> <spring:message code="contests"/></a></li>
            <li><a href="${contextPath}/recipe/list.do" class="icon"><i class="fa fa-envira"></i> <spring:message code="recipes"/> </a>
            <security:authorize access="hasAnyRole('USER')">
                <ul>
                    <li><a href="${contextPath}/user/recipe/list.do"><spring:message code="managerecipes"/> </a></li>
                    <li><a href="${contextPath}/user/recipe/new.do"><spring:message code="newrecipe"/> </a></li>
                    <li><a href="${contextPath}/user/recipe/qualify.do"><spring:message code="qualifyrecipe"/></a></li>
                </ul>
            </security:authorize>
            </li>
            <li><a href="${contextPath}/category/list.do" class="icon"><i class="fa fa-tags"></i> <spring:message code="categories"/></a></li>
            <li><a href="${contextPath}/masterclass/list.do" class="icon"><i class="fa fa-book"></i> <spring:message code="masterclasses"/></a></li>
        </ul>
    </article>
</header>
<section class="banners">
	<article>
		<img src="assets/img/banner1.jpg">
		<img src="assets/img/banner2.jpg">
		<img src="assets/img/banner3.jpg">
		<img src="assets/img/banner4.jpg">
	</article>
</section>
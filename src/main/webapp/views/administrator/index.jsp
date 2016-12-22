<%@page language="java" contentType="text/html; charset=ISO-8859-1"
        pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security"	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="input" uri="http://www.springframework.org/tags/form" %>

<h1><spring:message code="administratorpanel" /> </h1>
<%@ include file="/views/administrator/layout.jsp" %>
<article class="col s7" style="margin-left:2%">
    <spring:message code="Whatdoyouwanttodo"/>
    <ul>
        <li><a href="${contextPath}/administrator/spamTags/list.do"><spring:message code="managespamtags" /> </a> </li>
        <li><a href="${contextPath}/administrator/category/list.do"><spring:message code="managecategories" /></a> </li>
        <li><a href="${contextPath}/administrator/contest/list.do"><spring:message code="managecontests" /> </a> </li>
        <li><a href="${contextPath}/administrator/dashboard.do"><spring:message code="dashboard" /> </a> </li>
    </ul>
</article>
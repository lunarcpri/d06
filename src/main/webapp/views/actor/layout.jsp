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

<h1>Edit Personal Info</h1>
<article class="col s3 navbar">
    <ul>
        <li><a href="${contextPath}/actor/edit.do?edit=personal">Edit Personal Data</a> </li>
        <security:authorize access="hasAnyRole('NUTRITIONIST')">
            <li><a href="${contextPath}/actor/edit.do?edit=curriculum">Edit Curriculum</a> </li>
        </security:authorize>
        <security:authorize access="hasAnyRole('USER')">
            <li><a href="${contextPath}/user/recipe/list.do"><spring:message code="managerecipes"/> </a> </li>
        </security:authorize>
        <li><a href="${contextPath}/actor/socialIdentity/list.do">Manage Social Identities</a> </li>
    </ul>
</article>

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
        <li><a href="${contextPath}/nutritionist/property/list.do"><spring:message code="managecurriculum" /> </a> </li>
        <li><a href="${contextPath}/nutritionist/ingredient/list.do"><spring:message code="manageingredients" /></a> </li>
        <li><a href="${contextPath}/nutritionist/property/list.do"><spring:message code="manageproperties" /> </a> </li>
    </ul>
</article>

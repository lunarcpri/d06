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

<%@ include file="/views/nutritionist/layout.jsp" %>
<article class="col s7" style="margin-left:2%">
    <h1> <spring:message code="${editOrCreate}" /> <spring:message code="ingredient"/> </h1>

    <form:form modelAttribute="ingredient" action="${actionURI}">
        <form:hidden path="id"/>
        <form:hidden path="version"/>

        <form:label path="name">
            <spring:message code="name"/>
        </form:label>
        <form:input path="name"/>
        <form:errors cssClass="error" path="name"/>

        <form:label path="description">
            <spring:message code="description"/>
        </form:label>
        <form:input path="description"/>
        <form:errors cssClass="error" path="description"/>

        <form:label path="properties">
            <spring:message code="properties"/>
        </form:label>
        <form:select path="properties" multiple="multiple">
            <form:options items="${properties}" itemLabel="name" itemValue="id"/>
        </form:select>
        <form:errors cssClass="error" path="properties"/>
        <div class="block">
            <input type="submit" value="<spring:message code="send"/>" />

        <a class="button" href="${contextPath}/nutritionist/ingredient/list.do"><spring:message code="cancel"/> </a>
        </div>
    </form:form>
</article>
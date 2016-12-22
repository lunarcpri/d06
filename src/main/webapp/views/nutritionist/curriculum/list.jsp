<%@page language="java" contentType="text/html; charset=ISO-8859-1"
        pageEncoding="ISO-8859-1" %>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<%@taglib prefix="display" uri="http://displaytag.sf.net" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="input" uri="http://www.springframework.org/tags/form" %>

<%@ include file="/views/nutritionist/layout.jsp" %>
<article class="col s7" style="margin-left:2%">
    <h1><spring:message code="listofProperties"/></h1>
    <ul class="horizontal-list message-list-options">
        <li><a href="${contextPath}/nutritionist/curriculum/new.do"><i class="fa fa-plus"></i>
            <spring:message code="newcurriculum"/> </a></li>
    </ul>
    <h2>Curriculum</h2>
    <jstl:if test="${empty curriculum}">
        <p><b><spring:message code="thereisnocurriculum"/> </b></p>
    </jstl:if>
    <jstl:if test="${not empty curriculum}">
        <h3><spring:message code="educational"/></h3>
        <p>${curriculum.educational}</p>
        <h3><spring:message code="experience"/></h3>
        <p>${curriculum.experience}</p>
        <h3><spring:message code="hobbies"/></h3>
        <p>${curriculum.hobbies}</p>
        <h3><spring:message code="references"/></h3>
        <jstl:forEach items="${curriculum.references}" var="reference" varStatus="index">
            <h1><spring:message code="reference"/> ${index.index+1} </h1>
            <p><b><spring:message code="name"/>:</b>${reference.name}</p>
            <p><b><spring:message code="homepage"/>:</b>${reference.homepage}</p>
        </jstl:forEach>
    </jstl:if>
    <div class="block">
        <a class="button" href="${contextPath}/nutritionist/curriculum/edit.do"><spring:message code="edit"/></a>
        <a class="button" href="${contextPath}/nutritionist/curriculum/delete.do"><spring:message code="delete"/></a>
    </div>
</article>
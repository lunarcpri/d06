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
    <h1><spring:message code="listofProperties"/> </h1>
    <ul class="horizontal-list message-list-options">
        <li><a href="${contextPath}/nutritionist/property/new.do"><i class="fa fa-plus"></i>
        <spring:message code="newproperty"/> </a></li>
    </ul>
    <display:table pagesize="5" class="displaytag" keepStatus="true"
                   name="propertys" requestURI="${requestURI}" id="row">

        <spring:message code="name" var="nameHeader" />
        <display:column property="name" title="${nameHeader}" sortable="true" />
        
        <spring:message code="edit" var="editHeader" />
        <display:column title="${editHeader}">
            <a href="${contextPath}/nutritionist/property/edit.do?propertyId=${row.id}">${editHeader}</a>
        </display:column>

        <spring:message code="delete" var="deleteHeader" />
        <display:column title="${deleteHeader}">
            <jstl:if test="${fn:length(row.ingredients)==0}">
            <a href="${contextPath}/nutritionist/property/delete.do?propertyId=${row.id}">${deleteHeader}</a>
            </jstl:if>
        </display:column>
    </display:table>
</article>
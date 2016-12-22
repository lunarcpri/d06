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
<section class="main">
    <h1><spring:message code="administrator.contest.newheader"/></h1>
    <%@ include file="/views/administrator/layout.jsp" %>
    <article class="col s7" style="margin-left:2%">
    <div class="form-group-2">

        <form:form action="administrator/contest/new.do" modelAttribute="contest" method="POST">

        <form:label path="title">
            <spring:message code="administrator.contest.title" />:
        </form:label>
            <form:input path="title"/>
            <form:errors cssClass="error" path="title"  />

            <form:label path="opened_at">
                <spring:message code="administrator.contest.openedat"/>
            </form:label>
                <form:input path="opened_at"/>
                <form:errors cssClass="error" path="opened_at"  />

        <form:label path="closed_at">
            <spring:message code="administrator.contest.closedat"/>
        </form:label>
            <form:input path="closed_at"/>
            <form:errors cssClass="error" path="closed_at"  />



            <form:hidden path="id"/>
            <form:hidden path="version"/>

        <div class="block">
            <input type="submit" name="save" value="<spring:message code="administrator.contest.create" /> " />
            <a class="button" href="${contextPath}/administrator/contest/list.do"><spring:message code="cancel"/> </a>
        </div>

        </form:form>
    </div>
    </article>
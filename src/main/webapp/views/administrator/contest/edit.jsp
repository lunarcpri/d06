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
    <h1><spring:message code="administrator.contest.editheader"/> ${contest.title}</h1>
    <%@ include file="/views/administrator/layout.jsp" %>
    <article class="col s7" style="margin-left:2%">
    <div class="form-group-1">
        <form:form action="administrator/contest/edit.do" modelAttribute="contest" method="POST">
            <jsp:useBean id="now" class="java.util.Date"/>
            <jstl:if test="${contest.open_at gt now}">
                <form:label path="title">
                    <spring:message code="administrator.contest.title" />:
                </form:label>
                <form:input path="title"/>
                <form:errors cssClass="error" path="title"  />
            </jstl:if>
            <jstl:if test="${contest.open_at lt now}">
                    <form:hidden path="title"/>
                </jstl:if >

            <form:label path="closed_at">
                <spring:message code="administrator.contest.closedat"/>
            </form:label>
            <form:input path="closed_at"/>
            <form:errors cssClass="error" path="closed_at"  />
            
            <form:hidden path="recipesQualified"/>
            <form:hidden path="winnerRecipes"/>
            <form:hidden path="ended" />
            <form:hidden path="opened_at"/>
            <form:hidden path="id"/>
            <form:hidden path="version"/>

            <div class="block">
                <input type="submit" name="edit" value="<spring:message code="administrator.contest.edit" /> " />
                <a class="button" href="${contextPath}/administrator/contest/list.do"><spring:message code="cancel"/> </a>
            </div>
        </form:form>
    </div>
    </article>
</section>


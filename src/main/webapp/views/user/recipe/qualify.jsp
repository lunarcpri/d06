

<%@page language="java" contentType="text/html; charset=ISO-8859-1"
        pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security"	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>

<%@ include file="/views/actor/layout.jsp" %>
<article class="col s7" style="margin-left:2%">
   <jstl:if test="${canQualify == true}">


    <<h2>Contest to qualify</h2>
    <display:table pagesize="5" class="displaytag" keepStatus="true"
                   name="contests" requestURI="${requestURI}" id="row">

        <spring:message code="recipes.title" var="titleHeader" />
        <display:column property="title" title="${titleHeader}" sortable="true" />

        <spring:message code="contest.openedat" var="openedatHeader" />
        <display:column property="opened_at" title="${openedatHeader}" sortable="true" format="{0,date,dd/MM/yyyy HH:mm}"/>

        <spring:message code="contest.closedat" var="closedatHeader" />
        <display:column property="closed_at" title="${closedatHeader}" sortable="true" format="{0,date,dd/MM/yyyy HH:mm}"/>


        <spring:message code="qualify" var="qualifyHeader" />
        <display:column title="${qualifyHeader}">
            <a href="http://localhost:8080/user/recipe/qualify.do?contestId=${row.id}&recipeId=${recipe.id}">${qualifyHeader}</a>
        </display:column>

    </display:table>
   </jstl:if>
<jstl:if test="${canQualify == false}">
    <spring:message code="cantqualify"/>
</jstl:if>
</article>

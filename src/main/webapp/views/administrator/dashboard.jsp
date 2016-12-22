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

<h1><spring:message code="administratorpanel"/></h1>
<%@ include file="/views/administrator/layout.jsp" %>
<article class="col s7" style="margin-left:2%">
    <h1><spring:message code="dashboard"/></h1>
    <h2><spring:message code="minmaxavgrecperuser"/></h2>
    <table>
        <thead>
        <tr>
            <th><spring:message code="min"/></th>
            <th><spring:message code="max"/></th>
            <th><spring:message code="avg"/></th>
        </tr>
        </thead>
        <tbody>
        <tr>
            <td>${minrecipesperuser}</td>
            <td>${maxrecipesperuser}</td>
            <td>${avgrecipesperuser}</td>
        </tr>
        </tbody>
    </table>

    <h2><spring:message code="userwithmorerecipes"/></h2>
    <table>
        <thead>
        <tr>
            <th><spring:message code="user"/></th>
            <th><spring:message code="nrecipes"/></th>
        </tr>
        </thead>
        <tbody>
        <tr>
            <td>${usermorerecipes.userAccount.username}</td>
            <td>${fn:length(usermorerecipes.recipes)}</td>
        </tr>
        </tbody>
    </table>

    <h2><spring:message code="minmaxavgrecpercontest"/></h2>
    <table>
        <thead>
        <tr>
            <th><spring:message code="min"/></th>
            <th><spring:message code="max"/></th>
            <th><spring:message code="avg"/></th>
        </tr>
        </thead>
        <tbody>
        <tr>
            <td>${minrecipespercontest}</td>
            <td>${maxrecipespercontest}</td>
            <td>${avgrecipespercontest}</td>
        </tr>
        </tbody>
    </table>

    <h2><spring:message code="contestwithmorerecipes"/></h2>
    <table>
        <thead>
        <tr>
            <th><spring:message code="contest"/></th>
            <th><spring:message code="nrecipes"/></th>
        </tr>
        </thead>
        <tbody>
        <tr>
            <td>${contestmorerecipes.title}</td>
            <td>${fn:length(contestmorerecipes.recipesQualified)}</td>
        </tr>
        </tbody>
    </table>
    <h2><spring:message code="stdavgstepsperrecipe"/></h2>
    <table>
        <thead>
        <tr>
            <th><spring:message code="avg"/></th>
            <th><spring:message code="std"/></th>
        </tr>
        </thead>
        <tbody>
        <tr>
            <td>${stdstepsperrecipe}</td>
            <td>${avgstepsperrecipe}</td>
        </tr>
        </tbody>
    </table>
    <h2><spring:message code="stdavgingredientsperrecipe"/></h2>
    <table>
        <thead>
        <tr>
            <th><spring:message code="avg"/></th>
            <th><spring:message code="std"/></th>
        </tr>
        </thead>
        <tbody>
        <tr>
            <td>${stdingredientsperrecipe}</td>
            <td>${avgingredientsperrecipe}</td>
        </tr>
        </tbody>
    </table>
    <h2><spring:message code="listofuserbypopularity"/></h2>
    <display:table pagesize="5" class="displaytag" keepStatus="true"
                   name="userspopularity" requestURI="administrator/dashboard.do" id="row">

        <!-- Attributes -->

        <spring:message code="name" var="nameHeader"/>
        <display:column property="name" title="${nameHeader}" sortable="true"/>

        <spring:message code="surnames" var="surnamesHeader"/>
        <display:column property="name" title="${surnamesHeader}" sortable="true"/>

        <spring:message code="email" var="emailHeader"/>
        <display:column property="email" title="${emailHeader}" sortable="true"/>

        <spring:message code="phone" var="phoneHeader"/>
        <display:column property="phone" title="${phoneHeader}" sortable="true"/>

        <spring:message code="address" var="addressHeader"/>
        <display:column property="address" title="${addressHeader}" sortable="false"/>

        <spring:message code="browse" var="browseHeader"/>
        <display:column title="${browseHeader}">
            <a href="${contextPath}/user/${row.id}.do">${browseHeader}</a>
        </display:column>

    </display:table>
    <h2><spring:message code="listofuserbyavgrecipeslike"/></h2>
    <display:table pagesize="5" class="displaytag" keepStatus="true"
                   name="usersrecipes" requestURI="administrator/dashboard.do" id="row2">

        <!-- Attributes -->

        <spring:message code="name" var="nameHeader"/>
        <display:column property="name" title="${nameHeader}" sortable="true"/>

        <spring:message code="surnames" var="surnamesHeader"/>
        <display:column property="name" title="${surnamesHeader}" sortable="true"/>

        <spring:message code="email" var="emailHeader"/>
        <display:column property="email" title="${emailHeader}" sortable="true"/>

        <spring:message code="phone" var="phoneHeader"/>
        <display:column property="phone" title="${phoneHeader}" sortable="true"/>

        <spring:message code="address" var="addressHeader"/>
        <display:column property="address" title="${addressHeader}" sortable="false"/>

        <spring:message code="browse" var="browseHeader"/>
        <display:column title="${browseHeader}">
            <a href="${contextPath}/user/${row2.id}.do">${browseHeader}</a>
        </display:column>

    </display:table>
</article>
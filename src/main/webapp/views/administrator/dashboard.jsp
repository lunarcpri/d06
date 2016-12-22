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
    <h1><spring:message code="dashboard"/></h1>
    <h2><spring:message code="minmaxavgrecperuser"/> </h2>
    <table>
        <thead>
        <tr>
            <th><spring:message code="min"/> </th>
            <th><spring:message code="max"/> </th>
            <th><spring:message code="avg"/> </th>
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

    <h2><spring:message code="userwithmorerecipes"/> </h2>
    <table>
        <thead>
        <tr>
            <th><spring:message code="user"/> </th>
            <th><spring:message code="nrecipes"/> </th>
        </tr>
        </thead>
        <tbody>
        <tr>
            <td>${usermorerecipes.userAccount.username}</td>
            <td>${fn:length(usermorerecipes.recipes)}</td>
        </tr>
        </tbody>
    </table>

    <h2><spring:message code="minmaxavgrecpercontest"/> </h2>
    <table>
        <thead>
        <tr>
            <th><spring:message code="min"/> </th>
            <th><spring:message code="max"/> </th>
            <th><spring:message code="avg"/> </th>
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

    <h2><spring:message code="contestwithmorerecipes"/> </h2>
    <table>
        <thead>
        <tr>
            <th><spring:message code="contest"/> </th>
            <th><spring:message code="nrecipes"/> </th>
        </tr>
        </thead>
        <tbody>
        <tr>
            <td>${contestmorerecipes.title}</td>
            <td>${fn:length(contestmorerecipes.recipesQualified)}</td>
        </tr>
        </tbody>
    </table>

</article>
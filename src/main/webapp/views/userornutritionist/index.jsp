<%--
 * action.jsp
 *
 * Copyright (C) 2016 Universidad de Sevilla
 * 
 * The use of this project is hereby constrained to the conditions of the 
 * TDG Licence, a copy of which you may download from 
 * http://www.tdg-seville.info/License.html
 --%>

<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl"	uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<section class="main">

	<h1> <spring:message code="followedrecipes" /> </h1>

	<display:table pagesize="5" class="displaytag" keepStatus="true"
				   name="recipes" requestURI="${requestURI}" id="row">

		<!-- Action links -->

		<!-- Attributes -->

		<spring:message code="recipes.title" var="titleHeader" />
		<display:column property="title" title="${titleHeader}" sortable="true" />

		<spring:message code="recipes.summary" var="summaryHeader" />
		<display:column property="summary" title="${summaryHeader}" sortable="false"/>

		<spring:message code="createdat" var="createdatHeader" />
		<display:column property="created_at" title="${createdatHeader}" sortable="true" format="{0,date,dd/MM/yyyy HH:mm}" />

		<spring:message code="recipes.author" var="authorHeader" />
		<display:column title="${authorHeader}">
			<a href="http://localhost:8080/user/${row.author.id}.do">${row.author.userAccount.username}</a>
		</display:column>
		<spring:message code="recipes.category" var="categoryHeader" />
		<display:column title="${categoryHeader}">
			<a href="http://localhost:8080/recipe/list.do?category=${row.category.id}">${row.category.name}</a>
		</display:column>

		<spring:message code="userornutritionist.browse" var="browseHeader" />
		<display:column title="${browseHeader}">
			<a href="http://localhost:8080/recipe/${row.id}.do">${browseHeader}</a>
		</display:column>
	</display:table>
</section>
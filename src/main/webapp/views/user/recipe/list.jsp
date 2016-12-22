

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
	<ul class="horizontal-list message-list-options">
		<li><a href="${contextPath}/user/recipe/new.do"><i class="fa fa-plus"></i> <spring:message code="newrecipe"/> </a></li>
	</ul>
<h2><spring:message code="recipesauthored" /> </h2>
	<display:table pagesize="5" class="displaytag" keepStatus="true"
				   name="recipes" requestURI="${requestURI}" id="row">


		<spring:message code="recipes.title" var="titleHeader" />
		<display:column property="title" title="${titleHeader}" sortable="true" />

		<spring:message code="recipes.summary" var="summaryHeader" />
		<display:column property="summary" title="${summaryHeader}" sortable="false"/>

		<spring:message code="browse" var="browseHeader" />
		<display:column title="${browseHeader}">
			<a href="http://localhost:8080/recipe/${row.id}.do">${browseHeader}</a>
		</display:column>
		<spring:message code="edit" var="editHeader" />
		<display:column title="${editHeader}">
            <jstl:if test="${row.read_only == false}">
			<a href="http://localhost:8080/user/recipe/edit.do?recipeId=${row.id}">${editHeader}</a>
            </jstl:if>
        </display:column>

		<spring:message code="delete" var="deleteHeader" />
		<display:column title="${deleteHeader}">
			<a href="http://localhost:8080/user/recipe/delete.do?recipeId=${row.id}">${deleteHeader}</a>
		</display:column>

		<spring:message code="qualifyrecipe" var="qualifyrecipeHeader" />
		<display:column title="${qualifyrecipeHeader}">
			<a href="http://localhost:8080/user/recipe/qualifyRecipe.do?recipeId=${row.id}">${qualifyrecipeHeader}</a>
		</display:column>

	</display:table>
	</article>

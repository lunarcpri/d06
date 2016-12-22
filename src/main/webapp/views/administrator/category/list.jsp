<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl"	uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>

<section class="main">
    <%@ include file="/views/administrator/layout.jsp" %>
    <article class="col s7" style="margin-left:2%">
        <h1><spring:message code="listofcategories"/> </h1>
        <ul class="horizontal-list message-list-options">

            <li><a href="${contextPath}/administrator/category/new.do"><i class="fa fa-plus"></i> New category</a></li>
        </ul>

<display:table pagesize="5" class="displaytag" keepStatus="true"
               name="categories" requestURI="${requestURI}" id="row">

    <!-- Action links -->

    <!-- Attributes -->

    <spring:message code="categories.name" var="nameHeader" />
    <display:column property="name" title="${nameHeader}" sortable="true" />

    <spring:message code="categories.description" var="descriptionHeader" />
    <display:column property="description" title="${descriptionHeader}" sortable="false"/>


    <spring:message code="categories.tags" var="tagsHeader" />
    <display:column property="tags" title="${tagsHeader}" sortable="false"/>

    <spring:message code="administrator.category.edit" var="editHeader" />
    <display:column  title="${editHeader}">

        <a href="${contextPath}/administrator/category/edit.do?categoryId=${row.id}">
            ${editHeader}
                </display:column>



                    <spring:message code="delete" var="deleteHeader" />
                <display:column title="${deleteHeader}">
                <a href="http://localhost:8080/administrator/category/delete.do?categoryId=${row.id}">${deleteHeader}</a>
                </display:column>

</display:table>
    </article>
</section>





<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl"	uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>

<section class="main">

    <h1><spring:message code="administrator.contest.listheader"/></h1>

    <article>

        <ul class="horizontal-list message-list-options">

            <li><a href="${contextPath}/administrator/contest/new.do"><i class="fa fa-plus"></i><spring:message code="administrator.contest.newheader"/></a></li>
        </ul>
        <display:table pagesize="5" class="displaytag" keepStatus="true"
                       name="contests" requestURI="${requestURI}" id="row">

            <!-- Action links -->

            <!-- Attributes -->

            <spring:message code="administrator.contest.title" var="titleHeader" />
            <display:column property="title" title="${titleHeader}" sortable="true" />

            <spring:message code="administrator.contest.openedat" var="openedatHeader" />
            <display:column property="opened_at" title="${openedatHeader}" sortable="true" format="{0,date,dd/MM/yyyy HH:mm}"/>

            <spring:message code="administrator.contest.closedat" var="closedatHeader" />
            <display:column property="closed_at" title="${closedatHeader}" sortable="true" format="{0,date,dd/MM/yyyy HH:mm}"/>


            <spring:message code="browse" var="browseHeader" />
            <display:column title="${browseHeader}">
                <a href="http://localhost:8080/contest/${row.id}.do">${browseHeader}</a>
            </display:column>

        <spring:message code="administrator.category.edit" var="editHeader" />
        <display:column  title="${editHeader}">
        <a href="${contextPath}/administrator/contest/edit.do?contestId=${row.id}">
                ${editHeader}
            </display:column>



            <spring:message code="administrator.contest.delete" var="deleteHeader" />
            <display:column title="${deleteHeader}">
            <a href="http://localhost:8080/administrator/contest/delete.do?contestId=${row.id}">${deleteHeader}</a>
            </display:column>
        </display:table>
    </article>
</section>





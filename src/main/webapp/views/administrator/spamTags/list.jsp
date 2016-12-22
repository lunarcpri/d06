<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl"	uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>

<section class="main">

    <%@ include file="/views/administrator/layout.jsp" %>
    <article class="col s7" style="margin-left:2%">

        <h1><spring:message code="spamTags.header"/></h1>
        <ul class="horizontal-list message-list-options">

            <li><a href="${contextPath}/administrator/spamTags/new.do"><i class="fa fa-plus"></i> New SpamTag</a></li>
        </ul>
        <display:table pagesize="5" class="displaytag" keepStatus="true"
                       name="spamTags" requestURI="${requestURI}" id="row">

            <ul class="horizontal-list message-list-options">

                <li><a href="${contextPath}/administrator/spamTags/new.do"><i class="fa fa-plus"></i> New SpamTag</a></li>
            </ul>

            <spring:message code="spamTags.name" var="nameHeader" />
            <display:column property="name" title="${nameHeader}" sortable="true" />



        </display:table>
    </article>
</section>
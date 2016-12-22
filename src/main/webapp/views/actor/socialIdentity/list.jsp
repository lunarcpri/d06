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


<h2><spring:message code="actor.socialIdentity.header"/></h2>
<ul class="horizontal-list message-list-options">
    <li><a href="${contextPath}/actor/socialIdentity/new.do"><i class="fa fa-plus"></i><spring:message code="actor.socialIdentity.newbutton"/> </a></li>
</ul>
<display:table pagesize="5" class="displaytag" keepStatus="true"
               name="socialIdentities" requestURI="${requestURI}" id="row">

    <!-- Action links -->

    <!-- Attributes -->

    <display:column property="nick" title="nick" sortable="true" />

    <spring:message code="actor.socialIdentity.name" var="nameHeader" />
    <display:column property="name" title="${nameHeader}" />


    <display:column property="link" title="Url"/>


    <spring:message code="actor.socialIdentity.edit" var="editHeader" />
    <display:column title="${editHeader}">
        <a href="http://localhost:8080/actor/socialIdentity/edit.do?socialIdentityId=${row.id}">${editHeader}</a>
    </display:column>

</display:table>
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


<h2><spring:message code="actor.socialIdentity.editheader"/> </h2>
<form:form modelAttribute="socialIdentity" action="actor/socialIdentity/edit.do">
<div class="form-group-1 socialIdentity">

    <div class="card inline s10">
        <h2>${socialIdentity.name}</h2>
        <form:hidden path="id"/>
        <form:hidden path="actor"/>
        <form:label path="name">
            <spring:message code="actor.socialIdentity.name" />
        </form:label>
        <form:errors cssClass="error" path="name" />
        <form:input required="required" path="name" />
        <form:label path="nick">
            <spring:message code="actor.socialIdentity.nick" />
        </form:label>
        <form:input  required="required" path="nick" />
        <form:errors cssClass="error" path="nick" />
        <form:label path="link">
            <spring:message code="actor.socialIdentity.link" />
        </form:label>
        <form:input type="url" equired="required"  path="link" />
        <form:errors cssClass="error" path="link" />
        <form:label path="picture">
            <spring:message code="actor.socialIdentity.picture" />
        </form:label>
        <form:input type="url" path="picture" />
        <form:errors cssClass="error" path="picture" />
    <div class="block">

        <input name="save" type="submit" value="<spring:message code="actor.socialIdentity.edit"/>"/>
        <a class="button"  href="${context}/actor/socialIdentity/delete.do?socialIdentityId=${socialIdentity.id}">
            <spring:message code="actor.socialIdentity.delete" /></a>
        <a class="button"  href="${context}/actor/socialIdentity/list.do">
            <spring:message code="actor.socialIdentity.cancel" /></a>

    </div>
    </div>
</form:form>
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

<%@ include file="/views/actor/layout.jsp" %>
<article class="col s7" style="margin-left:2%">
<h2>Social Identities</h2>
<form:form modelAttribute="socialIdentity" action="actor/socialIdentity/new.do">
    <div class="form-group-1 socialIdentity">

        <div class="card inline s10">
            <h2>${socialIdentity.name}</h2>
            <form:hidden path="id"/>
            <form:hidden path="version"/>
            <form:label path="name">
                <spring:message code="name"/>:
            </form:label>
            <form:errors cssClass="error" path="name"/>
            <form:input required="required" path="name"/>
            <form:label path="nick">
                Nickname
            </form:label>
            <form:input required="required" path="nick"/>
            <form:errors cssClass="error" path="nick"/>
            <form:label path="link">
                <spring:message code="link"/>:
            </form:label>
            <form:input type="url" equired="required" path="link"/>
            <form:errors cssClass="error" path="link"/>
            <form:label path="picture">
                <spring:message code="picture"/>:
            </form:label>
            <form:input type="url" path="picture"/>
            <form:errors cssClass="error" path="picture"/>
            <div class="block">
                <input type="submit" name="save" value="<spring:message code="save"/>"/>
                <a class="button" href="${context}/actor/socialIdentity/list.do">
                    <spring:message code="cancel"/></a>

            </div>
        </div>
</form:form>
    </div>
</article>
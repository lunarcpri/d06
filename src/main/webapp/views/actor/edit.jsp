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

<%@ include file="/views/actor/layout.jsp" %>
<article class="col s7" style="margin-left:2%">
    <form:form action="actor/edit.do" modelAttribute="actor" method="POST">
        <form:hidden path="id" />
        <h2><spring:message code="actor.header"/> </h2>
        <input type="hidden" value="${role}" name="role"/>
        <div class="form-group-2">
            <form:label path="email">
                <spring:message code="actor.email" />:
            </form:label>
            <form:input path="email" type="email" required="required"/>
            <form:errors cssClass="error" path="email" />
            <br>
            <form:label path="name">
                <spring:message code="actor.name" />:
            </form:label>
            <form:input path="name" required="required" />
            <form:errors cssClass="error" path="name" />
            <br>
            <form:label path="surnames">
                <spring:message code="actor.surnames" />:
            </form:label>
            <form:input path="surnames" required="required" />
            <form:errors cssClass="error"  path="surnames" />
            <br>
        </div>
        <div class="form-group-2">
            <form:label path="phone">
                <spring:message code="actor.phone" />:
            </form:label>
            <form:input path="phone" pattern="((\+[0-9]{1,3})?\s*(\([0-9]{3}\))?\s*([a-zA-Z0-9\- ]{4,}))$" />
            <form:errors cssClass="error" path="phone" />
            <br>
            <form:label path="address">
                <spring:message code="actor.address" />:
            </form:label>
            <form:input path="address" />
            <form:errors cssClass="error" path="address" />
        </div>
        
        <input  name="save" type="submit" value="<spring:message code="actor.edit" />" />
        <a  href="${contextPath}" class="button cancel_button"><spring:message code="actor.cancel" /></a>
    </form:form>
</article>


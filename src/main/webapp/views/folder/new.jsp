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
<section class="main">
    <h1><spring:message code="folder.newheader"/> </h1>

    <div class="form-group-1">
        <form:form action="folder/new.do" modelAttribute="folder" method="POST">
            <form:label path="name">
                <spring:message code="folder.name" />:
            </form:label>
            <form:input path="name"/>
            <form:errors cssClass="error" path="name"  />
            <div class="block">
            <input type="submit" name="send" value="<spring:message code="folder.create" /> " />
            </div>
        </form:form>
    </div>
</section>


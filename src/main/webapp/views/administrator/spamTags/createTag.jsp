<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>

<h1><spring:message code="administrator.header"/></h1>

<form:form method="POST" modelAttribute="spamTags" action="administrator/spamTags/new.do">
	<form:label path="name"><spring:message code="administrator.spamTag"/></form:label>
	<form:input path="name" type="text"/>
	<input type="submit" name="save" value="<spring:message code="administrator.add"/>" />
</form:form>
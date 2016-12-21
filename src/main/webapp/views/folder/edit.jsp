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
    <h1>Edit Folder ${folder.name}</h1>

    <div class="form-group-1">
        <form:form action="folder/edit.do" modelAttribute="folder" method="POST">
            <form:label path="name">
                <spring:message code="name" />:
            </form:label>
            <form:input path="name"/>
            <form:errors cssClass="error" path="name"  />
            <form:hidden path="id"/>
            <form:hidden path="actor"/>
            <form:hidden path="folderType"/>
            <div class="block">
            <input type="submit" name="send" value="<spring:message code="send" /> " />
                <a class="button" href="${contextPath}/folder/list.do"><spring:message code="cancel"/> </a>
            </div>
        </form:form>
    </div>
</section>


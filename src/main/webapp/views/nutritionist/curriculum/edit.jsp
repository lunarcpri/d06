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

<%@ include file="/views/nutritionist/layout.jsp" %>
<article class="col s7" style="margin-left:2%">
    <h1><spring:message code="${editOrCreate}"/> Curriculum </h1>

    <form:form modelAttribute="curriculum" action="${actionURI}">
        <form:hidden path="id"/>
        <form:hidden path="version"/>

        <form:label path="educational">
            <spring:message code="educational"/>
        </form:label>
        <form:textarea path="educational"/>
        <form:errors cssClass="error" path="educational"/>

        <form:label path="experience">
            <spring:message code="experience"/>
        </form:label>
        <form:textarea path="experience"/>
        <form:errors cssClass="error" path="experience"/>

        <form:label path="hobbies">
            <spring:message code="hobbies"/>
        </form:label>
        <form:textarea path="hobbies"/>
        <form:errors cssClass="error" path="hobbies"/>
        <h2><spring:message code="hobbies"/></h2>
        <jstl:if test="${nReferences gt 0}">

            <jstl:forEach begin="0" end="${nReferences-1}" varStatus="index">
                <h3><spring:message code="reference"/> ${index.index}</h3>

                <form:hidden path="references[${index.index}].id"/>
                <form:hidden path="references[${index.index}].version"/>
                <form:label path="references[${index.index}].name">
                    <spring:message code="name"/>
                </form:label>
                <form:input path="references[${index.index}].name"/>
                <form:errors cssClass="error" path="references[${index.index}].name"/>

                <form:label path="references[${index.index}].homepage">
                    <spring:message code="homepage"/>
                </form:label>
                <form:input path="references[${index.index}].homepage" type="url" required="required"/>
                <form:errors cssClass="error" path="references[${index.index}].homepage"/>
            </jstl:forEach>
        </jstl:if>
        <div class="block">
            <a href="${actionURI}?nReferences=${nReferences+1}" class="button"><spring:message code="addreference"/></a>
            <a href="${actionURI}?nReferences=${nReferences-1}" class="button"><spring:message
                    code="removereference"/></a>
        </div>
        <div class="block">
            <input type="submit" value="<spring:message code="send"/>"/>

            <a class="button" href="${contextPath}/nutritionist/curriculum/list.do"><spring:message code="cancel"/> </a>
        </div>
    </form:form>
</article>
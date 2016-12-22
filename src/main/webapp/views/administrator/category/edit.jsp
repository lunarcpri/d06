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
    <h1>Edit Category ${category.name}</h1>

    <div class="form-group-1">
        <form:form action="administrator/category/edit.do" modelAttribute="category" method="POST">

            <form:label path="name">
                <spring:message code="administrator.categories.name" />:
            </form:label>
            <form:input path="name"/>
            <form:errors cssClass="error" path="name"  />

            <form:label path="description">
                <spring:message code="administrator.categories.description"/>
            </form:label>
            <form:input path="description" type="url"/>
            <form:errors cssClass="error" path="description"  />

            <form:label path="description">
                <spring:message code="administrator.categories.description"/>
            </form:label>
            <form:input path="description"/>
            <form:errors cssClass="error" path="description"  />

            <form:label path="picture">
                <spring:message code="administrator.categories.picture"/>
            </form:label>
            <form:input path="picture" type="url"/>
            <form:errors cssClass="error" path="picture"  />

            <form:label path="tags">
                <spring:message code="administrator.categories.tags"/>
            </form:label>
            <form:input path="tags" type="url"/>
            <form:errors cssClass="error" path="tags" />

            <form:label path="parent">
                <spring:message code="administrator.categories.parent"/>
            </form:label>
            <form:select path="parent" style="width:120px">
                <form:option value=""/>
                <form:options itemValue="id" itemLabel="name" items="${categories}"/>
            </form:select>
            <form:errors cssClass="error" path="quantities[${index.index}].ingredient"/>

            <form:hidden path="id"/>
            <form:hidden path="version"/>

            <div class="block">
            <input type="submit" name="save" value="<spring:message code="administrator.categories.edit" /> " />
                <a class="button" href="${contextPath}/administrator/category/list.do"><spring:message code="cancel"/> </a>
            </div>
        </form:form>
    </div>
</section>


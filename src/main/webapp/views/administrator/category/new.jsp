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

    <h1>New Category</h1>
    <%@ include file="/views/administrator/layout.jsp" %>
    <article class="col s7" style="margin-left:2%">


        <form:form action="administrator/category/new.do" modelAttribute="category" method="POST">
            <div class="form-group-2">
            <form:label path="name">
                <spring:message code="administrator.categories.name" />:
            </form:label>
                <form:input path="name"/>
                <form:errors cssClass="error" path="name"  />

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
            </div>
            <div class="form-group-2">
            <form:label path="tags">
                <spring:message code="administrator.categories.tags"/>
            </form:label>
                <form:input path="tags" type="text"/>
                <form:errors cssClass="error" path="tags" />

            <form:label path="parent">
                <spring:message code="administrator.categories.parent"/>
            </form:label>
            <form:select path="parent" style="width:50%">
                <form:options items="${categories}" itemValue="id" itemLabel="name"/>
            </form:select>
                <form:errors cssClass="error" path="parent"/>
            
            <form:label path="childrens">
                <spring:message code="administrator.categories.childrens"/> 
            </form:label>
            <form:select path="childrens" style="width:50%" multiple="true">
                <form:options items="${categories}" itemValue="id" itemLabel="name"/>
            </form:select>
            </div>

                <form:hidden path="id"/>
                <form:hidden path="version"/>

            <div class="block">
                <input type="submit" name="save" value="<spring:message code="administrator.categories.create" /> " />
                <a class="button" href="${contextPath}/administrator/category/list.do"><spring:message code="cancel"/> </a>
            </div>


        </form:form>
    </article>
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
<h1>New Recipe</h1>

    <div class="form-group-2">
<form:form action="user/recipe/new.do" modelAttribute="recipe" method="POST">
    <form:label path="title">
        <spring:message code="title" />:
    </form:label>
    <form:input path="title"/>
    <form:errors cssClass="error" path="title"/>

    <form:label path="picture">
        <spring:message code="picture" />:
    </form:label>
    <form:input path="picture"/>
    <form:errors type="url" cssClass="error" path="picture"/>

    <form:label path="category">
        <spring:message code="category" />:
    </form:label>
    <form:select path="category" style="width:50%">
        <form:options items="${categories}" itemValue="id" itemLabel="name"/>
    </form:select>
    <form:errors type="url" cssClass="error" path="category"/>
</div>
    <div class="form-group-2">
    <h2><spring:message code="quantities"/> </h2>
        <article id="quantities-list">
            <jstl:forEach begin="0" end="${nIngredients-1}" varStatus="index">
        <div id="ingredient-quantity0">
        <div class="col s3" id="quantities-selection">
            <form:label path="quantities[${index.index}].ingredient">
                <spring:message code="ingredient"/>
            </form:label>
        <form:select path="quantities[${index.index}].ingredient" style="width:120px">
            <form:options itemValue="id" itemLabel="name" items="${ingredients}"/>
        </form:select>
            <form:errors cssClass="error" path="quantities[${index.index}].ingredient"/>
        </div>

        <div class="col s3">
            <form:label path="quantities[${index.index}].quantity">
                <spring:message code="quantity"/>
            </form:label>
        <form:input  style="width:80px" step="any" path="quantities[${index.index}].quantity" type="number"/>
            <form:errors cssClass="error" path="quantities[${index.index}].quantity"/>
        </div>
        <div class="col s3">
            <form:label path="quantities[${index.index}].unit">
                <spring:message code="unit"/>
            </form:label>
             <form:select path="quantities[${index.index}].unit" style="width:120px">
            <jstl:forEach items="gram, kilograms, ounces, pounds, militres, litres, spoons, cups, pieces" var="unit">
                <form:option value="${unit}">${unit}</form:option>
            </jstl:forEach>
        </form:select>
            <form:errors cssClass="error" path="quantities[${index.index}].unit"/>
        </div>

        </div>
            </jstl:forEach>
        </article>
        <a href="${contextPath}/user/recipe/new.do?nIngredients=${nIngredients+1}&nSteps=${nSteps}" class="button">Add Ingredient</a>
    </div>
    <div class="form-group-1">
        <label for="summary">
        <spring:message code="summary"/>
    </label>
        <form:textarea path="summary" />
        <form:errors cssClass="error" path="summary"/>
       <h1>Steps</h1>
        <jstl:forEach begin="0" end="${nSteps-1}" varStatus="index">
            <h2>Step ${index.index}</h2>
            <label for="steps[${index.index}].picture">
               <spring:message code="picture" />
            </label>
            <form:input path="steps[${index.index}].picture" type="url" />
            <form:errors cssClass="error" path="steps[${index.index}].picture"/>

            <label for="steps[${index.index}].picture">
                <spring:message code="description" />
            </label>
            <form:textarea path="steps[${index.index}].description" />
            <form:errors cssClass="error" path="steps[${index.index}].description"/>

            <label for="steps[${index.index}].hints">
                <spring:message code="hints" />
            </label>
            <form:textarea path="steps[${index.index}].hints" />
            <form:errors cssClass="error" path="steps[${index.index}].hints"/>
        </jstl:forEach>
        <a href="${contextPath}/user/recipe/new.do?nIngredients=${nIngredients}&nSteps=${nSteps+1}" class="button" id="add-quantity">Add Step</a>
    </div>
    <div class="block">

    <input type="submit" name="send" value="<spring:message code="send" /> " />
    </div>
</form:form>
</section>

<script>


</script>


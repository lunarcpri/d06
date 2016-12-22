<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl"	uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<section class="main">

    <h1>Recipe ${recipe.title}</h1>
    <article class="col s7 ingredients">
        <h2>Ingredients</h2>
        <ul>
        <jstl:forEach var="quantity" items="${recipe.quantities}">
            <li>${quantity.quantity} ${quantity.unit} <spring:message code="of"/> ${quantity.ingredient.name} </li>
        </jstl:forEach>
        </ul>
    </article>
    <article class="col s4">
        <h2> Recipe info</h2>
        <p>
<c:if test="${not empty recipe.picture}">
            <img src="${recipe.picture}" width="100" height="100"/>
</c:if>
    </p>
        <p><b><spring:message code="ticker"/>:</b> ${recipe.ticker} </p>
        <p><b><spring:message code="category"/>:</b> <a href="/category/${recipe.category.id}.do}">${recipe.category.name}</a> </p>
        <p><b><spring:message code="author"/>:</b>
            <a href="http://localhost:8080/user/${recipe.author.id}.do">${recipe.author.userAccount.username} </a></p>
        <p><b><spring:message code="created_at"/>:</b> ${recipe.created_at} </p>
        <p><b><spring:message code="updated_at"/>:</b> ${recipe.updated_at} </p>
        <p><b><spring:message code="likes"/>:</b> ${likes}</p>
        <p><b><spring:message code="dislikes"/>: </b>${dislikes}</p>
        <jstl:if test="${canlike==true}">
            <div class="likes">
            <a href="${likeURI}" class="like<jstl:if test="${liked==true}"> liked</jstl:if>"><i class="fa fa-thumbs-up"></i></a>
            <a href="${dislikeURI}" class="dislike<jstl:if test="${disliked==true}"> disliked</jstl:if>"><i class="fa fa-thumbs-down"></i></a>
            </div>
                </jstl:if>
    </article>
    <article class="col s11">
       <h2><spring:message code="summary"/></h2>
        <p>${recipe.summary}</p>
        <h2><spring:message code="steps"/> </h2>
        <ul class="step">
            <jstl:forEach var="step" items="${recipe.steps}"  varStatus="loop">
                <li><h3>Step ${loop.index+1}</h3>
                    <p>
                        <c:if test="${not empty step.picture}">
                            <img src="${step.picture}" width="80" height="80" />
                        </c:if>
                       ${step.description}</p>
                    <h4><spring:message code="hints"/></h4>
                    <p>${step.hints}</p>
                </li>
            </jstl:forEach>
        </ul>
    </article>
    <article class="comment-section">
        <h1><spring:message code="comments"/> </h1>
        <jstl:if test="${not empty comment}">
            <div class="writecomment">
            <form:form action="recipe/${recipe.id}.do" modelAttribute="comment" method="POST">
                <form:label path="title">
                    <spring:message code="title"/>
                </form:label>
                <form:input path="title"/>
                <form:errors cssClass="error" path="title"/>

                <form:hidden path="recipe"/>
                <form:hidden path="version" />
                <form:hidden path="author"/>
                <form:hidden path="id" />

                <form:label path="text">
                    <spring:message code="text"/>
                </form:label>
                <form:textarea path="text"/>
                <form:errors cssClass="error" path="text"/>

                <input type="submit" name="tocomment" value="<spring:message code="tocomment"/>"/>
            </form:form>
            </div>
        </jstl:if>

        <div class="comments">
            <jstl:forEach var="comment1" items="${recipe.comments}" varStatus="index">
                <div class="comment">
                    <h3>${comment1.title}</h3>
                    <div class="dateauthor">
                        <spring:message code="writtenby" /> <b>${comment1.author.userAccount.username}</b>
                    <spring:message code="at" /> ${comment1.created_at}
                    </div>
                    <div class="text">
                        ${comment1.text}
                    </div>
                    <jstl:set var="starrating" value="0"/>
                    <jstl:set var="numberstar" value="0"/>
                    <jstl:forEach items="${comment1.stars}" var="star" varStatus="index">
                        <jstl:set var="starrating" value="${starrating+star.stars}"/>
                        <jstl:set var="numberstar" value="${numberstar+1}"/>
                    </jstl:forEach>
                    <jstl:if test="${numberstar!=0}">
                    <jstl:set var="starrating" value="${starrating/numberstar}"/>
                    </jstl:if>
                    <div class="rate" data-comment="${comment1.id}" data-rating="${starrating}"></div>
                </div>
            </jstl:forEach>
        </div>

    </article>
</section>
<script>
    $(document).ready(function(){
        $(".rate").each(function(){
            var rating = $(this).data("rating");
            console.log(rating);
            $(this).rateYo({
                rating: rating,
                fullStar: true
                <jstl:if test="${empty comment}">
                ,readOnly: true
                </jstl:if>
            })
            $(this).rateYo()
                .on("rateyo.set", function (e, data) {

                   document.location.href = "${contextPath}/recipe/rate.do?commentId="+e.currentTarget.dataset.comment+"&stars="+data.rating;
                });
        })
    })
</script>





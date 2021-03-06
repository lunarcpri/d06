<%@page language="java" contentType="text/html; charset=ISO-8859-1"
        pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security"	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>

<h1><spring:message code="sponsor.registerheader"/> </h1>
<form:form action="sponsor/register.do" modelAttribute="sponsor" method="POST">

    <div class="form-group-1">
        <h2><spring:message code="sponsor.userinfo"/> </h2>
    <form:label path="userAccount.username">
        <spring:message code="userAccount.username" />:
    </form:label>
    <form:input path="userAccount.username" />
    <form:errors cssClass="error" path="userAccount.username" />

    <form:label path="userAccount.password">
        <spring:message code="userAccount.password" />:
    </form:label>
    <form:password path="userAccount.password" />
    <form:errors cssClass="error" path="userAccount.password" />
    <br>
    <form:errors cssClass="error" path="email" />
    </div>
    <h2><spring:message code="sponsor.personaldata"/> </h2>
    <div class="form-group-2">
    <form:label path="email">
        <spring:message code="email" />:
    </form:label>
    <form:input path="email" type="email" required="required"/>
    <form:errors cssClass="error" path="email" />
    <br>
    <form:label path="name">
        <spring:message code="name" />:
    </form:label>
    <form:input path="name" required="required" />
    <form:errors cssClass="error" path="name" />
    <br>
    <form:label path="surnames">
        <spring:message code="surnames" />:
    </form:label>
    <form:input path="surnames" required="required" />
    <form:errors cssClass="error"  path="surnames" />
    <br>
    </div>
    <div class="form-group-2">
    <form:label path="phone">
        <spring:message code="phone" />:
    </form:label>
    <form:input path="phone" pattern="((\+[0-9]{1,3})?\s*(\([0-9]{3}\))?\s*([a-zA-Z0-9\- ]{4,}))$" />
    <form:errors cssClass="error" path="phone" />
    <br>
    <form:label path="address">
        <spring:message code="address" />:
    </form:label>
    <form:input path="address" />
    <form:errors cssClass="error" path="address" />
    </div>
    <h2>Sponsor data</h2>
    <div class="form-group-1">
        <form:label path="company">
            <spring:message code="company" />:
        </form:label>
        <form:input path="company" required="required" />
        <form:errors cssClass="error" path="company" />

        <form:label path="creditCard.holder_name">
            <spring:message code="holder_name" />:
        </form:label>
        <form:input path="creditCard.holder_name" required="required" />
        <form:errors cssClass="error" path="creditCard.holder_name" />

        <form:label path="creditCard.brand_name">
            <spring:message code="brand_name" />:
        </form:label>
        <form:input path="creditCard.brand_name" required="required" />
        <form:errors cssClass="error" path="creditCard.brand_name" />

        <form:label path="creditCard.number">
            <spring:message code="number"  />:
        </form:label>
        <form:input  path="creditCard.number" required="required" pattern="[0-9\- ]*" />
        <form:errors cssClass="error" path="creditCard.number" />

        <form:label path="creditCard.expired_month">
            <spring:message code="expired_month" />:
        </form:label>
        <form:input type="number" path="creditCard.expired_month" required="required" min="1" max="12" />
        <form:errors cssClass="error" path="creditCard.expired_month" />

        <form:label path="creditCard.expired_year">
            <spring:message code="expired_year" />:
        </form:label>
        <form:input path="creditCard.expired_year" required="required" pattern="[0-9]*" />
        <form:errors cssClass="error" path="creditCard.expired_year" />

        <form:label path="creditCard.cvv">
            CCV
        </form:label>
        <form:input type="number" required="required" path="creditCard.cvv" min="100" max="999" />
        <form:errors cssClass="error" path="creditCard.cvv" />

    </div>
    <input name="register" type="submit" value="<spring:message code="sponsor.register" />" />
</form:form>
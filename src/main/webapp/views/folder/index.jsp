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
    <article class="col s12">
        <div class="messageView">
            <h1><spring:message code="folder.message"/> </h1>
        <h2><spring:message code="folder.subject" /></h2>
        <p>${message1.subject}</p>
        <h2><spring:message code="folder.recipients" /></h2>
        <p><jstl:forEach items="${message1.recipients}" var="recipient">
           ${recipient.userAccount.username},
        </jstl:forEach></p>
        <h2><spring:message code="folder.sendedat"/></h2>
        <p>${message1.sended_at}</p>
        <h2><spring:message code="folder.body"/> </h2>
        <p >${message1.body}</p>
        </div>
    </article>
</section>

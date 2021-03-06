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
    <article class="col s2 folder-list">
        <ul>
            <jstl:forEach items="${folders}" var="item">
            <li><a href="${contextPath}/message/list.do?folderId=${item.id}">${item.name}</a></li>
            </jstl:forEach>
        </ul>
    </article>
    <article class="col s9 message-list">
        <ul class="horizontal-list message-list-options">
            <li><a href="${contextPath}/message/new.do"><i class="fa fa-plus"></i><spring:message code="message.newbutton"/></a></li>
            <li><a href="${contextPath}/folder/list.do"><i class="fa fa-plus"></i><spring:message code="message.managefolders"/></a></li>
        </ul>
    <h1>${folder.name}</h1>
        <display:table pagesize="5" class="displaytag" keepStatus="true"
                       name="messageList" requestURI="${requestURI}" id="row">


            <spring:message code="message.subject" var="subjectHeader" />
            <display:column property="subject" title="${subjectHeader}" sortable="true" />

            <spring:message code="message.sender" var="senderHeader" />
            <display:column title="${senderHeader}">
               ${row.sender.userAccount.username}
            </display:column>
            <spring:message code="message.recipients" var="recipientsHeader" />
            <display:column title="${recipientsHeader}">
                <jstl:forEach items="${row.recipients}" var="recipient">
                  ${recipient.userAccount.username},
                </jstl:forEach>
            </display:column>
            <spring:message code="message.sendedat" var="sendedatHeader" />
            <display:column property="sended_at" title="${sendedatHeader}" sortable="true" format="{0,date,dd/MM/yyyy HH:mm}"/>
            <spring:message code="message.browse" var="browseHeader" />
            <display:column title="${browseHeader}">
                <a href="http://localhost:8080/message/${row.id}.do">${browseHeader}</a>
            </display:column>
            <spring:message code="message.delete" var="deleteHeader" />
            <display:column title="${deleteHeader}">
                <a href="http://localhost:8080/message/delete.do?messageId=${row.id}&folderId=${folder.id}">${deleteHeader}</a>
            </display:column>
            <spring:message code="message.move" var="moveHeader" />
            <display:column title="${moveHeader}">
                <select title="move" name="move" onchange="location = this.value;">
               <jstl:forEach items="${folders}" var="current_folder">
                   <option value="http://localhost:8080/message/move.do?messageId=${row.id}&folderId=${current_folder.id}">
                           ${current_folder.name}
                   </option>
               </jstl:forEach>
                </select>
            </display:column>
        </display:table>
    </article>
</section>


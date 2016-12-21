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
    <article class="col s12 message-list">

    <h1>Folder list</h1>
        <ul class="horizontal-list message-list-options">
            <li><a href="${contextPath}/folder/new.do"><i class="fa fa-plus"></i> New Folder</a></li>
        </ul>
        <display:table pagesize="5" class="displaytag" keepStatus="true"
                       name="folderList" requestURI="${requestURI}" id="row">


            <spring:message code="name" var="nameHeader" />
            <display:column property="name" title="${nameHeader}" sortable="true" />


            <spring:message code="edit" var="editHeader" />
                <display:column  title="${editHeader}">
                        <a href="${contextPath}/folder/edit.do?folderId=${row.id}">
                                ${editHeader}
                </display:column>
            <spring:message code="delete" var="deleteHeader"/>
            <display:column  title="${deleteHeader}">
                   <jstl:if test="${row.folderType=='CUSTOM'}">
                <a href="${contextPath}/folder/delete.do?folderId=${row.id}">
                        ${deleteHeader}
                            </jstl:if>
            </display:column>

                </display:table>

    </article>
</section>


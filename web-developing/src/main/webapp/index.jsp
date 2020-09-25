<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setBundle basename="messages"/>

<fmt:message key="add.name.button" var="addNewNameButton" />
<fmt:message key="update.name.button" var="updateNameButton" />

<html>
<head>
    <title><fmt:message key="welcome.page.title" /></title>
    <script src="https://code.jquery.com/jquery-1.10.2.js" type="text/javascript"></script>
    <script src="../js/jquery.cookie.js" type="text/javascript"></script>
    <script src="../js/ajax.js" type="text/javascript"></script>
</head>
<body>
    <h1 align="center"><fmt:message key="welcome.page.title" /></h1>
    <h2 align="center"><fmt:message key="visit.number.section.title" /><span id="js-visit-count-section"></span></h2>

    <h3><fmt:message key="ajax.get.request.title" /></h3>
    <a href="#" id="js-show-list"><fmt:message key="reload.names.list.link" /></a>
    <br>

    <h3><fmt:message key="ajax.post.request.title" /></h3>
    <form id="addUsername">
        <fmt:message key="enter.name.input.field" /> <input type="text" name="username" />
        <input type="submit" value="${addNewNameButton}" />
    </form>

    <h3><fmt:message key="ajax.put.request.title" /></h3>
    <form id="updateUsername">
        <input type="hidden" id="js-update-user-id-filed" name="id" />
        <fmt:message key="enter.old.name.input.field" /> <input type="text" id="js-update-old-name-filed" name="oldname" readonly /><br>
        <fmt:message key="update.section.description" />
        <br><br>
        <fmt:message key="enter.new.name.input.field" /> <input type="text" id="js-update-new-name-filed" name="newname" />
        <br><br>
        <input type="submit" value="${updateNameButton}" />
    </form>

    <h3><fmt:message key="ajax.response.section.title" /></h3>
    <div id="js-ajax-get-servlet-response"></div>

</body>
</html>
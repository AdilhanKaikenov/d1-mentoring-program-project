<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<head>
    <title><tiles:getAsString name="title"/></title>
</head>
<body>
<table width="100%" border="1">
    <tr>
        <td><tiles:insertAttribute name="header"/></td>
    </tr>
    <tr>
        <td>
            <c:if test="${not empty notification}" >
                <div><fmt:message key="${notification}" /></div>
            </c:if>

            <tiles:insertAttribute name="body"/>
        </td>
    </tr>
</table>
</body>
</html>
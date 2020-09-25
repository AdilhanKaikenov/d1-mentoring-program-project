<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<h2><fmt:message key="403.access.denied" /></h2>
<br>
<a href="${pageContext.request.contextPath}/home"><fmt:message key="back.link" /></a>
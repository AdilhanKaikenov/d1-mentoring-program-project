<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<fmt:message key="signin.button" var="signIn" />

<c:if test="${not empty error}" >
    <div>${error}</div>
</c:if>
<c:if test="${not empty logout}" >
    <div>${logout}</div>
</c:if>

<sec:authorize access="isAnonymous()">
    <form method="POST" action="/login">
        <table>
            <tr>
                <td><fmt:message key="username.field" /></td>
                <td><input name="username" type="text" /></td>
            </tr>
            <tr>
                <td><fmt:message key="password.field" /></td>
                <td><input name="password" type="password" /></td>
            </tr>
            <tr>
                <td><input name="submit" type="submit" value="${signIn}" /></td>
                <td><a href="/sign-up"><fmt:message key="signup.link"/></a></td>
            </tr>
        </table>
        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
    </form>
</sec:authorize>

<sec:authorize access="isAuthenticated()">
    <a href="/logout"><fmt:message key="logout.link" /></a>
</sec:authorize>
<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<fmt:message key="signup.link" var="signUp" />

<form:form method="POST" modelAttribute="userModel" action="/sign-up">

    <h1><fmt:message key="registration.page" /></h1>

    <table>
        <tr>
            <td><fmt:message key="username.field" /></td>
            <td><form:input path="username" type="text" /></td>
            <td><form:errors path="username" /></td>
        </tr>
        <tr>
            <td><fmt:message key="password.field" /></td>
            <td><form:input path="password" type="password" /></td>
            <td><form:errors path="password" /></td>
        </tr>
        <tr>
            <td><fmt:message key="address.field" /></td>
            <td><form:input path="address" type="text" /></td>
            <td><form:errors path="address" /></td>
        </tr>
        <tr>
            <td<fmt:message key="role.field" /></td>
            <td>
                <form:select path="role">
                    <form:options items="${roles}" />
                </form:select>
            </td>
        </tr>
        <tr>
            <td><input name="submit" type="submit" value="${signUp}" /></td>
        </tr>
    </table>

</form:form>
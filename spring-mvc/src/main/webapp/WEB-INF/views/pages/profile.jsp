<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<img src="<c:url value='/profile/photo/${userModel.photo}'/>" />

<form method="POST" action="/profile/${userModel.username}/uploadPhoto?${_csrf.parameterName}=${_csrf.token}" enctype="multipart/form-data">
    <table>
        <tr>
            <td><label><fmt:message key="select.file.to.upload" /><label></td>
            <td><input type="file" name="file" accept="image/jpeg" /></td>
        </tr>
        <tr>
            <td><input type="submit" value="Submit" /></td>
        </tr>
    </table>
</form>

<table>
        <tr>
            <td><fmt:message key="username.field" /></td>
            <td>${userModel.username}</td>
        </tr>
        <tr>
            <td><fmt:message key="address.field" /></td>
            <td>${userModel.address}</td>
        </tr>
        <tr>
            <td><fmt:message key="role.field" /></td>
            <td>${userModel.role}</td>
        </tr>
</table>


<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<fmt:message key="create.button" var="createButton" />

<form:form method="POST" modelAttribute="productItemModel" action="/product/create">

    <h1><fmt:message key="create.product.page" /></h1>

    <table>
        <tr>
            <td><fmt:message key="productName.field" /></td>
            <td><form:input path="productName" type="text" /></td>
            <td><form:errors path="productName" /></td>
        </tr>
        <tr>
            <td><fmt:message key="price.field" /></td>
            <td><form:input path="price" type="number" step="0.01" /></td>
            <td><form:errors path="price" /></td>
        </tr>
        <tr>
            <td><input name="submit" type="submit" value="${createButton}" /></td>
        </tr>
    </table>

</form:form>
<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:url var="homepageUrl" value="/home" />

<div align="center">
    <c:if test="${not empty productItems}">
    <table>
        <c:forEach items="${productItems}" var="product">
            <tr>
                <th>${product.productName}</th>
                <td>| <fmt:message key="price.field" /> ${product.price}</td>
                <td>| <a href="#">Order</a></td>
            </tr>
        </c:forEach>
    </table>
    </c:if>
</div>

<div align="center">
    <c:if test="${not empty paginationModel and paginationModel.pageCount ne 1}">
        <c:forEach begin="${paginationModel.firstPageIndex}" end="${paginationModel.pageCount}" varStatus="loop">
            <c:url var="pageUrl" value="${homepageUrl}">
                <c:param name="${paginationModel.pageParameterName}" value="${loop.index}" />
            </c:url>
            <a href="${pageUrl}">${loop.index}</a>
        </c:forEach>
    </c:if>
</div>
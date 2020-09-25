<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<c:url var="homepageUrl" value="/home" />
<c:url var="userProfileUrl" value="/profile/${currentUser.username}" />
<c:url var="createProductUrl" value="/product/create" />

<a href="${homepageUrl}"><fmt:message key="homepage.link" /></a>
<sec:authorize access="isAuthenticated()">
    <a href="${userProfileUrl}"><fmt:message key="profile.link" /></a>
</sec:authorize>
<sec:authorize access="hasRole('ADMIN')">
    <a href="${createProductUrl}"><fmt:message key="create.product.link" /></a>
</sec:authorize>

<span style="float: right">
    <tiles:insertDefinition name="authentication" /><br/><br/>
    <c:if test="${not empty cookie.username}">
        <b>Cookie username value: ${cookie.username.value}</b><br/>
    </c:if>
    <a href="?lang=en">ENG</a> | <a href="?lang=ru">RU</a>
</span>
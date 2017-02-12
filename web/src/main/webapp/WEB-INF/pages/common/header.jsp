<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<header class="header">
    <div class="container">
        <div class="row">
            <div class="avatar col-md-3 pull-left">
                <a href="<%=request.getContextPath()%>/"><p class="text-header"><spring:message code="form.home"/></p></a>

                <div >
                    <br>
                    <a href="?locale=ru" style="padding: 5px; color: red;">RU</a>
                    <a href="?locale=en" style="padding: 5px; color: red;">EN</a>
                </div>
            </div>

            <div class="col-md-9">

                <sec:authorize ifAnyGranted="ROLE_ANONYMOUS">
                    <a href="<%=request.getContextPath()%>/signin" style="margin-top:13px"  class="btn btn-success pull-right"><spring:message code="form.login"/></a>
                </sec:authorize>


                <sec:authorize ifAnyGranted="ROLE_USER">
                    <c:url var="logout" value="/j_spring_security_logout"/>
                    <a class="btn btn-success pull-right" href="${logout}" id="logOutButton" style="margin-top:13px"><spring:message code="form.logout"/></a>
                </sec:authorize>

            </div>
        </div>
    </div>
</header>


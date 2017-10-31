<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" isELIgnored="false" %>
<%@ taglib prefix="core" uri="http://java.sun.com/jsp/jstl/core" %>
<core:set var="ctx" value="${pageContext.request.contextPath}"/>
<html>
<head>
    <title>会话时间已过期</title>
    <link rel="stylesheet" type="text/css" href="${ctx}/css/error/error.css"/>
    <script type="text/javascript">
        function jump(count) {
            window.setTimeout(function () {
                count--;
                if (count > 0) {
                    document.getElementById("num").innerHTML = count;
                    jump(count);
                } else {
                    window.top.location.href = "${ctx }/login";
                }
            }, 1000);
        }
        window.onload = jump(5);

        function doClick() {
            window.top.location.href = "${ctx }/login";
        }
    </script>
</head>
<body scroll="no">
<div class="error">
    <div class="top">
        <div class="left"></div>
        <div class="right"></div>
        <div class="clear"></div>
    </div>
    <div class="middle">
        <div class="left"></div>
        <div class="center">
            <div class="img">访问失败</div>
            <br/>
            <br/> 错误信息： <br/>
            <br/>

            <div class="info">没有登录，或会话时间已过期，或已在别处登录</div>
            <br/> <span id="num">5</span>秒钟自动跳转到登录页面 <a
                href="javascript:void(0);" style="color: red;" onclick="doClick();">立即跳转到登录页面</a>
        </div>
        <div class="right"></div>
    </div>
    <div class="bottom">
        <div class="left"></div>
        <div class="center"></div>
        <div class="right"></div>
    </div>
</div>
</body>
</html>
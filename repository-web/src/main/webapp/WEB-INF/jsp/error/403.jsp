<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="core" uri="http://java.sun.com/jsp/jstl/core"%>
<core:set var="ctx" value="${pageContext.request.contextPath}"/>
<html>
	<head>
		<title>没有权限访问该页面</title>
		<link rel="stylesheet" type="text/css" href="${ctx}/css/error/error.css"/>
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
					<div class="img">403 error</div>
					<br/><br/>
					错误信息：
					<br/><br/>
					<div class="info">对不起，您没有权限访问该页面！</div><br/>
					error message：
					<br/><br/>
					<span style="padding-line:2px;font-size:13px;">
						Sorry, you do not have permission to access this page！
					</span>
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
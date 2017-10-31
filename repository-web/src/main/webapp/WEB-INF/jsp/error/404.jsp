<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="core" uri="http://java.sun.com/jsp/jstl/core" %>
<core:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="${ctx}/css/ace/ace.min.css" id="main-ace-style">
<link rel="stylesheet" href="${ctx}/css/skins.css">
<link rel="stylesheet" href="${ctx}/css/bootstrap/bootstrap.min.css">
<link rel="stylesheet" href="${ctx}/css/ace/font-awesome.min.css">
</head>
<body>
	<div class="error-container">
		<div class="well">
			<h1 class="grey lighter smaller">
				<span class="blue bigger-125"> <i class="ace-icon fa fa-sitemap"></i> 404</span> Page Not Found
			</h1>
			<hr>
			<h3 class="lighter smaller">We looked everywhere but we couldn't
				find it!</h3>
			<div>

				<div class="space"></div>
				<h4 class="smaller">Try one of the following:</h4>
				<ul class="list-unstyled spaced inline bigger-110 margin-15">
					<li><i class="ace-icon fa fa-hand-o-right blue"></i> Re-checkthe url for typos</li>
					<li><i class="ace-icon fa fa-hand-o-right blue"></i> Read the faq</li>
					<li><i class="ace-icon fa fa-hand-o-right blue"></i> Tell us about it</li>
				</ul>
			</div>
			<hr>
			<div class="space"></div>
			<div class="center">
				<a href="javascript:history.back()" class="btn btn-grey"> <i
					class="ace-icon fa fa-arrow-left"></i> Go Back
				</a> <a href="#" class="btn btn-primary"> <i
					class="ace-icon fa fa-tachometer"></i> Dashboard
				</a>
			</div>
		</div>
	</div>
</body>
</html>
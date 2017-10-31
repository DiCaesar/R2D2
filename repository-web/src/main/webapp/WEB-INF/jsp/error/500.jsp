<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false" isErrorPage="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
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
				<span class="blue bigger-125"> <i class="icon-random"></i>
					500
				</span> Something Went Wrong(Page Error!)
			</h1>

			<hr>
			<h3 class="lighter smaller">
				But we are working <i
					class="icon-wrench icon-animated-wrench bigger-125"></i> on it!
			</h3>

			<div class="space">
				<textarea type="hidden">
					${logId}
					${pageContext.exception}
					<c:forEach var="trace" items="${pageContext.exception.stackTrace}">
						${trace}
					</c:forEach>
				</textarea>
			</div>

			<div>
				<h4 class="lighter smaller">Meanwhile, try one of the
					following:</h4>

				<ul class="list-unstyled spaced inline bigger-110 margin-15">
					<li><i class="icon-hand-right blue"></i> Read the faq</li>

					<li><i class="icon-hand-right blue"></i> Give us more info on
						how this specific error occurred!</li>
				</ul>
			</div>

			<hr>
			<div class="space"></div>

			<div class="center">
				<a href="#" class="btn btn-grey"> <i class="icon-arrow-left"></i>
					Go Back
				</a> <a href="#" class="btn btn-primary"> <i class="icon-dashboard"></i>
					Dashboard
				</a>
			</div>
		</div>
	</div>
</body>
</html>
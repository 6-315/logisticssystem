<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>测试</title>
</head>
<body>
<form action="${pageContext.request.contextPath }/test/test_add">
1:<input name="w.test1" type="text">
2:<input name="w.test2" type="text">
<input type="submit" value="Add">
</form>
</body>
</html>
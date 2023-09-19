<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="frm" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>Cập nhật thông tin</title>
</head>
<body>
    <h1>Cập nhật tài khoản ${id}</h1>
    <frm:form action="/home/edit" method="post" modelAttribute="customer">
        <frm:input path="fullName" /> <br>
        <frm:input path="password" /> <br>
        <frm:input path="phone" /> <br>
      <button>Save</button>
    </frm:form>
</body>
</html>

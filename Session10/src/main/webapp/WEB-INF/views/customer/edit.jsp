<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="frm" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>Cập nhật khách hàng</title>
</head>
<body>
    <h1>Thông tin khách hàng</h1>
    <frm:form action="/customer/edit" enctype="multipart/form-data" method="post" modelAttribute="cus">
        <frm:input path="id" /> <br>
        <frm:input path="name" /> <br>
        <frm:input path="age" /> <br>
        <frm:input path="birthday" type="date" /> <br>
        <frm:input path="image" type="file" /> <br>
        <button>Save</button>
    </frm:form>
</body>
</html>

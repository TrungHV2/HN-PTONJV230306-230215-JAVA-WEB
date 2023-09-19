<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>Home</title>
</head>
<body>
    <h1>Xin chào ${ fullName }</h1>
    <h1>Danh sách khách hàng</h1>
    <table border="1" cellspacing="0" cellpadding="5" width="100%">
        <tr>
            <th>Họ tên</th>
            <th>Tuổi</th>
            <th>Ngày sinh</th>
            <th>Hình ảnh</th>
        </tr>
        <c:forEach var="c" items="${data}">
            <tr>
                <td>${c.name}</td>
                <td>${c.age}</td>
                <td>${c.birthday}</td>
                <td>${c.avatar}</td>
            </tr>
        </c:forEach>
    </table>
</body>
</html>

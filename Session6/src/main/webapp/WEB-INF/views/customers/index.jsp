<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <title>Danh sách khách hàng</title>
</head>
<body>
    <h1>Danh sách khách hàng</h1>
    <p><a href="?action=create">Thêm mới</a></p>
    <table border="1" cellpadding="5"cellspacing="0" width="100%">
        <tr>
            <th>Tên khách hàng</th>
            <th>Tuổi</th>
            <th>Ngày sinh</th>
            <th>Hình ảnh</th>
            <th>Hành động</th>
        </tr>
        <c:forEach items="${data}" var="c">
            <tr>
                <td>${c.name}</td>
                <td>${c.age}</td>
                <td><fmt:formatDate value="${c.birthday}" pattern="dd-MM-yyyy" /></td>
                <td><img src="${c.avatar}" alt="" width="75"></td>
                <td>
                    <a href="?action=edit&id=<c:out value="${c.id}" />">Sửa</a> |
                    <a href="?action=delete&id=<c:out value="${c.id}" />">Xóa</a>
                </td>
            </tr>
        </c:forEach>
    </table>
</body>
</html>

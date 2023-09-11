<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Danh sách danh mục</title>
</head>
<body>
    <h1>Danh sách danh mục</h1>
    <p><a href="?action=create">Thêm mới</a></p>
    <table border="1" cellpadding="5"cellspacing="0" width="100%">
        <tr>
            <th>Tên danh mục</th>
            <th>Danh mục cha</th>
            <th>Trạng thái</th>
            <th>Hành động</th>
        </tr>
        <c:forEach items="${data}" var="c">
            <tr>
                <td>${c.name}</td>
                <td>${c.parent}</td>
                <td>
                    <c:if test="${c.status == true}">
                        <c:out value="Hoạt động" />
                    </c:if>
                    <c:if test="${c.status == false}">
                        <c:out value="Không hoạt động" />
                    </c:if>
                </td>
                <td>
                    <a href="?action=edit&id=<c:out value="${c.id}" />">Sửa</a> |
                    <a href="?action=delete&id=<c:out value="${c.id}" />">Xóa</a>
                </td>
            </tr>
        </c:forEach>
    </table>
</body>
</html>

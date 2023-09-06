<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <title>Users</title>
</head>
<body>
    <h2>Danh sách tài khoản</h2>
    <p>
        <a href="create-user">Thêm mới</a>
    </p>
    <table border="1" cellpadding="5" cellspacing="0" width="100%">
        <tr>
            <th>Id</th>
            <th>Tài khoản</th>
            <th>Mật khẩu</th>
            <th>Quyền</th>
            <th>Ngày sinh</th>
            <th>Số dư</th>
            <th>Trạng thái</th>
            <th>Hành động</th>
        </tr>
        <c:forEach var="u" items="${ data }">
            <tr>
                <td>${u.id}</td>
                <td>${u.username}</td>
                <td>${u.password}</td>
                <td>
                    <c:choose>
                        <c:when test="${u.role == 1}">Admin</c:when>
                        <c:when test="${u.role == 2}">Employee</c:when>
                        <c:when test="${u.role == 3}">Customer</c:when>
                        <c:otherwise>Unknow</c:otherwise>
                    </c:choose>
                </td>
                <td><fmt:formatDate value="${u.birthday}" pattern="dd/MM/yyyy" /></td>
                <td><fmt:formatNumber value="${u.balance}" /></td>
                <td>
                    <c:if test="${u.status == true}">
                        <c:out value="Hoạt động" />
                    </c:if>
                    <c:if test="${u.status == false}">
                        <c:out value="Không hoạt động" />
                    </c:if>
                </td>
                <td>
                    <a href="/edit-user?id=<c:out value="${u.id}" />">Sửa</a>
                </td>
            </tr>
        </c:forEach>
    </table>
</body>
</html>

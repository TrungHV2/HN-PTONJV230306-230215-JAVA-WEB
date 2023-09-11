<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Cập nhật danh mục</title>
</head>
<body>
    <h1>Cập nhật danh mục</h1>
    <form method="POST">
        <input type="hidden" name="action" value="edit">
        <input type="hidden" name="txtId" value="${category.id}">
        <p><b>Tên danh mục: </b><input type="text" name="txtName" value="${category.name}"></p>
        <p><b>Danh mục cha: </b>
            <select name="txtParentId" id="">
                <option>Danh mục gốc</option>
                <c:forEach var="c" items="${data}">
                    <c:if test="${category.parentId == c.id}">
                        <option value="${c.id}" selected>${c.name}</option>
                    </c:if>
                    <c:if test="${category.parentId != c.id}">
                        <option value="${c.id}">${c.name}</option>
                    </c:if>
                </c:forEach>
            </select>
        </p>
        <p><b>Trạng thái: </b>
            <c:if test="${category.status == true}">
                <input type="radio" name="txtStatus" value="true" checked> Hoạt động
                <input type="radio" name="txtStatus" value="false"> Không động
            </c:if>
            <c:if test="${category.status == false}">
                <input type="radio" name="txtStatus" value="true"> Hoạt động
                <input type="radio" name="txtStatus" value="false" checked> Không động
            </c:if>

        </p>
        <button type="submit">Lưu</button>
    </form>
</body>
</html>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Thêm mới danh mục</title>
</head>
<body>
    <h1>Thêm mới danh mục</h1>
    <form method="POST">
      <input type="hidden" name="action" value="create">
      <p><b>Tên danh mục: </b><input type="text" name="txtName"></p>
      <p><b>Danh mục cha: </b>
        <select name="txtParentId" id="">
          <option>Danh mục gốc</option>
          <c:forEach var="c" items="${data}">
            <option value="${c.id}">${c.name}</option>
          </c:forEach>
        </select>
      </p>
      <p><b>Trạng thái: </b>
        <input type="radio" name="txtStatus" value="true"> Hoạt động
        <input type="radio" name="txtStatus" value="false"> Không động
      </p>
      <button type="submit">Thêm</button>
    </form>
</body>
</html>

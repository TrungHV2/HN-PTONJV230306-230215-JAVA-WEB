<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Thêm mới khách hàng</title>
</head>
<body>
    <h1>Thêm mới khách hàng</h1>
    <form method="POST">
        <input type="hidden" name="action" value="create">
        <p><b>Tên khách hàng: </b><input type="text" name="txtName"></p>
        <p><b>Tuổi: </b><input type="number" name="txtAge"></p>
        <p><b>Ngày sinh: </b><input type="date" name="txtBirthday"></p>
        <p><b>Hình ảnh: </b><input type="text" name="txtAvatar"></p>
        <button type="submit">Thêm</button>
    </form>
</body>
</html>

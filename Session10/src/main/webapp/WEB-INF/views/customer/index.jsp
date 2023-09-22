<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <title>Danh sách khách hàng</title>
</head>
<body>
  <h1>Danh sách khách hàng</h1>
  <table>
    <tr>
      <th>Mã KH</th>
      <th>Tên KH</th>
      <th>Tuổi</th>
      <th>Ngày sinh</th>
      <th>Ảnh đại diện</th>
      <th>Hành động</th>
    </tr>
    <c:forEach var="c" items="${data}">
      <tr>
        <td>${c.id}</td>
        <td>${c.name}</td>
        <td>${c.age}</td>
        <td><fmt:formatDate value="${c.birthday}" pattern="dd/MM/yyyy" /> </td>
        <td><img src="${c.avatar}" alt="" width="100"></td>
        <td>
          <a href="/customer/edit/${c.id}">Sửa</a>
        </td>
      </tr>
    </c:forEach>
  </table>
</body>
</html>

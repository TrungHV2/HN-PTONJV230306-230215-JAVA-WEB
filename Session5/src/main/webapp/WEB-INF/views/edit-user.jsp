<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <title>Cập nhật thông tin tài khoản</title>
</head>
<body>
  <h1>Cập nhật tài khoản</h1>
  <form method="post">
    <table>
      <tr>
        <td>Mã tài khoản</td>
        <td><input type="text" readonly name="id" value="<c:out value="${user.id}" />"></td>
      </tr>
      <tr>
        <td>Tên tài khoản</td>
        <td><input type="text" name="username" value="<c:out value="${user.username}" />"></td>
      </tr>
      <tr>
        <td>Mật khẩu</td>
        <td><input type="text" name="password" value="<c:out value="${user.password}" />"></td>
      </tr>
      <tr>
        <td>Quyền</td>
        <td><input type="text" name="role" value="<c:out value="${user.role}" />"></td>
      </tr>
      <tr>
        <td>Số dư</td>
        <td><input type="text" name="balance" value="<c:out value="${user.balance}" />"></td>
      </tr>
      <tr>
        <td>Ngày sinh</td>
        <td><input type="date" name="birthday" value="<fmt:formatDate value="${user.birthday}" pattern="yyyy-MM-dd" />"></td>
      </tr>
      <tr>
        <td>Trạng thái</td>
        <td>
          <c:if test="${user.status == true}">
            <input type="radio" name="status" checked value="true"> Hoạt động |
            <input type="radio" name="status" value="false"> Không hoạt động
          </c:if>
          <c:if test="${user.status == false}">
            <input type="radio" name="status" value="true"> Hoạt động |
            <input type="radio" name="status" checked value="false"> Không hoạt động
          </c:if>
        </td>
      </tr>
      <tr>
        <td>&nbsp;</td>
        <td><button type="submit">Lưu</button></td>
      </tr>
    </table>
  </form>
</body>
</html>

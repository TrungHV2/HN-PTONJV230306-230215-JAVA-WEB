<%--
  Created by IntelliJ IDEA.
  User: TRUNGHV
  Date: 9/6/2023
  Time: 8:40 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Thêm mới tài khoản</title>
</head>
<body>
  <h1>Thông tin tài khoản</h1>
  <form method="post">
    <table>
      <tr>
        <td>Mã tài khoản</td>
        <td><input type="text" name="id"></td>
      </tr>
      <tr>
        <td>Tên tài khoản</td>
        <td><input type="text" name="username"></td>
      </tr>
      <tr>
        <td>Mật khẩu</td>
        <td><input type="text" name="password"></td>
      </tr>
      <tr>
        <td>Quyền</td>
        <td><input type="text" name="role"></td>
      </tr>
      <tr>
        <td>Số dư</td>
        <td><input type="text" name="balance"></td>
      </tr>
      <tr>
        <td>Ngày sinh</td>
        <td><input type="date" name="birthday"></td>
      </tr>
      <tr>
        <td>Trạng thái</td>
        <td>
          <input type="radio" name="status" value="true"> Hoạt động |
          <input type="radio" name="status" value="false"> Không hoạt động
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

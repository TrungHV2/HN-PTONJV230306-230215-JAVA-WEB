package com.ra.web.model;


import javax.validation.constraints.*;

/**
 * @NotNull - kiểm tra giá trị null
 * @NotEmpty - kiểm tra không được trống và empty
 * @NotBlank - kiểm tra không null hoặc khoảng trắng
 * @Size - kiểm tra độ dài max, min
 * @Min - kiểm tra giá trị tối thiểu
 * @Max - kiểm tra giá trị đối đa
 * @Email - kiểm tra định dạng email
 * @Pattern - kiểm tra giá trị theo định dạng regex
 */
public class LoginModel {
    @NotEmpty(message = "Tài khoản không được bỏ trống!")
    private String username;
    @NotEmpty(message = "Mật khẩu không được bỏ trống!")
    private String password;

    public LoginModel(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public LoginModel() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}

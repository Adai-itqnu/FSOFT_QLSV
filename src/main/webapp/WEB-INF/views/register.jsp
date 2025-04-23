<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
    <title>Đăng Ký Sinh Viên</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 0 auto;
            padding: 20px;
            max-width: 600px;
            background-color: #f4f4f4;
        }
        h2, h3 {
            color: #333;
        }
        form {
            background-color: #fff;
            padding: 20px;
            border-radius: 5px;
            box-shadow: 0 0 10px rgba(0,0,0,0.1);
        }
        label {
            display: inline-block;
            width: 150px;
            margin-bottom: 10px;
        }
        input[type="text"], input[type="email"], select {
            width: 300px;
            padding: 5px;
            margin-bottom: 10px;
            border: 1px solid #ccc;
            border-radius: 3px;
        }
        input[type="submit"] {
            background-color: #4CAF50;
            color: white;
            padding: 10px 20px;
            border: none;
            border-radius: 3px;
            cursor: pointer;
        }
        input[type="submit"]:hover {
            background-color: #45a049;
        }
        .error {
            color: red;
            font-size: 14px;
        }
    </style>
</head>
<body>
<h2>Đăng Ký Sinh Viên Tốt Nghiệp</h2>
<c:if test="${not empty error}">
    <p class="error">${error}</p>
</c:if>
<form action="${pageContext.request.contextPath}/register" method="post">
    <h3>Thông Tin Sinh Viên</h3>
    <label>Số CMND:</label>
    <input type="text" name="soCMND" required/><br/>
    <label>Họ Tên:</label>
    <input type="text" name="hoTen"/><br/>
    <label>Email:</label>
    <input type="email" name="email"/><br/>
    <label>Số Điện Thoại:</label>
    <input type="text" name="soDT"/><br/>

    <h3>Thông Tin Tốt Nghiệp</h3>
    <label>Trường:</label>
    <select name="maTruong" required>
        <option value="">Chọn trường</option>
        <c:forEach var="truong" items="${truongList}">
            <option value="${truong.maTruong}">${truong.tenTruong}</option>
        </c:forEach>
    </select><br/>
    <label>Ngành:</label>
    <select name="maNganh" required>
        <option value="">Chọn ngành</option>
        <c:forEach var="nganh" items="${nganhList}">
            <option value="${nganh.maNganh}">${nganh.tenNganh}</option>
        </c:forEach>
    </select><br/>
    <label for="ngayTN">Ngày Tốt Nghiệp (yyyy-MM-dd):</label>
    <input type="text" id="ngayTN" name="ngayTN" required/><br/>

    <input type="submit" value="Đăng Ký"/>
</form>
</body>
</html>
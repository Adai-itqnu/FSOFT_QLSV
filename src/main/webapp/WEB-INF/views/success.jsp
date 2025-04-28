<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>Đăng Ký Thành Công</title>
     <!-- Bootstrap CSS -->
    <link href="${pageContext.request.contextPath}/resources/css/bootstrap.min.css" rel="stylesheet">
    
    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 0 auto;
            padding: 20px;
            max-width: 600px;
            background-color: #f4f4f4;
        }
        h2 {
            color: #333;
        }
        p {
            font-size: 16px;
        }
        a {
            display: inline-block;
            margin: 10px 0;
            padding: 10px 20px;
            background-color: #4CAF50;
            color: white;
            text-decoration: none;
            border-radius: 3px;
        }
        a:hover {
            background-color: #45a049;
        }
    </style>
</head>
<body>
<h2>Đăng Ký Thành Công!</h2>
<p>Thông tin sinh viên đã được lưu.</p>
<a href="${pageContext.request.contextPath}/register">Đăng ký thêm</a><br/>
<a href="${pageContext.request.contextPath}/searchBasic">Tìm kiếm cơ bản</a><br/>
<a href="${pageContext.request.contextPath}/searchDetails">Tìm kiếm chi tiết</a>
</body>
</html>
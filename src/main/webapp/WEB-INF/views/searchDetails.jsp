<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>Tìm Kiếm Chi Tiết</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 0 auto;
            padding: 20px;
            max-width: 1200px;
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
            margin-bottom: 20px;
        }
        label {
            display: inline-block;
            width: 150px;
        }
        input[type="text"] {
            width: 300px;
            padding: 5px;
            border: 1px solid #ccc;
            border-radius: 3px;
        }
        input[type="submit"] {
            background-color: #4CAF50;
            color: white;
            padding: 5px 10px;
            border: none;
            border-radius: 3px;
            cursor: pointer;
        }
        input[type="submit"]:hover {
            background-color: #45a049;
        }
        table {
            width: 100%;
            border-collapse: collapse;
            background-color: #fff;
            box-shadow: 0 0 10px rgba(0,0,0,0.1);
        }
        th, td {
            padding: 10px;
            border: 1px solid #ddd;
            text-align: left;
        }
        th {
            background-color: #4CAF50;
            color: white;
        }
    </style>
</head>
<body>
<h2>Tìm Kiếm Chi Tiết Sinh Viên</h2>
<form action="${pageContext.request.contextPath}/searchDetails" method="post">
    <label for="keyword">Từ khóa (Số CMND hoặc Họ Tên):</label>
    <input type="text" id="keyword" name="keyword"/>
    <input type="submit" value="Tìm kiếm"/>
</form>

<c:if test="${not empty results}">
    <h3>Kết Quả Tìm Kiếm</h3>
    <table>
        <tr>
            <th>Số CMND</th>
            <th>Họ Tên</th>
            <th>Email</th>
            <th>Số Điện Thoại</th>
            <th>Mã Trường</th>
            <th>Tên Trường</th>
            <th>Mã Ngành</th>
            <th>Tên Ngành</th>
            <th>Ngày Tốt Nghiệp</th>
            <th>Tên Công Ty</th>
            <th>Địa Chỉ Công Ty</th>
            <th>Vị Trí Công Việc</th>
        </tr>
        <c:forEach var="row" items="${results}">
            <tr>
                <td>${row[0]}</td>
                <td>${row[1]}</td>
                <td>${row[2]}</td>
                <td>${row[3]}</td>
                <td>${row[4]}</td>
                <td>${row[5]}</td>
                <td>${row[6]}</td>
                <td>${row[7]}</td>
                <td>${row[8]}</td>
                <td>${row[9]}</td>
                <td>${row[10]}</td>
                <td>${row[11]}</td>
            </tr>
        </c:forEach>
    </table>
</c:if>
</body>
</html>
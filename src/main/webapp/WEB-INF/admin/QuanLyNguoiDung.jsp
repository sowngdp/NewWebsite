<%--
  Created by IntelliJ IDEA.
  User: Sown
  Date: 12/17/2023
  Time: 2:13 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page import="com.news.QLLTC.model.NguoiDung" %>
<%@ page import="java.util.List" %>
<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<t:main-layout>
    <jsp:body>
        <div class="container">
            <div class="row">
                <div class="col-md-12">
                    <h1>Quản lý người dùng</h1>
                    <table class="table table-bordered">
                        <thead>
                        <tr>
                            <th>STT</th>
                            <th>Username</th>
                            <th>Password</th>
                            <th>Vai trò</th>
                        </tr>
                        </thead>
                        <tbody>
                        <jsp:useBean id="nguoiDungList" scope="request" type="java.util.List"/>
                        <c:forEach items="${listNguoiDung}" var="nguoiDung" varStatus="stt">
                            <tr>
                                <td>${nguoiDung.maNguoiDung}</td>
                                <td>${nguoiDung.tenDangNhap}</td>
                                <td>${nguoiDung.matKhau}</td>
                                <td>${nguoiDung.vaiTro}</td>
                                <td>
                                    <a href="nguoidung/edit/${nguoiDung.tenDangNhap}">Sửa</a>
                                    <a href="nguoidung/delete/${nguoiDung.tenDangNhap}">Xóa</a>
                                </td>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                    <a href="nguoidung/add">Thêm mới</a>
                </div>
            </div>
        </div>
    </jsp:body>
</t:main-layout>
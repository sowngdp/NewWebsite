<%@ tag import="com.news.QLLTC.model.NguoiDung" %>
<%@ tag import="com.news.QLLTC.model.VaiTro" %><%--
  Created by IntelliJ IDEA.
  User: Laffy
  Date: 12/12/2023
  Time: 1:12 PM
  To change this template use File | Settings | File Templates.
--%>
<%@tag description="Main layout" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bulma@0.9.4/css/bulma.min.css">
    <title>
        Tin vịt
    </title>
</head>
<body>
<%
    var user = (NguoiDung) session.getAttribute("nguoiDung");
    var userName = user == null ? "" : user.getTenDangNhap();
%>
<header id="pageheader">
    <nav class="navbar" role="navigation" aria-label="main navigation">
        <div class="navbar-brand">
            <a class="navbar-item" href="https://bulma.io">
                <img src="https://bulma.io/images/bulma-logo.png" width="112" height="28">
            </a>

            <a role="button" class="navbar-burger" aria-label="menu" aria-expanded="false"
               data-target="navbarBasicExample">
                <span aria-hidden="true"></span>
                <span aria-hidden="true"></span>
                <span aria-hidden="true"></span>
            </a>
        </div>

        <div id="navbarBasicExample" class="navbar-menu">
            <div class="navbar-start">
                <a class="navbar-item" href="${pageContext.request.contextPath}/">
                    Trang chủ
                </a>


                <% if (user !=null && !user.getVaiTro().name().equals(VaiTro.KHACH_HANG.name())) { %>
                <div class="navbar-item has-dropdown is-hoverable">
                    <a class="navbar-link">
                        Quản lý
                    </a>

                    <div class="navbar-dropdown">
                        <a class="navbar-item" href="${pageContext.request.contextPath}/admin/QuanLyGa/">
                            Quản lý Ga
                        </a>
                        <a class="navbar-item" href="${pageContext.request.contextPath}/admin/QuanLyTau/">
                            Quản lý Tàu
                        </a>
                        <a class="navbar-item" href="${pageContext.request.contextPath}/admin/QuanLyTuyenDi/">
                            Quản lý Tuyến Đi
                        </a>

                    </div>
                </div>
                <% } %>
            </div>

            <div class="navbar-end">
                <% if (user == null) { %>
                <div class="navbar-item">
                    <div class="buttons">
                        <a class="button is-primary" href="${pageContext.request.contextPath}/auth/register">
                            <strong>
                                Đăng ký
                            </strong>
                        </a>
                        <a class="button is-light" href="${pageContext.request.contextPath}/auth/login">
                            Đăng nhập
                        </a>
                    </div>
                </div>
                <% } else { %>
                <div class="navbar-item ">

                    <div class="buttons">
                        <a class="button is-white  mr-2" href="${pageContext.request.contextPath}/auth/profile">
                            <strong>
                                <%= userName %>
                            </strong>
                        </a>
                        <a class="button is-primary" href="${pageContext.request.contextPath}/auth/logout">
                            <strong>
                                Đăng xuất
                            </strong>
                        </a>
                    </div>
                </div>
                <% } %>
            </div>
        </div>
    </nav>
</header>

<main id="main" class="container" style="min-height: 120vh">
    <jsp:doBody/>
</main>

</body>
</html>

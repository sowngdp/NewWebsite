<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%
    var error = request.getAttribute("error");
%>
<t:main-layout>
    <jsp:body>
        <div class="card  mx-auto mt-6" style="width: 500px;">
            <header class="card-header">
                <p class="card-header-title is-size-2	">
                    Đăng nhập
                </p>
            </header>
            <div class="card-content" >
                <p class="help is-danger is-size-6 mb-2" ${error == null ? 'hidden' : ''}>
                   ${error}
                </p>
                <form action="${pageContext.request.contextPath}/auth/login" method="post">

                    <div class="field">
                        <label class="label">
                            Username
                        </label>
                        <div class="control">
                            <input class="input" type="text" placeholder="Nhập tên đăng nhập" name="tenDangNhap">
                        </div>
                    </div>
                    <div class="field">
                        <label class="label">
                            Password
                        </label>
                        <div class="control">
                            <input class="input" type="password" placeholder="Nhập mật khẩu" name="matKhau">
                        </div>
                    </div>

                    <div class="field is-grouped is-flex is-justify-content-flex-end">
                        <div class="control">
                            <button class="button is-link">
                                Đăng nhập
                            </button>
                        </div>

                    </div>
                </form>
            </div>
        </div>

    </jsp:body>
</t:main-layout>
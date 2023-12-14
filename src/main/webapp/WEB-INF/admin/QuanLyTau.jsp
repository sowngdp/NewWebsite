<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<t:admin-layout>
    <jsp:body>
        <div class="is-flex is-align-content-center is-justify-content-space-between">
            <h1 class="is-size-3 has-text-weight-bold">
                Quản lý tàu
            </h1>
            <a href="${pageContext.request.contextPath}/admin/QuanLyTau/them-tau" class="button is-primary">
                Thêm tàu
            </a>
        </div>
        <table class="table mt-2 mx-auto" style="width: 80%">
            <thead>
            <tr>
                <th>
                    Mã tàu
                </th>
                <th>
                    Tên tàu
                </th>
                <th>
                    Biển số
                </th>
                <th>
                    Hành động
                </th>


            </tr>
            </thead>


            <tbody>

            <jsp:useBean id="tauList" scope="request" type="java.util.List"/>
            <c:forEach items="${tauList}" var="tau" >
                <tr>
                    <td>
                            ${tau.maTau}
                    </td>
                    <td>
                            ${tau.tenTau}
                    </td>
                    <td>
                            ${tau.bienSo}
                    </td>
                    <td>
                        <div class="is-flex ">
                            <a href="${pageContext.request.contextPath}/admin/QuanLyTau/sua-tau?maTau=${tau.maTau}" class="button mr-1 is-warning">
                                Sửa
                            </a>

                            <form method="post" action="${pageContext.request.contextPath}/admin/QuanLyTau/xoa-tau"
                                  onsubmit="
                                          return confirm('Bạn có chắc chắn muốn xóa tau ${tau.tenTau} không?')"
                            >
                                <input type="hidden" name="maTau" value="${tau.maTau}">
                                <button class="button is-danger"
                                        type="submit"
                                >
                                    Xóa
                                </button>
                            </form>
                        </div>
                    </td>

                </tr>
            </c:forEach>

            </tbody>
        </table>
    </jsp:body>
</t:admin-layout>
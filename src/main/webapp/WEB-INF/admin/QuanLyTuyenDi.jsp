<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<t:admin-layout>
    <jsp:body>
        <div class="is-flex is-align-content-center is-justify-content-space-between">
            <h1 class="is-size-3 has-text-weight-bold">
                Quản lý tuyến đi
            </h1>
            <a href="${pageContext.request.contextPath}/admin/QuanLyTuyenDi/them-tuyen-di" class="button is-primary">
                Thêm tuyến đi
            </a>
        </div>
        <table class="table mt-2 mx-auto" style="width: 80%">
            <thead>
            <tr>
                <th>
                    Mã tuyến đi
                </th>
                <th>
                    Điểm đầu tuyến
                </th>
                <th>
                    Điểm cuối tuyến
                </th>
                <th>
                    Hành động
                </th>


            </tr>
            </thead>


            <tbody>

            <jsp:useBean id="tuyenDiList" scope="request" type="java.util.List"/>
            <c:forEach items="${tuyenDiList}" var="tuyenDi">
                <tr>
                    <td>
                            ${tuyenDi.maTuyenDi}
                    </td>
                    <td>
                            ${tuyenDi.gaDau.tenGa}
                    </td>
                    <td>
                            ${tuyenDi.gaCuoi.tenGa}
                    </td>
                    <td>
                        <div class="is-flex ">
                            <a href="${pageContext.request.contextPath}/admin/QuanLyTuyenDi/sua-tuyen-di?maTuyenDi=${tuyenDi.maTuyenDi}" class="button mr-1 is-warning">
                                Sửa
                            </a>

                            <form method="post" action="${pageContext.request.contextPath}/admin/QuanLyTuyenDi/xoa-tuyen-di"
                                  onsubmit="
                                          return confirm('Bạn có chắc chắn muốn xóa tuyenDi ${tuyenDi.maTuyenDi} không?')"
                            >
                                <input type="hidden" name="maTuyenDi" value="${tuyenDi.maTuyenDi}">
                                <button class="button is-danger"
                                        type="submit"
                                >
                                    Xóa
                                </button>
                            </form>
                            <a  href="${pageContext.request.contextPath}/admin/QuanLyLichTrinh/tao-lich-trinh?maTuyenDi=${tuyenDi.maTuyenDi}" class="button mr-1 is-link">
                                Quản lý lịch trình
                            </a>
                        </div>
                    </td>

                </tr>
            </c:forEach>

            </tbody>
        </table>
    </jsp:body>
</t:admin-layout>
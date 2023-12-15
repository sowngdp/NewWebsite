<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<t:admin-layout>

    <jsp:body>
        <script src="https://cdn.jsdelivr.net/npm/bulma-extensions@6.2.7/bulma-quickview/dist/js/bulma-quickview.min.js"></script>
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bulma-extensions@6.2.7/dist/css/bulma-extensions.min.css">
        <div class="is-flex is-align-content-center is-justify-content-space-between">
            <h1 class="is-size-3 has-text-weight-bold">
                Quản lý lịch trình của chuyến ${tuyenDi.maTuyenDi} (Từ ${ga.stream().filter(ga -> ga.maGa == tuyenDi.diemDau).findFirst().get().tenGa} đến ${ga.stream().filter(ga -> ga.maGa == tuyenDi.diemCuoi).findFirst().get().tenGa})
            </h1>
        </div>
        <table class="table mt-2 mx-auto" style="width: 80%">
            <thead>
            <tr>
                <th>
                    Mã lịch trình
                </th>
                <th>
                    Ga xuất phát
                </th>
                <th>
                    Ga đến
                </th>
                <th>
                    Giờ xuất phát
                </th>
                <th>
                    Giờ đến
                </th>
                <th>
                    Hành động
                </th>
            </tr>
            </thead>


            <tbody>

            <jsp:useBean id="lichTrinhs" scope="request" type="java.util.List"/>
            <c:forEach items="${lichTrinhs}" var="lich">
                <tr>
                    <td>
                            ${lich.maLichTrinh}
                    </td>
                    <td>
                            ${ga.stream().filter(ga -> ga.maGa == lich.maGaDi).findFirst().get().tenGa}
                    </td>
                    <td>
                            ${ga.stream().filter(ga -> ga.maGa == lich.maGaDen).findFirst().get().tenGa}
                    </td>
                    <td>
                            ${lich.ngayDi}
                    </td>
                    <td>
                            ${lich.ngayDen}
                    </td>
                    <td>
                        <button class="button is-info">

                            <span>Sửa</span>
                        </button>
                        <button class="button is-danger">

                            <span>Xóa</span>
                        </button>
                    </td>
                </tr>

            </c:forEach>

            </tbody>
        </table>
    </jsp:body>
</t:admin-layout>
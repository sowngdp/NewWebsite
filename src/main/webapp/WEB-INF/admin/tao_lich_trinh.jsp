<%@ page import="com.news.QLLTC.Utils" %>
<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <%
        var utils = new Utils();
    %>
<t:main-layout>

    <jsp:body>


        <script src="
https://cdn.jsdelivr.net/npm/bulma-calendar@6.1.19/dist/js/bulma-calendar.min.js
"></script>
        <link href="
https://cdn.jsdelivr.net/npm/bulma-calendar@6.1.19/dist/css/bulma-calendar.min.css
" rel="stylesheet">
        <style>
            .datetimepicker-wrapper.modal .datetimepicker {
                width: 31rem !important;
            }

            SPAN.td {
                display: table-cell;
            }

            div.td {
                display: table-cell;
            }

            form.tr, DIV.tr {
                display: table-row !important;
            }
        </style>
        <jsp:useBean id="utils" class="com.news.QLLTC.Utils"/>
        <div class="is-flex is-align-content-center is-justify-content-space-between">
            <h1 class="is-size-3 has-text-weight-bold">
                Quản lý lịch trình của chuyến ${tuyenDi.maTuyenDi}
                (Từ ${gaList.stream().filter(ga -> ga.maGa == tuyenDi.diemDau).findFirst().get().tenGa}
                đến ${gaList.stream().filter(ga -> ga.maGa == tuyenDi.diemCuoi).findFirst().get().tenGa})
                ngày ${utils.toDateTimeString(tuyenDi.thoiGianDi)}
                tàu ${tuyenDi.tau.bienSo}
            </h1>
        </div>
        <jsp:useBean id="gaList" type="java.util.List" scope="request"/>
        <table class="table mt-2 mx-auto" style="width: 80%">
            <thead>
            <tr>
                <th>
                    Mã
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
                            ${gaList.stream().filter(ga -> ga.maGa == lich.maGaDi).findFirst().get().tenGa}
                    </td>
                    <td>
                            ${gaList.stream().filter(ga -> ga.maGa == lich.maGaDen).findFirst().get().tenGa}
                    </td>
                    <td>
                            ${utils.toDateTimeString(lich.ngayDi)}
                    </td>
                    <td>
                            ${utils.toDateTimeString(lich.ngayDen)}
                    </td>
                    <td>
                      <div class="is-flex">
                          <button class="button mr-2 is-info">

                              <span>Sửa</span>
                          </button>
                          <form action="${pageContext.request.contextPath}/admin/QuanLyLichTrinh/xoa?maTuyenDi=${tuyenDi.maTuyenDi}"
                                method="post">
                              <input type="hidden" name="maLichTrinh" value="${lich.maLichTrinh}">
                              <button class="button is-danger" type="submit">

                                  <span>Xóa</span>
                              </button>
                          </form>
                      </div>
                    </td>
                </tr>

            </c:forEach>

            </tbody>


        </table>
        <form class=" is-flex mx-auto is-align-content-center is-justify-content-space-between"
              style="width: 80%;"
              action="${pageContext.request.contextPath}/admin/QuanLyLichTrinh/tao-lich-trinh?maTuyenDi=${tuyenDi.maTuyenDi}"
              method="post">

            <div style="width: 19%">
                <input type="hidden" name="maTuyenDi" value="${tuyenDi.maTuyenDi}">
                <div class="select">
                    <select required name="maDiemDau" id="maDiemDau"
                            value="${lichTrinhs.size() == 0 ? tuyenDi.diemDau : ''}"  ${lichTrinhs.size() == 0? 'readonly' : ''}>
                        <option value="">
                            Chọn điểm đầu tuyến
                        </option>
                        <c:forEach items="${gaList}" var="ga">
                            <option value="${ga.maGa}" ${lichTrinhs.size() == 0&&ga.maGa == tuyenDi.diemDau ? 'selected' : ''}>
                                    ${ga.tenGa}
                            </option>
                        </c:forEach>
                    </select>
                </div>
            </div>
            <div style="width: 19%">
                <div class="select">
                    <select required name="maDiemCuoi" id="maDiemCuoi">
                        <option value="">
                            Chọn điểm cuối tuyến
                        </option>
                        <c:forEach items="${gaList}" var="ga">
                            <option value="${ga.maGa}">
                                    ${ga.tenGa}
                            </option>
                        </c:forEach>
                    </select>
                </div>
            </div>
            <div style="width: 19%">
                <input type="datetime-local" name="ngayDi" id="ngayDi" required>
            </div>
            <div style="width: 19%">
                <input type="datetime-local" name="ngayDen" id="ngayDen" required>
            </div>
            <div style="width: 19%">
                <div class="is-flex is-justify-content-flex-end">
                    <button class="button  is-success" type="submit">

                        <span>Thêm lịch trình</span>
                    </button>
                </div>
            </div>
        </form>

        <script>
            // Initialize all input of type date
            var calendars = bulmaCalendar.attach('[type="datetime-local"]', {
                type: 'datetime',
                lang: 'vi',
                dateFormat: 'dd/MM/yyyy',
                timeFormat: 'HH:mm',
                minDate: new Date(),
                displayMode: 'dialog',
            });

            // Loop on each calendar initialized
            for (var i = 0; i < calendars.length; i++) {
                // Add listener to select event
                calendars[i].on('select', date => {
                    console.log(date);
                });
            }

            // To access to bulmaCalendar instance of an element
            // var element = document.querySelector('#my-element');
            // if (element) {
            //     // bulmaCalendar instance is available as element.bulmaCalendar
            //     element.bulmaCalendar.on('select', function(datepicker) {
            //         console.log(datepicker.data.value());
            //     });
            // }
        </script>
    </jsp:body>
</t:main-layout>
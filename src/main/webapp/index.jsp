<%@ page import="com.news.QLLTC.Utils" %>
<%@ page import="java.util.ArrayList" %>
<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
    var gaDi = request.getAttribute("gaDi");
    var gaDen = request.getAttribute("gaDen");
    var tauDi = request.getAttribute("tauDi");
    var ngayDi = request.getAttribute("ngayDi");

%>
<t:main-layout>
    <jsp:body>
        <jsp:useBean id="gaList" type="java.util.List" scope="request"/>
        <jsp:useBean id="tauList" type="java.util.List" scope="request"/>
     <jsp:useBean id="tuyenDis" type="java.util.List" scope="request"/>
        <script src="
https://cdn.jsdelivr.net/npm/bulma-calendar@6.1.19/dist/js/bulma-calendar.min.js
"></script>
        <link href="
https://cdn.jsdelivr.net/npm/bulma-calendar@6.1.19/dist/css/bulma-calendar.min.css
" rel="stylesheet">
        <jsp:useBean id="utils" class="com.news.QLLTC.Utils"/>
        <form style="width: 600px; " class="mx-auto mb-6" method="post" action="${pageContext.request.contextPath}/">
            <div class="field">
                <label class="label">
                    Ngày đi
                </label>
                <div class="control">
                    <input type="date" name="ngayDi" id="ngayDi" required value="${ngayDi}"/>
                </div>
            </div>
            <div class="field">
                <label class="label">
                    Tàu đi
                </label>
                <div class="control">
                    <div style="width: 100%" class="select">
                        <select style="width: 100%" name="tauDi" id="tauDi" required>
                            <option value="">Chọn tàu đi</option>
                            <c:forEach items="${tauList}" var="tau">
                                <option value="${tau.maTau}" ${tau.maTau == tauDi ? "selected" : ""}>
                                        ${tau.tenTau}</option>
                            </c:forEach>
                        </select>
                    </div>
                </div>

            </div>
            <div class="field">
                <label class="label">
                    Ga đi
                </label>
                <div class="control">
                    <div style="width: 100%" class="select">
                        <select style="width: 100%" name="gaDi" id="gaDi" required>
                            <option value="">Chọn ga đi</option>
                            <c:forEach items="${gaList}" var="ga">
                                <option value="${ga.maGa}" ${ga.maGa == gaDi ? "selected" : ""}>
                                        ${ga.tenGa}</option>
                            </c:forEach>
                        </select>
                    </div>
                </div>

            </div>
            <div class="field">
                <label class="label">
                    Ga đến
                </label>
                <div class="control">
                    <div style="width: 100%" class="select">
                        <select style="width: 100%" name="gaDen" id="gaDen"required>
                            <option value="">Chọn ga đến</option>
                            <c:forEach items="${gaList}" var="ga">
                                <option value="${ga.maGa}" ${ga.maGa == gaDen ? "selected" : ""}>
                                        ${ga.tenGa}</option>
                            </c:forEach>
                        </select>
                    </div>
                </div>

            </div>
            <div class="field is-grouped">
                <div class="control">
                    <button class="button is-link" type="submit">
                        Tìm kiếm
                    </button>
                </div>

            </div>
        </form>

            <c:forEach  items="${tuyenDis}" var="tuyenDi">
                <div class="is-flex is-align-content-center is-justify-content-space-between">
                    <h1 class="is-size-3 has-text-weight-bold">
                        Lịch trình của chuyến ${tuyenDi.maTuyenDi}
                        (Từ ${gaList.stream().filter(ga -> ga.maGa == tuyenDi.diemDau).findFirst().get().tenGa}
                        đến ${gaList.stream().filter(ga -> ga.maGa == tuyenDi.diemCuoi).findFirst().get().tenGa})
                        ngày ${utils.toDateTimeString(tuyenDi.thoiGianDi)}
                        tàu ${tuyenDi.tau.bienSo}

                    </h1>
                </div>
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

                    </tr>
                    </thead>


                    <tbody>

                    <c:forEach items="${tuyenDi.lichTrinhList}" var="lich">
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
                        </tr>

                    </c:forEach>

                    </tbody>
                </table>
            </c:forEach>
        <script>
            // Initialize all input of type date
            var calendars = bulmaCalendar.attach('[type="date"]', {
                type: 'date',
                lang: 'vi',
                dateFormat: 'dd/MM/yyyy',
                minDate: new Date(),
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
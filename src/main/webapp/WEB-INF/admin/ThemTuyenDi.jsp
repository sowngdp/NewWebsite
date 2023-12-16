<%@ page import="com.news.QLLTC.model.Tau" %>
<%@ page import="java.util.List" %>
<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
    var error = request.getAttribute("error");
    %>
<t:main-layout>
    <jsp:body>
        <script src="
https://cdn.jsdelivr.net/npm/bulma-calendar@6.1.19/dist/js/bulma-calendar.min.js
"></script>
        <link href="
https://cdn.jsdelivr.net/npm/bulma-calendar@6.1.19/dist/css/bulma-calendar.min.css
" rel="stylesheet">

        <h1 class="is-size-3 has-text-weight-bold">
            Tạo tuyến đi mới
        </h1>
        <jsp:useBean id="gaList" type="java.util.List" scope="request" />
        <form action="${pageContext.request.contextPath}/admin/QuanLyTuyenDi/them-tuyen-di" method="post">
            <p class="is-danger">
                ${error}
            </p>
            <div class="field">
                <label class="label" for="maDiemDau">
                    Điểm đầu tuyến
                </label>
                <div class="select">
                    <select required name="maDiemDau" id="maDiemDau">
                        <option value="">
                            Chọn điểm đầu tuyến
                        </option>
                        <c:forEach items="${gaList}" var="ga">
                            <option value="${ga.maGa}">
                                ${ga.tenGa}
                            </option>
                        </c:forEach>
                    </select>
                </div>
            </div>

            <div class="field">
                <label class="label" for="maDiemCuoi">
                    Điểm cuối tuyến
                </label>
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
            <jsp:useBean id="tauList" type="java.util.List" scope="request" />
            <div class="field">
                <label class="label" for="thoiGianDi">
                    Tàu đi
                </label>
                <div class="select">
                    <select required name="maTau" id="maTau">
                        <option value="">
                            Chọn tàu đi
                        </option>
                        <c:forEach items="${tauList}" var="tau">
                            <option value="${tau.maTau}">
                                ${tau.tenTau}
                            </option>
                        </c:forEach>

                    </select>
                </div>
            </div>
            <div class="field">
                <label class="label" for="thoiGianDi">
                   Thời gian đi
                </label>
                <input type="datetime-local" name="thoiGianDi" id="thoiGianDi" required>
            </div>
            <div class="field">
                <label class="label" for="thoiGianDen">
                    Thời gian đến
                </label>
                <input type="datetime-local" name="thoiGianDen" id="thoiGianDen" required>
            </div>


                <div class="field is-grouped">
                    <div class="control">
                        <button class="button is-link" type="submit">
                            Thêm tuyến đi
                        </button>
                    </div>

                </div>
        </form>
        <script>
            // Initialize all input of type date
            var calendars = bulmaCalendar.attach('[type="datetime-local"]',{
                type: 'datetime',
                lang: 'vi',
                dateFormat: 'dd/MM/yyyy',
                timeFormat: 'HH:mm',
                minDate: new Date(),
            } );

            // Loop on each calendar initialized
            for(var i = 0; i < calendars.length; i++) {
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
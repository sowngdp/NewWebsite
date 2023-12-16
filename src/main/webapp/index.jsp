<%@ page import="com.news.QLLTC.Utils" %>
<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<t:main-layout>
    <jsp:body>
        <jsp:useBean id="gaList" type="java.util.List" scope="request"/>
        <jsp:useBean id="tauList" type="java.util.List" scope="request"/>

        <script src="
https://cdn.jsdelivr.net/npm/bulma-calendar@6.1.19/dist/js/bulma-calendar.min.js
"></script>
        <link href="
https://cdn.jsdelivr.net/npm/bulma-calendar@6.1.19/dist/css/bulma-calendar.min.css
" rel="stylesheet">
        <jsp:useBean id="utils" class="com.news.QLLTC.Utils"/>
        <form style="width: 600px; " class="mx-auto" method="post" action="${pageContext.request.contextPath}/search">
            <div class="field">
                <label class="label">
                    Ngày đi
                </label>
                <div class="control">
                    <input type="date" name="ngayDi" id="ngayDi" required>
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
                                <option value="${tau.maTau}">${tau.tenTau}</option>
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
                                <option value="${ga.maGa}">${ga.tenGa}</option>
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
                                <option value="${ga.maGa}">${ga.tenGa}</option>
                            </c:forEach>
                        </select>
                    </div>
                </div>

            </div>
            <div class="field is-grouped">
                <div class="control">
                    <button class="button is-link">
                        Tìm kiếm
                    </button>
                </div>

            </div>
        </form>
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
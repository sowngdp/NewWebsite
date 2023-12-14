<%@ page import="com.news.QLLTC.model.Tau" %>
<%@ page import="java.util.List" %>
<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<t:admin-layout>
    <jsp:body>
        <h1 class="is-size-3 has-text-weight-bold">
            Tạo tuyến đi mới
        </h1>
        <jsp:useBean id="gaList" type="java.util.List" scope="request" />
        <form action="${pageContext.request.contextPath}/admin/QuanLyTuyenDi/them-tuyen-di" method="post">
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


                <div class="field is-grouped">
                    <div class="control">
                        <button class="button is-link" type="submit">
                            Thêm tuyến đi
                        </button>
                    </div>

                </div>
        </form>
    </jsp:body>
</t:admin-layout>
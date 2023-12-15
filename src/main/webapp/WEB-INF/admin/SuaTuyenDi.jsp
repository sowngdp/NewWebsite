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
        <jsp:useBean id="tuyenDi" type="com.news.QLLTC.model.TuyenDi" scope="request" />
        <form action="${pageContext.request.contextPath}/admin/QuanLyTuyenDi/sua-tuyen-di" method="post">
            <input type="hidden" name="maTuyenDi" value="${tuyenDi.maTuyenDi}" />
            <div class="field">
                <label class="label" for="maDiemDau">
                    Điểm đầu tuyến
                </label>
                <div class="select">
                    <select required name="maDiemDau" id="maDiemDau" value="${tuyenDi.diemDau}">
                        <option value="">
                            Chọn điểm đầu tuyến
                        </option>
                        <c:forEach items="${gaList}" var="ga">
                            <option value="${ga.maGa}" ${ga.maGa == tuyenDi.diemDau ? 'selected="selected"' : ''}>
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
                    <select required name="maDiemCuoi" id="maDiemCuoi" value="${tuyenDi.diemCuoi}">
                        <option value="">
                            Chọn điểm cuối tuyến
                        </option>
                        <c:forEach items="${gaList}" var="ga">
                            <option value="${ga.maGa}" ${ga.maGa == tuyenDi.diemCuoi ? 'selected="selected"' : ''}>
                                    ${ga.tenGa}
                            </option>
                        </c:forEach>
                    </select>
                </div>
            </div>


            <div class="field is-grouped">
                <div class="control">
                    <button class="button is-link" type="submit">
                        Lưu
                    </button>
                </div>
                <div class="control">
                    <button class="button is-light" type="reset">
                        Reset
                    </button>
                </div>
            </div>
        </form>
    </jsp:body>
</t:admin-layout>
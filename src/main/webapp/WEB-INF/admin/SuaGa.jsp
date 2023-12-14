<%@ page import="com.news.QLLTC.model.Ga" %>
<%@ page import="java.util.List" %>
<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
    String tenGaError = (String) request.getAttribute("tenGaError");
    String diaChiError = (String) request.getAttribute("diaChiError");

    Ga ga = (Ga) request.getAttribute("ga");
    if(ga.getDiaChi()==null){
        ga.setDiaChi("");
    }
%>
<t:admin-layout>
    <jsp:body>
        <h1 class="is-size-3 has-text-weight-bold">
            Tạo ga mới
        </h1>

        <form action="${pageContext.request.contextPath}/admin/QuanLyGa/sua-ga" method="post">
            <input type="hidden" name="maGa" value="${ga.maGa}"/>
            <div class="field">
                <label class="label" for="tenGa">
                    Tên ga
                </label>
                <div class="control">
                    <input
                            value="${ga.tenGa}"
                            class="input ${tenGaError != null ? 'is-danger' : ''}" type="text" placeholder="Nhập tên ga" name="tenGa" required id="tenGa">
                </div>
                <c:if test="${tenGaError != null}">
                    <p class="help is-danger">
                            ${tenGaError}
                    </p>
                </c:if>
            </div>
            <div class="field">
                <label class="label" for="diaChi" >
                    Địa chỉ ga
                </label>
                <div class="control">
                    <textarea
                            class="textarea ${diaChiError != null ? 'is-danger' : ''}"
                            placeholder="Nhập địa chỉ ga" name="diaChi" required  id="diaChi" rows="3"><c:out value="${ga.diaChi}"/></textarea>
                </div>
                <c:if test="${diaChiError != null}">
                    <p class="help is-danger">
                            ${diaChiError}
                    </p>
                </c:if>
            </div>
            <div class="field is-grouped">
                <div class="control">
                    <button class="button is-link" type="submit">
                      Lưu thay đổi
                    </button>
                </div>
                <div class="control">
                    <button class="button is-light" type="reset">
                       Đặt lại
                    </button>
                </div>
            </div>
        </form>
    </jsp:body>
</t:admin-layout>
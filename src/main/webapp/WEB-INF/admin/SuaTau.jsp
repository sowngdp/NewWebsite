<%@ page import="com.news.QLLTC.model.Tau" %>
<%@ page import="java.util.List" %>
<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
    String tenTauError = (String) request.getAttribute("tenTauError");
    String bienSoError = (String) request.getAttribute("bienSoError");

    Tau tau = (Tau) request.getAttribute("tau");
    if(tau.getBienSo() == null){
        tau.setBienSo("");
    }
%>
<t:main-layout>
    <jsp:body>
        <h1 class="is-size-3 has-text-weight-bold">
            Sửa lại thông tin tàu
        </h1>

        <form action="${pageContext.request.contextPath}/admin/QuanLyTau/sua-tau" method="post">
            <input type="hidden" name="maTau" value="${tau.maTau}"/>
            <div class="field">
                <label class="label" for="tenTau">
                    Tên tàu
                </label>
                <div class="control">
                    <input
                            value="${tau.tenTau}"
                            class="input ${tenTauError != null ? 'is-danger' : ''}" type="text" placeholder="Nhập tên tàu" name="tenTau" required id="tenTau">
                </div>
                <c:if test="${tenTauError != null}">
                    <p class="help is-danger">
                            ${tenTauError}
                    </p>
                </c:if>
            </div>
            <div class="field">
                <label class="label" for="bienSo" >
                    Biển số tàu
                </label>
                <div class="control">
                    <div class="control">
                        <input
                                value="${tau.bienSo}"
                                class="input ${bienSoError != null ? 'is-danger' : ''}" type="text" placeholder="Nhập biển số tàu" name="bienSo" required id="bienSo">
                    </div>

                </div>
                <c:if test="${bienSoError != null}">
                    <p class="help is-danger">
                            ${bienSoError}
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
</t:main-layout>
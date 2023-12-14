<%@ page import="com.news.QLLTC.model.Ga" %>
<%@ page import="java.util.List" %>
<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<t:admin-layout>
    <jsp:body>
        <div class="is-flex is-align-content-center is-justify-content-space-between">
            <h1 class="is-size-3 has-text-weight-bold">
                Quản lý ga tàu
            </h1>
            <a href="${pageContext.request.contextPath}/admin/QuanLyGa/them-ga" class="button is-primary">
                Thêm ga
            </a>
        </div>
        <table class="table mt-2 mx-auto" style="width: 80%">
            <thead>
            <tr>
                <th>
                    Mã ga
                </th>
                <th>
                    Tên ga
                </th>
                <th>
                    Địa chỉ
               </th>
                <th>
                   Hành động
                </th>


            </tr>
            </thead>


            <tbody>

            <jsp:useBean id="gaList" scope="request" type="java.util.List"/>
            <c:forEach items="${gaList}" var="ga">
                <tr>
                    <td>
                        ${ga.maGa}
                    </td>
                    <td>
                        ${ga.tenGa}
                    </td>
                    <td>
                        ${ga.diaChi}
                    </td>
                    <td>
                       <div class="is-flex ">
                           <a href="${pageContext.request.contextPath}/admin/QuanLyGa/sua-ga?maGa=${ga.maGa}" class="button mr-1 is-warning">
                               Sửa
                           </a>

                          <form method="post" action="${pageContext.request.contextPath}/admin/QuanLyGa/xoa-ga"
                          onsubmit="
                            return confirm('Bạn có chắc chắn muốn xóa ga ${ga.tenGa} không?')"
                          >
                                <input type="hidden" name="maGa" value="${ga.maGa}">
                                <button class="button is-danger"
                                type="submit"
                                >
                                    Xóa
                                </button>
                            </form>
                       </div>
                    </td>

                </tr>
            </c:forEach>

            </tbody>
        </table>
    </jsp:body>
</t:admin-layout>
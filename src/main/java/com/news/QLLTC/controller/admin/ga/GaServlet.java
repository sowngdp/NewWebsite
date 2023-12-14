package com.news.QLLTC.controller.admin.ga;

import com.news.QLLTC.model.Ga;
import com.news.QLLTC.service.GaService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "QuanLyGa", value = "/admin/QuanLyGa/*")
public class GaServlet extends HttpServlet {
    private final GaService gaService;

    public GaServlet() {
        this.gaService = new GaService();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        switch (request.getPathInfo()) {
            case "/them-ga":
                request.getRequestDispatcher("/WEB-INF/admin/ThemGa.jsp").forward(request, response);
                break;
            case "/sua-ga":
                int maGa = Integer.parseInt(request.getParameter("maGa"));
                Ga ga = this.gaService.timGa(maGa);
                request.setAttribute("ga", ga);
                request.getRequestDispatcher("/WEB-INF/admin/SuaGa.jsp").forward(request, response);
                break;

            case "/":
                List<Ga> gaList = this.gaService.layTatCaGa();
                request.setAttribute("gaList", gaList);
                request.getRequestDispatcher("/WEB-INF/admin/QuanLyGa.jsp").forward(request, response);
                break;
            default:
                // go to 404 page
                response.sendError(404);
                break;
        }
    }
    private  boolean validateGa(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String tenGa = request.getParameter("tenGa").trim();
        String diaChi = request.getParameter("diaChi").trim();
        if ( tenGa.length()<2) {
            request.setAttribute("tenGaError", "Tên ga phải dài hơn 2 ký tự");
            return false;
        }
        if ( diaChi.length()<10) {
            request.setAttribute("diaChiError", "Địa chỉ phải dài hơn 10 ký tự");
            return false;
        }
        return true;

    }
    private void validateAndCreateGa(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

     if (validateGa(request,response)){
         String tenGa = request.getParameter("tenGa").trim();
         String diaChi = request.getParameter("diaChi").trim();
         Ga ga = Ga.builder().tenGa(tenGa).diaChi(diaChi).build();
         this.gaService.taoGa(ga);
          redirectToGaListPage(request,response);
     } else {
         request.getRequestDispatcher("/WEB-INF/admin/ThemGa.jsp").forward(request, response);
     }
    }

    private void validateAndUpdateGa(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
if (validateGa(request,response)){
    int maGa = Integer.parseInt(request.getParameter("maGa"));
    String tenGa = request.getParameter("tenGa").trim();
    String diaChi = request.getParameter("diaChi").trim();
    Ga ga = Ga.builder().maGa(maGa).tenGa(tenGa).diaChi(diaChi).build();
    this.gaService.capNhatGa(ga);
    redirectToGaListPage(request,response);
}else {
    int maGa = Integer.parseInt(request.getParameter("maGa"));
    Ga ga = this.gaService.timGa(maGa);
    request.setAttribute("ga", ga);
    request.getRequestDispatcher("/WEB-INF/admin/SuaGa.jsp").forward(request, response);


}

    }

    private void deleteGa(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int maGa = Integer.parseInt(request.getParameter("maGa"));
        this.gaService.xoaGa(maGa);
        redirectToGaListPage(request,response);

    }

    private void redirectToGaListPage(HttpServletRequest req, HttpServletResponse response) throws IOException {

        var url = req.getContextPath() + "/admin/QuanLyGa/";
        response.sendRedirect(url);

    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        switch (request.getPathInfo()) {
            case "/them-ga":
                validateAndCreateGa(request,response);
                break;
            case "/sua-ga":
                validateAndUpdateGa(request,response);
                break;
            case "/xoa-ga":
                deleteGa(request,response);
                break;
            default:
                // go to 404 page
                response.sendError(404);
                break;
        }
    }
}

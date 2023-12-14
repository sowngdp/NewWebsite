package com.news.QLLTC.controller.admin.tau;

import com.news.QLLTC.model.Tau;
import com.news.QLLTC.service.TauService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "QuanLyTau", value = "/admin/QuanLyTau/*")
public class TauServlet extends HttpServlet {
    private final TauService tauService;

    public TauServlet() {
        this.tauService = new TauService();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        switch (request.getPathInfo()) {
            case "/them-tau":
                request.getRequestDispatcher("/WEB-INF/admin/ThemTau.jsp").forward(request, response);
                break;
            case "/sua-tau":
                int maTau = Integer.parseInt(request.getParameter("maTau"));
                Tau tau = this.tauService.timTau(maTau);
                request.setAttribute("tau", tau);
                request.getRequestDispatcher("/WEB-INF/admin/SuaTau.jsp").forward(request, response);
                break;

            case "/":
                List<Tau> tauList = this.tauService.layTatCaTau();
                request.setAttribute("tauList", tauList);
                request.getRequestDispatcher("/WEB-INF/admin/QuanLyTau.jsp").forward(request, response);
                break;
            default:
                // go to 404 page
                response.sendError(404);
                break;
        }
    }
    private  boolean validateTau(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String tenTau = request.getParameter("tenTau").trim();
        String bienSo = request.getParameter("bienSo").trim();
        if ( tenTau.length()<2) {
            request.setAttribute("tenTauError", "Tên tàu phải dài hơn 2 ký tự");
            return false;
        }
        if ( bienSo.length()<2) {
            request.setAttribute("bienSoError", "Biển số phải dài hơn 2 ký tự");
            return false;
        }
        return true;

    }
    private void validateAndCreateTau(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        if (validateTau(request,response)){
            String tenTau = request.getParameter("tenTau").trim();
            String bienSo = request.getParameter("bienSo").trim();
            Tau tau = Tau.builder().tenTau(tenTau).bienSo(bienSo).build();
            this.tauService.taoTau(tau);
            redirectToTauListPage(request,response);
        } else {
            request.getRequestDispatcher("/WEB-INF/admin/ThemTau.jsp").forward(request, response);
        }
    }

    private void validateAndUpdateTau(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (validateTau(request,response)){
            int maTau = Integer.parseInt(request.getParameter("maTau"));
            String tenTau = request.getParameter("tenTau").trim();
            String bienSo = request.getParameter("bienSo").trim();
            Tau tau = Tau.builder().maTau(maTau).tenTau(tenTau).bienSo(bienSo).build();
            this.tauService.capNhatTau(tau);
            redirectToTauListPage(request,response);
        }else {
            int maTau = Integer.parseInt(request.getParameter("maTau"));
            Tau tau = this.tauService.timTau(maTau);
            request.setAttribute("tau", tau);
            request.getRequestDispatcher("/WEB-INF/admin/SuaTau.jsp").forward(request, response);


        }

    }

    private void deleteTau(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int maTau = Integer.parseInt(request.getParameter("maTau"));
        this.tauService.xoaTau(maTau);
        redirectToTauListPage(request,response);

    }

    private void redirectToTauListPage(HttpServletRequest req, HttpServletResponse response) throws IOException {

        var url = req.getContextPath() + "/admin/QuanLyTau/";
        response.sendRedirect(url);

    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        switch (request.getPathInfo()) {
            case "/them-tau":
                validateAndCreateTau(request,response);
                break;
            case "/sua-tau":
                validateAndUpdateTau(request,response);
                break;
            case "/xoa-tau":
                deleteTau(request,response);
                break;
            default:
                // go to 404 page
                response.sendError(404);
                break;
        }
    }
}

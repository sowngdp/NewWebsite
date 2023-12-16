package com.news.QLLTC.controller;

import com.news.QLLTC.model.NguoiDung;
import com.news.QLLTC.model.VaiTro;
import com.news.QLLTC.service.NguoiDungService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(name = "authServlet", value = "/auth/*")
public class AuthServlet extends HttpServlet {
    private final NguoiDungService nguoiDungService;

    public AuthServlet() {
        this.nguoiDungService = new NguoiDungService();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        var pathInfo = req.getPathInfo();
        switch (pathInfo) {
            case "/login":
                if (req.getSession().getAttribute("nguoiDung") != null) {
                    resp.sendRedirect(req.getContextPath());
                    return;
                }
                req.getRequestDispatcher("/WEB-INF/login.jsp").forward(req, resp);
                break;
            case "/register":
                if (req.getSession().getAttribute("nguoiDung") != null) {
                    resp.sendRedirect(req.getContextPath());
                    return;
                }
                req.getRequestDispatcher("/WEB-INF/register.jsp").forward(req, resp);
                break;
            case "/logout":
                req.getSession().removeAttribute("nguoiDung");
                resp.sendRedirect(req.getContextPath() + "/auth/login");
                break;
            default:
                resp.sendError(HttpServletResponse.SC_NOT_FOUND);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        var pathInfo = req.getPathInfo();
        switch (pathInfo) {
            case "/login":
                var tenDangNhap = req.getParameter("tenDangNhap");
                var matKhau = req.getParameter("matKhau");
                try {
                    var nguoiDung = this.nguoiDungService.login(tenDangNhap, matKhau);
                    req.getSession().setAttribute("nguoiDung", nguoiDung);
                    resp.sendRedirect(req.getContextPath());
                } catch (Exception e) {
                    req.setAttribute("error", e.getMessage());
                    req.getRequestDispatcher("/WEB-INF/login.jsp").forward(req, resp);
                    return;
                }
                break;
            case "/register":
                try {
                    var tenDangNhap1 = req.getParameter("tenDangNhap");
                    var matKhau1 = req.getParameter("matKhau");
                    var nguoiDungObj = NguoiDung.builder().tenDangNhap(tenDangNhap1).matKhau(matKhau1).vaiTro(VaiTro.KHACH_HANG).build();
                    var nguoiDung1 = this.nguoiDungService.dangKy(nguoiDungObj);
                    req.getSession().setAttribute("nguoiDung", nguoiDung1);
                    resp.sendRedirect(req.getContextPath());
                } catch (Exception e) {
                    req.setAttribute("error", e.getMessage());
                    req.getRequestDispatcher("/WEB-INF/register.jsp").forward(req, resp);
                    return;
                }

                break;
            default:
                resp.sendError(HttpServletResponse.SC_NOT_FOUND);
        }
    }
}

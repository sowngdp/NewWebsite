package com.news.QLLTC.controller.admin.nguoidung;

import com.news.QLLTC.model.NguoiDung;
import com.news.QLLTC.service.NguoiDungService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "QuanLyNguoiDung", value = "/admin/QuanLyNguoiDung/*")
public class NguoiDungServlet extends HttpServlet {
    private final NguoiDungService nguoiDungService;

    public NguoiDungServlet() {
        this.nguoiDungService = new NguoiDungService();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        switch (request.getPathInfo()) {
            case "/them-nguoi-dung":
                request.getRequestDispatcher("/WEB-INF/admin/ThemNguoiDung.jsp").forward(request, response);
                break;
            case "/sua-nguoi-dung":
                int maNguoiDung = Integer.parseInt(request.getParameter("maNguoiDung"));
                NguoiDung nguoiDung = this.nguoiDungService.timNguoiDungTheoId(maNguoiDung);
                request.setAttribute("nguoiDung", nguoiDung);
                request.getRequestDispatcher("/WEB-INF/admin/SuaNguoiDung.jsp").forward(request, response);
                break;

            case "/":
                List<NguoiDung> nguoiDungList = this.nguoiDungService.layTatCaNguoiDung();
                request.setAttribute("nguoiDungList", nguoiDungList);
                request.getRequestDispatcher("/WEB-INF/admin/QuanLyNguoiDung.jsp").forward(request, response);
                break;
            default:
                // go to 404 page
                response.sendError(404);
                break;
        }
    }
}

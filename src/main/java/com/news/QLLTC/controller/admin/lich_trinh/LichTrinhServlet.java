package com.news.QLLTC.controller.admin.lich_trinh;

import com.news.QLLTC.model.Ga;
import com.news.QLLTC.service.GaService;
import com.news.QLLTC.service.LichTrinhService;
import com.news.QLLTC.service.TuyenDiService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(name = "lichTrinhServlet", value = "/admin/QuanLyLichTrinh/*")
public class LichTrinhServlet extends HttpServlet {
    private final LichTrinhService lichTrinhService;
    private final TuyenDiService tuyenDiService;
    private final GaService gaService;

    public LichTrinhServlet() {
        this.lichTrinhService = new LichTrinhService();
        this.tuyenDiService = new TuyenDiService();
        this.gaService = new GaService();
    }

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        var pathInfo = request.getPathInfo();
        var maTuyenDi = Integer.parseInt(request.getParameter("maTuyenDi"));
        var tuyenDi = tuyenDiService.timTuyenDiTheoId(maTuyenDi);
        var lichTrinhs = lichTrinhService.layLichTrinhTuTuyenDi(maTuyenDi);
        var ga = gaService.layTatCaGa();
        switch (pathInfo) {

            case "/tao-lich-trinh":


                    request.setAttribute("tuyenDi", tuyenDi);
                    request.setAttribute("ga", ga);
                    request.setAttribute("lichTrinhs", lichTrinhs);
                    request.getRequestDispatcher("/WEB-INF/admin/tao_lich_trinh.jsp").forward(request, response);

                break;
            case "/xoa":
                try {
                    var maLichTrinh = Integer.parseInt(request.getParameter("maLichTrinh"));
                    lichTrinhService.xoaLichTrinh(maLichTrinh);
                    response.sendRedirect("/admin/QuanLyLichTrinh");
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            default:


                    request.setAttribute("lichTrinhs", lichTrinhs);
                    request.getRequestDispatcher("/WEB-INF/admin/lich_trinh/lich_trinh.jsp").forward(request, response);

                break;
        }
    }
}

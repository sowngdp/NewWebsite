package com.news.QLLTC.controller.admin.lich_trinh;

import com.news.QLLTC.model.Ga;
import com.news.QLLTC.model.LichTrinh;
import com.news.QLLTC.service.GaService;
import com.news.QLLTC.service.LichTrinhService;
import com.news.QLLTC.service.TuyenDiService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.time.LocalDateTime;

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
                request.setAttribute("gaList", ga);
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

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        var pathInfo = req.getPathInfo();
        var maTuyenDi = Integer.parseInt(req.getParameter("maTuyenDi"));
        var tuyenDi = tuyenDiService.timTuyenDiTheoId(maTuyenDi);
        var lichTrinhs = lichTrinhService.layLichTrinhTuTuyenDi(maTuyenDi);
        var ga = gaService.layTatCaGa();
        req.setAttribute("tuyenDi", tuyenDi);
        req.setAttribute("gaList", ga);
        req.setAttribute("lichTrinhs", lichTrinhs);
        switch (pathInfo) {
            case "/tao-lich-trinh":
                try {
                    var maDiemDau = Integer.parseInt(req.getParameter("maDiemDau"));
                    var maDiemCuoi = Integer.parseInt(req.getParameter("maDiemCuoi"));
                    var ngayDiString = req.getParameter("ngayDi"); // dd/MM/yyyy HH:mm
                    var ngayDenString = req.getParameter("ngayDen");
                    LocalDateTime ngayDi = LocalDateTime.parse(ngayDiString, java.time.format.DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm"));
                    LocalDateTime ngayDen = LocalDateTime.parse(ngayDenString, java.time.format.DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm"));
                    var lichTrinh = LichTrinh.builder()
                            .maTuyenDi(maTuyenDi)
                            .maGaDi(maDiemDau)
                            .maGaDen(maDiemCuoi)
                            .ngayDi(ngayDi)
                            .ngayDen(ngayDen)
                            .build();
                    lichTrinhService.taoLichTrinh(lichTrinh);
                    resp.sendRedirect(req.getContextPath() + "/admin/QuanLyLichTrinh/tao-lich-trinh?maTuyenDi=" + maTuyenDi);
                } catch (Exception e) {
                    req.setAttribute("error", e.getMessage());
                    req.getRequestDispatcher("/WEB-INF/admin/tao_lich_trinh.jsp").forward(req, resp);
                }
                break;
            case "/sua-lich-trinh":
                try {
                    var maLichTrinh = Integer.parseInt(req.getParameter("maLichTrinh"));
                    var maDiemDau = Integer.parseInt(req.getParameter("maDiemDau"));
                    var maDiemCuoi = Integer.parseInt(req.getParameter("maDiemCuoi"));
                    var ngayDiString = req.getParameter("ngayDi"); // dd/MM/yyyy HH:mm
                    var ngayDenString = req.getParameter("ngayDen");
                    LocalDateTime ngayDi = LocalDateTime.parse(ngayDiString, java.time.format.DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm"));
                    LocalDateTime ngayDen = LocalDateTime.parse(ngayDenString, java.time.format.DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm"));
                    var lichTrinh = LichTrinh.builder()
                            .maLichTrinh(maLichTrinh)
                            .maTuyenDi(maTuyenDi)
                            .maGaDi(maDiemDau)
                            .maGaDen(maDiemCuoi)
                            .ngayDi(ngayDi)
                            .ngayDen(ngayDen)
                            .build();
                    lichTrinhService.suaLichTrinh(lichTrinh);
                    resp.sendRedirect(req.getContextPath() + "/admin/QuanLyLichTrinh/tao-lich-trinh?maTuyenDi=" + maTuyenDi);
                } catch (Exception e) {
                    req.setAttribute("error", e.getMessage());
                    req.getRequestDispatcher("/WEB-INF/admin/tao_lich_trinh.jsp").forward(req, resp);
                }
                break;
            case "/xoa":
                try {
                    var maLichTrinh = Integer.parseInt(req.getParameter("maLichTrinh"));
                    lichTrinhService.xoaLichTrinh(maLichTrinh);
                    resp.sendRedirect(req.getContextPath() + "/admin/QuanLyLichTrinh/tao-lich-trinh?maTuyenDi=" + maTuyenDi);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;

            default:
                resp.sendRedirect(req.getContextPath() + "/admin/QuanLyLichTrinh");
                break;
        }
    }
}

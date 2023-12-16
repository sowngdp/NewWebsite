package com.news.QLLTC.controller.admin.tuyen_di;

import com.news.QLLTC.model.Ga;
import com.news.QLLTC.model.Tau;
import com.news.QLLTC.model.TuyenDi;
import com.news.QLLTC.service.GaService;
import com.news.QLLTC.service.TauService;
import com.news.QLLTC.service.TuyenDiService;
import com.news.QLLTC.service.TuyenDiService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@WebServlet(name = "QuanLyTuyenDi", value = "/admin/QuanLyTuyenDi/*")
public class TuyenDiServlet extends HttpServlet {
    private final TuyenDiService tuyenDiService;
    private final GaService gaService;
    private final TauService tauService;

    public TuyenDiServlet() {
        this.tuyenDiService = new TuyenDiService();
        this.gaService = new GaService();
        this.tauService = new TauService();
    }

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Ga> gaList = this.gaService.layTatCaGa();
        request.setAttribute("gaList", gaList);
        List<Tau> tauList = this.tauService.layTatCaTau();
        request.setAttribute("tauList", tauList);
        switch (request.getPathInfo()) {
            case "/them-tuyen-di":

                request.getRequestDispatcher("/WEB-INF/admin/ThemTuyenDi.jsp").forward(request, response);
                break;
            case "/sua-tuyen-di":
                int maTuyenDi = Integer.parseInt(request.getParameter("maTuyenDi"));
                TuyenDi tuyenDi = this.tuyenDiService.timTuyenDiTheoId(maTuyenDi);
                request.setAttribute("tuyenDi", tuyenDi);
                request.getRequestDispatcher("/WEB-INF/admin/SuaTuyenDi.jsp").forward(request, response);
                break;

            case "/":
                List<TuyenDi> tuyenDiList = this.tuyenDiService.layTatCaTuyenDi();

                request.setAttribute("tuyenDiList", tuyenDiList);
                request.getRequestDispatcher("/WEB-INF/admin/QuanLyTuyenDi.jsp").forward(request, response);
                break;
            default:
                // go to 404 page
                response.sendError(404);
                break;
        }
    }

    private boolean validateTuyenDi(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        var thoiGianDiStr = request.getParameter("thoiGianDi"); // format dd/MM/yyyy HH:mm
        var thoiGianDenStr = request.getParameter("thoiGianDen"); // format dd/MM/yyyy HH:mm
        if (thoiGianDiStr.isBlank() || thoiGianDenStr.isBlank()) {
            request.setAttribute("error", "Thời gian đi và thời gian đến không được để trống");
            return false;
        }
        var thoiGianDi = LocalDateTime.parse(thoiGianDiStr, DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm"));
        var thoiGianDen = LocalDateTime.parse(thoiGianDenStr, DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm"));
        if (thoiGianDi.isAfter(thoiGianDen)) {
            request.setAttribute("error", "Thời gian đi phải trước thời gian đến");
            return false;
        }


        return true;

    }

    private void validateAndCreateTuyenDi(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        if (validateTuyenDi(request, response)) {
            var thoiGianDiStr = request.getParameter("thoiGianDi"); // format dd/MM/yyyy HH:mm
            var thoiGianDenStr = request.getParameter("thoiGianDen"); // format dd/MM/yyyy HH:mm
            var thoiGianDi = LocalDateTime.parse(thoiGianDiStr, DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm"));
            var thoiGianDen = LocalDateTime.parse(thoiGianDenStr, DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm"));

            int maDiemDau = Integer.parseInt(request.getParameter("maDiemDau"));
            int maDiemCuoi = Integer.parseInt(request.getParameter("maDiemCuoi"));
            int maTau = Integer.parseInt(request.getParameter("maTau"));
            TuyenDi tuyenDi = TuyenDi.builder().diemDau(maDiemDau).diemCuoi(maDiemCuoi).maTau(maTau).thoiGianDi(thoiGianDi).thoiGianDen(thoiGianDen).build();
            this.tuyenDiService.taoTuyenDi(tuyenDi);
            redirectToTuyenDiListPage(request, response);
        } else {
            request.getRequestDispatcher("/WEB-INF/admin/ThemTuyenDi.jsp").forward(request, response);
        }
    }

    private void validateAndUpdateTuyenDi(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (validateTuyenDi(request, response)) {
            var thoiGianDiStr = request.getParameter("thoiGianDi"); // format dd/MM/yyyy HH:mm
            var thoiGianDenStr = request.getParameter("thoiGianDen"); // format dd/MM/yyyy HH:mm
            var thoiGianDi = LocalDateTime.parse(thoiGianDiStr, DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm"));
            var thoiGianDen = LocalDateTime.parse(thoiGianDenStr, DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm"));
            int maTuyenDi = Integer.parseInt(request.getParameter("maTuyenDi"));
            int maDiemDau = Integer.parseInt(request.getParameter("maDiemDau"));
            int maDiemCuoi = Integer.parseInt(request.getParameter("maDiemCuoi"));
            int maTau = Integer.parseInt(request.getParameter("maTau"));
            TuyenDi tuyenDi = TuyenDi.builder().maTuyenDi(maTuyenDi).diemDau(maDiemDau).diemCuoi(maDiemCuoi).maTau(maTau).thoiGianDi(thoiGianDi).thoiGianDen(thoiGianDen).build();
            this.tuyenDiService.capNhatTuyenDi(tuyenDi);
            redirectToTuyenDiListPage(request, response);
        } else {
            int maTuyenDi = Integer.parseInt(request.getParameter("maTuyenDi"));
            TuyenDi tuyenDi = this.tuyenDiService.timTuyenDiTheoId(maTuyenDi);
            request.setAttribute("tuyenDi", tuyenDi);
            request.getRequestDispatcher("/WEB-INF/admin/SuaTuyenDi.jsp").forward(request, response);


        }

    }

    private void deleteTuyenDi(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int maTuyenDi = Integer.parseInt(request.getParameter("maTuyenDi"));
        this.tuyenDiService.xoaTuyenDi(maTuyenDi);
        redirectToTuyenDiListPage(request, response);

    }

    private void redirectToTuyenDiListPage(HttpServletRequest req, HttpServletResponse response) throws IOException {

        var url = req.getContextPath() + "/admin/QuanLyTuyenDi/";
        response.sendRedirect(url);

    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Ga> gaList = this.gaService.layTatCaGa();
        request.setAttribute("gaList", gaList);
        List<Tau> tauList = this.tauService.layTatCaTau();
        request.setAttribute("tauList", tauList);
        switch (request.getPathInfo()) {
            case "/them-tuyen-di":
                validateAndCreateTuyenDi(request, response);
                break;
            case "/sua-tuyen-di":
                validateAndUpdateTuyenDi(request, response);
                break;
            case "/xoa-tuyen-di":
                deleteTuyenDi(request, response);
                break;
            default:
                response.sendError(404);
                break;
        }
    }
}

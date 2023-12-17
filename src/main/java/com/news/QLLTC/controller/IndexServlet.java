package com.news.QLLTC.controller;

import java.io.*;
import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import com.news.QLLTC.service.GaService;
import com.news.QLLTC.service.LichTrinhService;
import com.news.QLLTC.service.TauService;
import com.news.QLLTC.service.TuyenDiService;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

@WebServlet(name = "indexServlet", value = "")
public class IndexServlet extends HttpServlet {
    private final LichTrinhService lichTrinhService;
    private final GaService gaService;
    private final TauService tauService;
    private final TuyenDiService tuyenDiService;

    public IndexServlet() {
        this.lichTrinhService = new LichTrinhService();
        this.gaService = new GaService();
        this.tauService = new TauService();
        this.tuyenDiService = new TuyenDiService();
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        var ga = gaService.layTatCaGa();
        var tau = tauService.layTatCaTau();
        request.setAttribute("gaList", ga);
        request.setAttribute("tauList", tau);
        request.setAttribute("tuyenDis",new ArrayList<>());
        request.getRequestDispatcher("/index.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        var ngayDi = req.getParameter("ngayDi"); // dd/MM/yyyy
        var dateFormater = new SimpleDateFormat("dd/MM/yyyy");
        Date date = null;
        try {
            date = new Date(dateFormater.parse(ngayDi).getTime());
        } catch (Exception e) {
            e.printStackTrace();
        }

        var gaDi = req.getParameter("gaDi");
        var gaDen = req.getParameter("gaDen");
        var tauDi = req.getParameter("tauDi");
        var tuyenDis = tuyenDiService.timTuyenDiTheoGa(date, Integer.parseInt(tauDi), Integer.parseInt(gaDi), Integer.parseInt(gaDen));
        req.setAttribute("tuyenDis", tuyenDis);
        var ga = gaService.layTatCaGa();
        var tau = tauService.layTatCaTau();
        req.setAttribute("gaList", ga);
        req.setAttribute("tauList", tau);
        req.getRequestDispatcher("/index.jsp").forward(req, resp);
    }
}
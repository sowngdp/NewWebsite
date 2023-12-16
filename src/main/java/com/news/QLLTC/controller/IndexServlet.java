package com.news.QLLTC.controller;

import java.io.*;

import com.news.QLLTC.service.GaService;
import com.news.QLLTC.service.LichTrinhService;
import com.news.QLLTC.service.TauService;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

@WebServlet(name = "indexServlet", value = "")
public class IndexServlet extends HttpServlet {
    private final LichTrinhService lichTrinhService;
    private final GaService gaService;
    private final TauService tauService;

    public IndexServlet() {
        this.lichTrinhService = new LichTrinhService();
        this.gaService = new GaService();
        this.tauService = new TauService();
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        var ga = gaService.layTatCaGa();
        var tau = tauService.layTatCaTau();
        request.setAttribute("gaList", ga);
        request.setAttribute("tauList", tau);
        request.getRequestDispatcher("/index.jsp").forward(request, response);
    }


}
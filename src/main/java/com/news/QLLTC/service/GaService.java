package com.news.QLLTC.service;

import com.news.QLLTC.common.HttpStatusException;
import com.news.QLLTC.model.Ga;
import com.news.QLLTC.repository.GaRepository;

import java.sql.SQLException;
import java.util.List;

public class GaService {
    private final GaRepository gaRepository;

    public GaService() {
        this.gaRepository = new GaRepository();
    }

    public List<Ga> layTatCaGa() {
        try {
            return this.gaRepository.timTatCaGa()   ;
        } catch (SQLException e) {
            throw new HttpStatusException(500, e.getMessage());
        }
    }

    public Ga timGa(int maGa) {
        try {
            return this.gaRepository.timGa(maGa);
        } catch (SQLException e) {
            throw new HttpStatusException(404, e.getMessage());
        }
    }

    public int taoGa(Ga ga) {
        try {
            return this.gaRepository.taoGa(ga);
        } catch (SQLException e) {
            throw new HttpStatusException(500, e.getMessage());
        }
    }

    public void xoaGa(int maGa) {
        try {
            this.gaRepository.xoaGa(maGa);
        } catch (SQLException e) {
            throw new HttpStatusException(500, e.getMessage());
        }
    }

    public void capNhatGa(Ga ga) {
        try {
            this.gaRepository.capNhatGa(ga);
        } catch (SQLException e) {
            throw new HttpStatusException(500, e.getMessage());
        }
    }




}

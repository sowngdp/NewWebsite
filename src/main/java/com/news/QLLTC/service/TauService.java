package com.news.QLLTC.service;

import com.news.QLLTC.common.HttpStatusException;
import com.news.QLLTC.model.Tau;
import com.news.QLLTC.repository.TauRepository;

import java.sql.SQLException;
import java.util.List;

public class TauService {
    private final TauRepository tauRepository;

    public TauService() {
        this.tauRepository = new TauRepository();
    }

    public List<Tau> layTatCaTau() {
        try {
            return this.tauRepository.timTatCaTau()   ;
        } catch (SQLException e) {
            throw new HttpStatusException(500, e.getMessage());
        }
    }

    public Tau timTau(int maTau) {
        try {
            return this.tauRepository.timTau(maTau);
        } catch (SQLException e) {
            throw new HttpStatusException(404, e.getMessage());
        }
    }

    public int taoTau(Tau tau) {
        try {
            return this.tauRepository.taoTau(tau);
        } catch (SQLException e) {
            throw new HttpStatusException(500, e.getMessage());
        }
    }

    public void xoaTau(int maTau) {
        try {
            this.tauRepository.xoaTau(maTau);
        } catch (SQLException e) {
            throw new HttpStatusException(500, e.getMessage());
        }
    }

    public void capNhatTau(Tau tau) {
        try {
            this.tauRepository.capNhatTau(tau);
        } catch (SQLException e) {
            throw new HttpStatusException(500, e.getMessage());
        }
    }




}

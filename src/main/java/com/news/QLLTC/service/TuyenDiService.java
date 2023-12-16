package com.news.QLLTC.service;

import com.news.QLLTC.common.HttpStatusException;
import com.news.QLLTC.model.TuyenDi;
import com.news.QLLTC.repository.TuyenDiRepository;

import java.sql.Date;
import java.sql.SQLException;
import java.util.List;

public class TuyenDiService {
    private final TuyenDiRepository tuyenDiRepository;
    private final LichTrinhService lichTrinhService;

    public TuyenDiService() {
        this.tuyenDiRepository = new TuyenDiRepository();
        this.lichTrinhService = new LichTrinhService();
    }

    public List<TuyenDi> layTatCaTuyenDi() {
        try {
            return this.tuyenDiRepository.timTatCaTuyenDi();
        } catch (SQLException e) {
            throw new HttpStatusException(500, e.getMessage());
        }
    }

    public TuyenDi timTuyenDiTheoId(int maTuyenDi) {
        try {
            return this.tuyenDiRepository.timTuyenDiTheoId(maTuyenDi);
        } catch (SQLException e) {
            throw new HttpStatusException(404, e.getMessage());
        }
    }

    public int taoTuyenDi(TuyenDi tuyenDi) {
        try {
            return this.tuyenDiRepository.taoTuyenDi(tuyenDi);
        } catch (SQLException e) {
            throw new HttpStatusException(500, e.getMessage());
        }
    }

    public void xoaTuyenDi(int maTuyenDi) {
        try {
            this.tuyenDiRepository.xoaTuyenDi(maTuyenDi);
        } catch (SQLException e) {
            throw new HttpStatusException(500, e.getMessage());
        }
    }

    public void capNhatTuyenDi(TuyenDi tuyenDi) {
        try {
            this.tuyenDiRepository.capNhatTuyenDi(tuyenDi);
        } catch (SQLException e) {
            throw new HttpStatusException(500, e.getMessage());
        }
    }

    public List<TuyenDi> timTuyenDiTheoGa(Date ngayDi, int maTau, int maGaDi, int maGaDen) {
        try {
            var ids = this.tuyenDiRepository.timLichTrinh(ngayDi, maTau, maGaDi, maGaDen);
            var tuyenDis = ids.stream().map(id -> {
                try {
                    var tuyenDi = this.tuyenDiRepository.timTuyenDiTheoId(id);
                    tuyenDi.setLichTrinhList(this.lichTrinhService.layLichTrinhTuTuyenDi(id));
                    return tuyenDi;
                } catch (SQLException e) {
                    throw new HttpStatusException(500, e.getMessage());
                }
            }).toList();
            return tuyenDis;
        } catch (SQLException e) {
            throw new HttpStatusException(500, e.getMessage());
        }

    }

}

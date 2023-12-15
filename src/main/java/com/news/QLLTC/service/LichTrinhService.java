package com.news.QLLTC.service;

import com.news.QLLTC.common.HttpStatusException;
import com.news.QLLTC.model.LichTrinh;
import com.news.QLLTC.repository.LichTrinhRepository;

import java.util.List;

public class LichTrinhService {
    private final LichTrinhRepository lichTrinhRepository;

    public LichTrinhService() {
        this.lichTrinhRepository = new LichTrinhRepository();
    }

    public int taoLichTrinh(LichTrinh lichTrinh) {
        try {
            return lichTrinhRepository.taoLichTrinh(lichTrinh);
        } catch (Exception e) {
            throw new HttpStatusException(500, e.getMessage());
        }
    }

    public void xoaLichTrinh(int maLichTrinh) {
        try {
            lichTrinhRepository.xoaLichTrinh(maLichTrinh);
        } catch (Exception e) {
            throw new HttpStatusException(500, e.getMessage());
        }
    }

    public List<LichTrinh> layLichTrinhTuTuyenDi(int maTuyenDi) {
        try {
            return lichTrinhRepository.layLichTrinhTuTuyenDi(maTuyenDi);
        } catch (Exception e) {
            throw new HttpStatusException(500, e.getMessage());
        }
    }

    public List<LichTrinh> layTatCaLichTrinh() {
        try {
            return lichTrinhRepository.layTatCaLichTrinh();
        } catch (Exception e) {
            throw new HttpStatusException(500, e.getMessage());
        }
    }
}

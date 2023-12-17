package com.news.QLLTC.service;

import com.news.QLLTC.common.HttpStatusException;
import com.news.QLLTC.model.NguoiDung;
import com.news.QLLTC.repository.NguoiDungRepository;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class NguoiDungService {
    private final NguoiDungRepository nguoiDungRepository;

    public NguoiDungService() {
        this.nguoiDungRepository = new NguoiDungRepository();
    }

    public NguoiDung login(String tenDangNhap, String matKhau) {
        NguoiDung nguoiDung = null;
        try {
            nguoiDung = this.nguoiDungRepository.findUserByTenDangNhap(tenDangNhap);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        if (nguoiDung == null) {
                throw new HttpStatusException(404, "Không tìm thấy người dùng");
        }
        if (!nguoiDung.getMatKhau().equals(matKhau)) {
            throw new HttpStatusException(400, "Sai mật khẩu");
        }
        return nguoiDung;

    }

    public NguoiDung dangKy(NguoiDung nguoiDung) {
        try {
            var nguoiDungDaTonTai = this.nguoiDungRepository.findUserByTenDangNhap(nguoiDung.getTenDangNhap());
            if (nguoiDungDaTonTai != null) {
                throw new HttpStatusException(400, "Tên đăng nhập đã tồn tại");
            }
            var maNguoiDung = this.nguoiDungRepository.createUser(nguoiDung);
            nguoiDung.setMaNguoiDung(maNguoiDung);
            return nguoiDung;
        } catch (SQLException e) {
            throw new HttpStatusException(500, e.getMessage());
        }
    }

    public void xoaNguoiDung(int maNguoiDung) {
        try {
            this.nguoiDungRepository.deleteUser(maNguoiDung);
        } catch (SQLException e) {
            throw new HttpStatusException(500, e.getMessage());
        }
    }

    public void capNhatNguoiDung(NguoiDung nguoiDung) {
        try {
            this.nguoiDungRepository.updateUser(nguoiDung);
        } catch (SQLException e) {
            throw new HttpStatusException(500, e.getMessage());
        }
    }

    public NguoiDung timNguoiDungTheoId(int maNguoiDung) {
        try {
            return this.nguoiDungRepository.findUserByMaNguoiDung(maNguoiDung);
        } catch (SQLException e) {
            throw new HttpStatusException(404, e.getMessage());
        }
    }

    public NguoiDung timNguoiDungTheoTenDangNhap(String tenDangNhap) {
        try {
            return this.nguoiDungRepository.findUserByTenDangNhap(tenDangNhap);
        } catch (SQLException e) {
            throw new HttpStatusException(404, e.getMessage());
        }
    }

    public List<NguoiDung> layTatCaNguoiDung() {
        try {
            return this.nguoiDungRepository.layTatCaNguoiDung();
        } catch (SQLException e) {
            throw new HttpStatusException(404, e.getMessage());
        }
    }
}

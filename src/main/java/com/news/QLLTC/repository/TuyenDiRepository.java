package com.news.QLLTC.repository;

import com.news.QLLTC.model.Ga;
import com.news.QLLTC.model.LichTrinh;
import com.news.QLLTC.model.Tau;
import com.news.QLLTC.model.TuyenDi;

import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class TuyenDiRepository {
//    public class TuyenDi {
//        private int maTuyenDi;
//        private int diemDau;
//        private int diemCuoi;
//        private Ga gaDau;
//        private Ga gaCuoi;
//        private int maTau;
//        private Tau tau;
//        private LocalDateTime thoiGianDi;
//        private LocalDateTime thoiGianDen;
//    }

    private final Connection connection;

    public TuyenDiRepository() {
        connection = DbConnection.getConnection();
    }

    public int taoTuyenDi(TuyenDi tuyenDi) throws SQLException {
        var sql = "INSERT INTO tuyen_di(diem_dau, diem_cuoi,maTau,thoiGianDi,thoiGianDen) VALUES (?, ?, ?, ?, ?)";
        var preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        preparedStatement.setInt(1, tuyenDi.getDiemDau());
        preparedStatement.setInt(2, tuyenDi.getDiemCuoi());
        preparedStatement.setInt(3, tuyenDi.getMaTau());
        preparedStatement.setObject(4, tuyenDi.getThoiGianDi());
        preparedStatement.setObject(5, tuyenDi.getThoiGianDen());
        preparedStatement.executeUpdate();
        var rs = preparedStatement.getGeneratedKeys();
        if (rs.next()) {
            return rs.getInt(1);
        }
        return 0;
    }

    public void xoaTuyenDi(int maTuyenDi) throws SQLException {
        var sql = "DELETE FROM tuyen_di WHERE ma_tuyen_di = ?";
        var preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1, maTuyenDi);
        preparedStatement.executeUpdate();
    }

    public void capNhatTuyenDi(TuyenDi tuyenDi) throws SQLException {
        var sql = "UPDATE tuyen_di SET diem_dau = ?, diem_cuoi = ?, maTau = ?, thoiGianDi = ?, thoiGianDen = ? WHERE ma_tuyen_di = ?";
        var preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1, tuyenDi.getDiemDau());
        preparedStatement.setInt(2, tuyenDi.getDiemCuoi());
        preparedStatement.setInt(3, tuyenDi.getMaTau());
        preparedStatement.setObject(4, tuyenDi.getThoiGianDi());
        preparedStatement.setObject(5, tuyenDi.getThoiGianDen());

        preparedStatement.executeUpdate();
    }

    public List<TuyenDi> timTatCaTuyenDi() throws SQLException {
        var sql = """
                        SELECT tuyen_di.ma_tuyen_di, tuyen_di.diem_dau, tuyen_di.diem_cuoi, gDen.tenGa as tenGaDen, gDi.tenGa as tenGaDi
                        , t.maTau, t.tenTau, t.bienSo, tuyen_di.thoiGianDi, tuyen_di.thoiGianDen
                        FROM tuyen_di
                        JOIN ga gDen on gDen.maGa = tuyen_di.diem_cuoi
                        JOIN ga gDi on gDi.maGa = tuyen_di.diem_dau
                        JOIN tau t on t.maTau = tuyen_di.maTau
                """;
        var preparedStatement = connection.prepareStatement(sql);
        var rs = preparedStatement.executeQuery();
        var tuyenDiList = new ArrayList<TuyenDi>();
        while (rs.next()) {
            var gaDi = Ga.builder()
                    .tenGa(rs.getString("tenGaDi"))
                    .maGa(rs.getInt("diem_dau"))
                    .build();
            var gaDen = Ga.builder()
                    .tenGa(rs.getString("tenGaDen"))
                    .maGa(rs.getInt("diem_cuoi"))
                    .build();
            var tau = Tau.builder()
                    .maTau(rs.getInt("maTau"))
                    .tenTau(rs.getString("tenTau"))
                    .bienSo(rs.getString("bienSo"))
                    .build();
            var tuyenDi = TuyenDi.builder()
                    .maTuyenDi(rs.getInt("ma_tuyen_di"))
                    .diemDau(rs.getInt("diem_dau"))
                    .diemCuoi(rs.getInt("diem_cuoi"))
                    .gaDau(gaDi)
                    .gaCuoi(gaDen)
                    .tau(tau)
                    .maTau(rs.getInt("maTau"))
                    .thoiGianDi(rs.getObject("thoiGianDi", LocalDateTime.class))
                    .thoiGianDen(rs.getObject("thoiGianDen", LocalDateTime.class))
                    .build();
            tuyenDiList.add(tuyenDi);
        }
        return tuyenDiList;
    }

    public TuyenDi timTuyenDiTheoId(int maTuyenDi) throws SQLException {
        var sql = "SELECT tuyen_di.ma_tuyen_di, tuyen_di.diem_dau, tuyen_di.diem_cuoi, gDen.tenGa as tenGaDen, gDi.tenGa as tenGaDi, t.maTau, t.tenTau, t.bienSo, tuyen_di.thoiGianDi, tuyen_di.thoiGianDen FROM tuyen_di JOIN ga gDen on gDen.maGa = tuyen_di.diem_cuoi JOIN ga gDi on gDi.maGa = tuyen_di.diem_dau JOIN tau t on t.maTau = tuyen_di.maTau WHERE ma_tuyen_di = ?";
        var preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1, maTuyenDi);
        var rs = preparedStatement.executeQuery();
        if (rs.next()) {
            var gaDi = Ga.builder()
                    .tenGa(rs.getString("tenGaDi"))
                    .maGa(rs.getInt("diem_dau"))
                    .build();
            var gaDen = Ga.builder()
                    .tenGa(rs.getString("tenGaDen"))
                    .maGa(rs.getInt("diem_cuoi"))
                    .build();
            var tau = Tau.builder()
                    .maTau(rs.getInt("maTau"))
                    .tenTau(rs.getString("tenTau"))
                    .bienSo(rs.getString("bienSo"))
                    .build();
            return TuyenDi.builder()
                    .maTuyenDi(rs.getInt("ma_tuyen_di"))
                    .diemDau(rs.getInt("diem_dau"))
                    .diemCuoi(rs.getInt("diem_cuoi"))
                    .gaDau(gaDi)
                    .gaCuoi(gaDen)
                    .tau(tau)
                    .maTau(rs.getInt("maTau"))
                    .thoiGianDi(rs.getObject("thoiGianDi", LocalDateTime.class))
                    .thoiGianDen(rs.getObject("thoiGianDen", LocalDateTime.class))
                    .build();
        }
        return null;
    }

    public List<Integer> timLichTrinh(Date ngayDi, int maTau, int maGaDi, int maGaDen) throws SQLException {
        var sql = """
                SELECT distinct tuyen_di.ma_tuyen_di
                FROM tuyen_di
                JOIN lich_trinh lt on lt.ma_tuyen_di = tuyen_di.ma_tuyen_di
                where ? between lt.ngay_di and lt.ngay_den
                and tuyen_di.maTau = ?
                and tuyen_di.diem_dau = ?
                and tuyen_di.diem_cuoi = ?
                       
                """;
        var preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setDate(1, ngayDi);
        preparedStatement.setInt(2, maTau);
        preparedStatement.setInt(3, maGaDi);
        preparedStatement.setInt(4, maGaDen);
        var rs = preparedStatement.executeQuery();
        var maTuyenDiList = new ArrayList<Integer>();
        while (rs.next()) {
            maTuyenDiList.add(rs.getInt("ma_tuyen_di"));
        }
        return maTuyenDiList;
    }
}

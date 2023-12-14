package com.news.QLLTC.repository;

import com.news.QLLTC.model.Ga;
import com.news.QLLTC.model.TuyenDi;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class TuyenDiRepository {
    private final Connection connection;

    public TuyenDiRepository() {
        connection = DbConnection.getConnection();
    }

    public int taoTuyenDi(TuyenDi tuyenDi) throws SQLException {
        var sql = "INSERT INTO tuyen_di(diem_dau, diem_cuoi) VALUES(?, ?)";
        var preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        preparedStatement.setInt(1, tuyenDi.getDiemDau());
        preparedStatement.setInt(2, tuyenDi.getDiemCuoi());
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
        var sql = "UPDATE tuyen_di SET diem_dau = ?, diem_cuoi = ? WHERE ma_tuyen_di = ?";
        var preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1, tuyenDi.getDiemDau());
        preparedStatement.setInt(2, tuyenDi.getDiemCuoi());
        preparedStatement.setInt(3, tuyenDi.getMaTuyenDi());
        preparedStatement.executeUpdate();
    }

    public List<TuyenDi> timTatCaTuyenDi() throws SQLException {
        var sql = """
                        SELECT tuyen_di.ma_tuyen_di, tuyen_di.diem_dau, tuyen_di.diem_cuoi, gDen.tenGa as tenGaDen, gDi.tenGa as tenGaDi
                        FROM tuyen_di
                        JOIN ga gDen on gDen.maGa = tuyen_di.diem_cuoi
                        JOIN ga gDi on gDi.maGa = tuyen_di.diem_dau
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

            var tuyenDi = TuyenDi.builder()
                    .maTuyenDi(rs.getInt("ma_tuyen_di"))
                    .diemDau(rs.getInt("diem_dau"))
                    .diemCuoi(rs.getInt("diem_cuoi"))
                    .gaDau(gaDi)
                    .gaCuoi(gaDen)
                    .build();
            tuyenDiList.add(tuyenDi);
        }
        return tuyenDiList;
    }

    public TuyenDi timTuyenDiTheoId(int maTuyenDi) throws SQLException {
        var sql = "SELECT tuyen_di.ma_tuyen_di, tuyen_di.diem_dau, tuyen_di.diem_cuoi, gDen.tenGa as tenGaDen, gDi.tenGa as tenGaDi FROM tuyen_di JOIN ga gDen on gDen.maGa = tuyen_di.diem_cuoi JOIN ga gDi on gDi.maGa = tuyen_di.diem_dau WHERE ma_tuyen_di = ?";
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
            var tuyenDi = TuyenDi.builder()
                    .maTuyenDi(rs.getInt("ma_tuyen_di"))
                    .diemDau(rs.getInt("diem_dau"))
                    .diemCuoi(rs.getInt("diem_cuoi"))
                    .gaDau(gaDi)
                    .gaCuoi(gaDen)
                    .build();
            return tuyenDi;
        }
        return null;
    }

    public TuyenDi timTuyenDiTheoDiemDauDiemCuoi(int diemDau, int diemCuoi) throws SQLException {
        var sql = "SELECT * FROM tuyen_di WHERE diem_dau = ? AND diem_cuoi = ?";
        var preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1, diemDau);
        preparedStatement.setInt(2, diemCuoi);
        var rs = preparedStatement.executeQuery();
        if (rs.next()) {
            return TuyenDi.builder()
                    .maTuyenDi(rs.getInt("ma_tuyen_di"))
                    .diemDau(rs.getInt("diem_dau"))
                    .diemCuoi(rs.getInt("diem_cuoi"))
                    .build();
        }
        return null;
    }


}

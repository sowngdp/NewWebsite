package com.news.QLLTC.repository;


import com.news.QLLTC.model.LichTrinh;

import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class LichTrinhRepository {
    private final Connection connection;
    public LichTrinhRepository() {
        connection = DbConnection.getConnection();
    }


    public int taoLichTrinh(LichTrinh lichTrinh) throws SQLException {
                var sql = "INSERT INTO lich_trinh(ngay_di, ngay_den, ma_ga_di, ma_ga_den, ma_tuyen_di) VALUES(?, ?, ?, ?, ?)";
                var preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
                preparedStatement.setObject(1, lichTrinh.getNgayDi());
                preparedStatement.setObject(2, lichTrinh.getNgayDen());
                preparedStatement.setInt(3, lichTrinh.getMaGaDi());
                preparedStatement.setInt(4, lichTrinh.getMaGaDen());
                preparedStatement.setInt(5, lichTrinh.getMaTuyenDi());
                preparedStatement.executeUpdate();
                var rs = preparedStatement.getGeneratedKeys();
                if (rs.next()) {
                    return rs.getInt(1);
                }
                return 0;

    }
    public void xoaLichTrinh(int maLichTrinh) throws SQLException {
        var sql = "DELETE FROM lich_trinh WHERE ma_lich_trinh = ?";
        var preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1, maLichTrinh);
        preparedStatement.executeUpdate();
    }
    public List<LichTrinh> layLichTrinhTuTuyenDi(int maTuyenDi) throws SQLException {
        var sql = "SELECT * FROM lich_trinh WHERE ma_tuyen_di = ?";
        var preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1, maTuyenDi);
        var rs = preparedStatement.executeQuery();
        var lichTrinhList = new ArrayList<LichTrinh>();
        while (rs.next()) {
            lichTrinhList.add(LichTrinh.builder()
                    .maLichTrinh(rs.getInt("ma_lich_trinh"))
                    .ngayDi(rs.getObject("ngay_di", LocalDateTime.class))
                    .ngayDen(rs.getObject("ngay_den", LocalDateTime.class))
                    .maGaDi(rs.getInt("ma_ga_di"))
                    .maGaDen(rs.getInt("ma_ga_den"))
                    .maTuyenDi(rs.getInt("ma_tuyen_di"))
                    .build());
        }
        return lichTrinhList;
    }

    public void capNhatLichTrinh(LichTrinh lichTrinh) throws SQLException {
        var sql = "UPDATE lich_trinh SET ngay_di = ?, ngay_den = ?, ma_ga_di = ?, ma_ga_den = ?, ma_tuyen_di = ? WHERE ma_lich_trinh = ?";
        var preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setObject(1, lichTrinh.getNgayDi());
        preparedStatement.setObject(2, lichTrinh.getNgayDen());
        preparedStatement.setInt(3, lichTrinh.getMaGaDi());
        preparedStatement.setInt(4, lichTrinh.getMaGaDen());
        preparedStatement.setInt(5, lichTrinh.getMaTuyenDi());
        preparedStatement.setInt(6, lichTrinh.getMaLichTrinh());
        preparedStatement.executeUpdate();
    }
    public LichTrinh timLichTrinh(int maLichTrinh) throws SQLException {
        var sql = "SELECT * FROM lich_trinh WHERE ma_lich_trinh = ?";
        var preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1, maLichTrinh);
        var rs = preparedStatement.executeQuery();
        if (rs.next()) {
            return LichTrinh.builder()
                    .maLichTrinh(rs.getInt("ma_lich_trinh"))
                    .ngayDi(rs.getObject("ngay_di", LocalDateTime.class))
                    .ngayDen(rs.getObject("ngay_den", LocalDateTime.class))
                    .maGaDi(rs.getInt("ma_ga_di"))
                    .maGaDen(rs.getInt("ma_ga_den"))
                    .maTuyenDi(rs.getInt("ma_tuyen_di"))
                    .build();
        }
        return null;
    }

   public List<LichTrinh> layTatCaLichTrinh() throws SQLException {
        var sql = "SELECT * FROM lich_trinh";
        var preparedStatement = connection.prepareStatement(sql);
        var rs = preparedStatement.executeQuery();
        var lichTrinhList = new ArrayList<LichTrinh>();
        while (rs.next()) {
            lichTrinhList.add(LichTrinh.builder()
                    .maLichTrinh(rs.getInt("ma_lich_trinh"))
                    .ngayDi(rs.getObject("ngay_di", LocalDateTime.class))
                    .ngayDen(rs.getObject("ngay_den", LocalDateTime.class))
                    .maGaDi(rs.getInt("ma_ga_di"))
                    .maGaDen(rs.getInt("ma_ga_den"))
                    .maTuyenDi(rs.getInt("ma_tuyen_di"))
                    .build());
        }
        return lichTrinhList;
    }



}

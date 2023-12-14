package com.news.QLLTC.repository;

import com.news.QLLTC.model.Ga;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class GaRepository {
    private final Connection connection;

    public GaRepository() {
        connection = DbConnection.getConnection();
    }

    public int taoGa(Ga ga) throws SQLException {
        var sql = "INSERT INTO ga(tenGa, diaChi) VALUES(?, ?)";
            var preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, ga.getTenGa());
            preparedStatement.setString(2, ga.getDiaChi());
           preparedStatement.executeUpdate();
            var rs = preparedStatement.getGeneratedKeys();
            if (rs.next()) {
                return rs.getInt(1);
            }
        return 0;
    }

    public void xoaGa(int maGa) throws SQLException {
        var sql = "DELETE FROM ga WHERE maGa = ?";
        var preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1, maGa);
        preparedStatement.executeUpdate();
    }

    public void capNhatGa(Ga ga) throws SQLException {
        var sql = "UPDATE ga SET tenGa = ?, diaChi = ? WHERE maGa = ?";
        var preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, ga.getTenGa());
        preparedStatement.setString(2, ga.getDiaChi());
        preparedStatement.setInt(3, ga.getMaGa());
        preparedStatement.executeUpdate();
    }

    public Ga timGa(int maGa) throws SQLException {
        var sql = "SELECT * FROM ga WHERE maGa = ?";
        var preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1, maGa);
        var rs = preparedStatement.executeQuery();
        if (rs.next()) {
            return Ga.builder()
                    .maGa(rs.getInt("maGa"))
                    .tenGa(rs.getString("tenGa"))
                    .diaChi(rs.getString("diaChi"))
                    .build();
        }
        return null;
    }

    public List<Ga> timTatCaGa() throws SQLException {
        var sql = "SELECT * FROM ga";
        var preparedStatement = connection.prepareStatement(sql);
        var rs = preparedStatement.executeQuery();
        var gaList = new ArrayList<Ga>();
        while (rs.next()) {
            gaList.add(Ga.builder()
                    .maGa(rs.getInt("maGa"))
                    .tenGa(rs.getString("tenGa"))
                    .diaChi(rs.getString("diaChi"))
                    .build());
        }
        return gaList;
    }

    public List<Ga> timGaTheoTen(String tenGa) throws SQLException {
        var sql = "SELECT * FROM ga WHERE tenGa LIKE ?";
        var preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, "%" + tenGa + "%");
        var rs = preparedStatement.executeQuery();
        var gaList = new ArrayList<Ga>();
        while (rs.next()) {
            gaList.add(Ga.builder()
                    .maGa(rs.getInt("maGa"))
                    .tenGa(rs.getString("tenGa"))
                    .diaChi(rs.getString("diaChi"))
                    .build());
        }
        return gaList;
    }
}

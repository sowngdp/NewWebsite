package com.news.QLLTC.repository;

import com.news.QLLTC.model.Tau;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class TauRepository {
    private final Connection connection;

    public TauRepository() {
        connection = DbConnection.getConnection();
    }

    public int taoTau(Tau tau) throws SQLException {
        var sql = "INSERT INTO tau(tenTau, bienSo) VALUES(?, ?)";
        var preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        preparedStatement.setString(1, tau.getTenTau());
        preparedStatement.setString(2, tau.getBienSo());
        preparedStatement.executeUpdate();
        var rs = preparedStatement.getGeneratedKeys();
        if (rs.next()) {
            return rs.getInt(1);
        }
        return 0;
    }

    public void xoaTau(int maTau) throws SQLException {
        var sql = "DELETE FROM tau WHERE maTau = ?";
        var preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1, maTau);
        preparedStatement.executeUpdate();
    }

    public void capNhatTau(Tau tau) throws SQLException {
        var sql = "UPDATE tau SET tenTau = ?, bienSo = ? WHERE maTau = ?";
        var preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, tau.getTenTau());
        preparedStatement.setString(2, tau.getBienSo());
        preparedStatement.setInt(3, tau.getMaTau());
        preparedStatement.executeUpdate();
    }

    public Tau timTau(int maTau) throws SQLException {
        var sql = "SELECT * FROM tau WHERE maTau = ?";
        var preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1, maTau);
        var rs = preparedStatement.executeQuery();
        if (rs.next()) {
            return Tau.builder()
                    .maTau(rs.getInt("maTau"))
                    .tenTau(rs.getString("tenTau"))
                    .bienSo(rs.getString("bienSo"))
                    .build();
        }
        return null;
    }

    public List<Tau> timTatCaTau() throws SQLException {
        var sql = "SELECT * FROM tau";
        var preparedStatement = connection.prepareStatement(sql);
        var rs = preparedStatement.executeQuery();
        var tauList = new ArrayList<Tau>();
        while (rs.next()) {
           tauList.add(Tau.builder()
                    .maTau(rs.getInt("maTau"))
                    .tenTau(rs.getString("tenTau"))
                    .bienSo(rs.getString("bienSo"))
                    .build());

        }
        return tauList;
    }


    public List<Tau> timTauTheoTen(String tenTau) throws SQLException {
        var sql = "SELECT * FROM tau WHERE tenTau LIKE ?";
        var preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, "%" + tenTau + "%");
        var rs = preparedStatement.executeQuery();
        var tauList = new ArrayList<Tau>();
        while (rs.next()) {
            tauList.add(Tau.builder()
                    .maTau(rs.getInt("maTau"))
                    .tenTau(rs.getString("tenTau"))
                    .bienSo(rs.getString("bienSo"))
                    .build());
    }
        return tauList;
    }
}

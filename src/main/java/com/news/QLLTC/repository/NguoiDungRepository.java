package com.news.QLLTC.repository;

import com.news.QLLTC.model.NguoiDung;
import com.news.QLLTC.model.VaiTro;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


public class NguoiDungRepository {

    private final Connection connection;

    public NguoiDungRepository() {
        connection = DbConnection.getConnection();
    }
    public int createUser(NguoiDung nguoiDung) throws SQLException {
        var sql = "INSERT INTO nguoi_dung(tenDangNhap, matKhau, vaiTro) VALUES(?, ?, ?)";
        var preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        preparedStatement.setString(1, nguoiDung.getTenDangNhap());
        preparedStatement.setString(2, nguoiDung.getMatKhau());
        preparedStatement.setString(3, nguoiDung.getVaiTro().toString());
        preparedStatement.executeUpdate();
        var rs = preparedStatement.getGeneratedKeys();
        if (rs.next()) {
            return rs.getInt(1);
        }
        return 0;
    }

    public void deleteUser(int maNguoiDung) throws SQLException {
        var sql = "DELETE FROM nguoi_dung WHERE maNguoiDung = ?";
        var preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1, maNguoiDung);
        preparedStatement.executeUpdate();
    }

    public void updateUser(NguoiDung nguoiDung) throws SQLException {
        var sql = "UPDATE nguoi_dung SET tenDangNhap = ?, matKhau = ?, vaiTro = ? WHERE maNguoiDung = ?";
        var preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, nguoiDung.getTenDangNhap());
        preparedStatement.setString(2, nguoiDung.getMatKhau());
        preparedStatement.setString(3, nguoiDung.getVaiTro().toString());
        preparedStatement.setInt(4, nguoiDung.getMaNguoiDung());
        preparedStatement.executeUpdate();
    }


    public NguoiDung findUserByTenDangNhap(String tenDangNhap) throws SQLException {
        var sql = "SELECT * FROM nguoi_dung WHERE tenDangNhap = ?";
        var preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, tenDangNhap);
        var rs = preparedStatement.executeQuery();
        if (rs.next()) {
            var vaitroString = rs.getString("vaiTro");
            return NguoiDung.builder()
                    .maNguoiDung(rs.getInt("maNguoiDung"))
                    .tenDangNhap(rs.getString("tenDangNhap"))
                    .matKhau(rs.getString("matKhau"))
                    .vaiTro(VaiTro.valueOf(rs.getString("vaiTro")))
                    .build();
        }
        return null;
    }

    public NguoiDung findUserByMaNguoiDung(int maNguoiDung) throws SQLException {
        var sql = "SELECT * FROM nguoi_dung WHERE maNguoiDung = ?";
        var preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1, maNguoiDung);
        var rs = preparedStatement.executeQuery();
        if (rs.next()) {
            return NguoiDung.builder()
                    .maNguoiDung(rs.getInt("maNguoiDung"))
                    .tenDangNhap(rs.getString("tenDangNhap"))
                    .matKhau(rs.getString("matKhau"))
                    .vaiTro(VaiTro.valueOf(rs.getString("vaiTro")))
                    .build();
        }
        return null;
    }

    public void xoaNguoiDung(int maNguoiDung) throws SQLException {
        var sql = "DELETE FROM nguoi_dung WHERE maNguoiDung = ?";
        var preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1, maNguoiDung);
        preparedStatement.executeUpdate();
    }

    public List<NguoiDung> layTatCaNguoiDung() throws SQLException {
        var sql = "SELECT * FROM nguoi_dung";
        var preparedStatement = connection.prepareStatement(sql);
        var rs = preparedStatement.executeQuery();
        var nguoiDungList = new ArrayList<NguoiDung>();
        while (rs.next()) {
            nguoiDungList.add(NguoiDung.builder().maNguoiDung(rs.getInt("maNguoiDung"))
                    .tenDangNhap(rs.getString("tenDangNhap"))
                    .matKhau(rs.getString("matKhau"))
                    .vaiTro(VaiTro.valueOf(rs.getString("vaiTro")))
                    .build());
        }
        return nguoiDungList;
    }


}

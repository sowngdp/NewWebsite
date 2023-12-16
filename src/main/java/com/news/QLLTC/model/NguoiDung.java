package com.news.QLLTC.model;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class NguoiDung {
    private int maNguoiDung;
    private String tenDangNhap;
    private String matKhau;
    private VaiTro vaiTro = VaiTro.KHACH_HANG;

}

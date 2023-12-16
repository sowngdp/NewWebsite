package com.news.QLLTC.model;

import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TuyenDi {
    private int maTuyenDi;
    private int diemDau;
    private int diemCuoi;
    private Ga gaDau;
    private Ga gaCuoi;
    private int maTau;
    private Tau tau;
    private LocalDateTime thoiGianDi;
    private LocalDateTime thoiGianDen;
    private List<LichTrinh> lichTrinhList;
}

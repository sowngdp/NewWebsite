package com.news.QLLTC.model;

import lombok.*;

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
}

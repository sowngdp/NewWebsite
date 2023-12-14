package com.news.QLLTC.model;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LichTrinh {
    private int maLichTrinh;
    private LocalDateTime ngayDi;
    private LocalDateTime ngayDen;
    private int maGaDi;
    private int maGaDen;
    private int maTuyenDi;


}

package com.mysite.sitebackend.board.stockMarket.domain;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Data
public class StockMarketBoard {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(length = 200)
    private String subject;
    private String contents;
    @Column(length = 20)
    private String author; // 작성자명
    @DateTimeFormat(pattern = "YYYYMMDD")
    private String date; // 작성일자
    private Integer views; // 조회수
}

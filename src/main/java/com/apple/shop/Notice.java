package com.apple.shop;

import jakarta.persistence.*;


@Entity
public class Notice {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;
    public String text;
    public String date;
}

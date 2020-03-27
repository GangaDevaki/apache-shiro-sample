package com.bny.esg.app.entity;

import lombok.Data;

import javax.persistence.*;


@Data
@Entity
public class UserRole {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;

    private Long userId;

    private Long roleId;


}

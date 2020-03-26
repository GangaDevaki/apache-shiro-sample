package com.bny.esg.entity;

import lombok.Data;

import javax.persistence.*;


@Data
@Entity
public class UserOrgRole {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;

    private Long userId;

    private Long roleId;


}

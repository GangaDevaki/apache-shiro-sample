package com.bny.esg.app.entity;

import lombok.Data;

import javax.persistence.*;


@Data
@Entity
public class Role {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;

    @Column(unique =true)
    private String roleCode;

    private String roleName;


}
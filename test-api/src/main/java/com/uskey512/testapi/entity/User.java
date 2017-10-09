package com.uskey512.testapi.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

/**
 * Created by uskey512 on 2017/09/30.
 */
@Entity
@Getter
@Setter
@Table(name = "testusers")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    @Column(insertable = false, updatable = false)
    private Date createdAt;
    @Column(insertable = false, updatable = false)
    private Date updatedAt;

}

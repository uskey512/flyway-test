package com.uskey512.testapi.dto.response;

import java.util.Date;

import javax.persistence.Column;

import org.hibernate.annotations.GeneratorType;

import lombok.Getter;
import lombok.Setter;

/**
 * Created by uskey512 on 2017/10/01.
 */
@Getter
@Setter
public class UserDto {
    private Integer id;
    private String name;
    private Date createdAt;
    private Date updatedAt;
}

package com.nevercome.icuriosity.domain;

import org.hibernate.validator.constraints.Length;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author: sun
 * @date: 2019/6/18
 */
@Table(name = "user")
public class UserPo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "select uuid()")
    private String id;

    @Length(min = 1, max = 16)
    private String name;

}

package com.nevercome.icuriosity.domain;

import com.nevercome.icuriosity.common.persistence.po.BasePO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * @author: sun
 * @date: 2019/6/18
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
@Table(name = "user")
public class User extends BasePO<String> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "select uuid()")
    private String id;

    @Length(min = 1, max = 16)
    private String name;

}

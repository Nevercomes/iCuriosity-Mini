package com.nevercome.icuriosity.aip;

import lombok.Data;

import java.util.List;

/**
 * @author: sun
 * @date: 2019/6/17
 */
@Data
public class AipResult {

    private String log_id;
    private int result_num;
    private List<AipBaiKeResult> result;

}





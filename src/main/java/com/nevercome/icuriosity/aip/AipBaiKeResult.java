package com.nevercome.icuriosity.aip;

import lombok.Data;

/**
 * @author: sun
 * @date: 2019/6/18
 */
@Data
public class AipBaiKeResult {
    private float score;
    private String root;
    private String keyword;
    private AipBaiKeInfo baike_info;
}

@Data
class AipBaiKeInfo {

    private String baike_url;
    private String image_url;
    private String description;

}


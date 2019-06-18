package com.nevercome.icuriosity.aip;

import com.baidu.aip.imageclassify.AipImageClassify;
import com.nevercome.icuriosity.utils.JsonMapper;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONObject;

import java.util.HashMap;

/**
 * @author: sun
 * @date: 2019/6/17
 */
@Slf4j
public class Aip {

    // 设置APP_ID/AK/SK
    private static final String APP_ID = "16546816";
    private static final String API_KEY = "R7Eg7nbj2mQm1FtWm33DuINF";
    private static final String SECRET_KEY = "Kr2wIU7rum6SivgcjDaUPqi9OjRPHTi3";

    public static AipResult infer(String path) {
        // 初始化一个AipImageClassifyClient
        AipImageClassify client = new AipImageClassify(APP_ID, API_KEY, SECRET_KEY);

        // 可选：设置网络连接参数
        client.setConnectionTimeoutInMillis(2000);
        client.setSocketTimeoutInMillis(60000);

        HashMap<String, String> options = new HashMap<>();
        options.put("baike_num", "5");

        JSONObject resBaiKee = client.advancedGeneral(path, options);
        AipResult aipResult = (AipResult) JsonMapper.fromJsonString(resBaiKee.toString(2), AipResult.class);
        log.info("AipImageClassify infer from path {}. Result log_id: {}, highest score keyword: {}",
                path, aipResult.getLog_id(), aipResult.getResult().get(0).getKeyword());
        return aipResult;
    }

    public static AipResult infer(byte[] image) {
        // 初始化一个AipImageClassifyClient
        AipImageClassify client = new AipImageClassify(APP_ID, API_KEY, SECRET_KEY);

        // 可选：设置网络连接参数
        client.setConnectionTimeoutInMillis(2000);
        client.setSocketTimeoutInMillis(60000);

        HashMap<String, String> options = new HashMap<>();
        options.put("baike_num", "5");

        JSONObject resBaiKee = client.advancedGeneral(image, options);
        AipResult aipResult = (AipResult) JsonMapper.fromJsonString(resBaiKee.toString(2), AipResult.class);
        log.info("AipImageClassify infer from image. Result log_id: {}, highest score keyword: {}",
                aipResult.getLog_id(), aipResult.getResult().get(0).getKeyword());
        return aipResult;
    }

    public static void main(String[] args) {

        // 初始化一个AipImageClassifyClient
        AipImageClassify client = new AipImageClassify(APP_ID, API_KEY, SECRET_KEY);


        // 可选：设置网络连接参数
        client.setConnectionTimeoutInMillis(2000);
        client.setSocketTimeoutInMillis(60000);

        String path = "D:/test.jpg";
        JSONObject res = client.objectDetect(path, new HashMap<String, String>());
        System.out.println(res.toString(2));

        HashMap<String, String> options = new HashMap<>();
        options.put("baike_num", "5");

        JSONObject resBaiKee = client.advancedGeneral(path, options);
        System.out.println(resBaiKee.toString(2));
        AipResult aipResult = (AipResult) JsonMapper.fromJsonString(resBaiKee.toString(2), AipResult.class);
        System.out.println(aipResult);

    }

}

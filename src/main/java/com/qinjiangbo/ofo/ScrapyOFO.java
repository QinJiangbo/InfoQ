package com.qinjiangbo.ofo;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import okhttp3.*;

import java.io.IOException;
import java.util.Map;
import java.util.Set;

/**
 * @date: 12/12/2016 6:33 PM
 * @author: qinjiangbo@github.io
 */
public class ScrapyOFO {

    public Map<Integer, Integer> request() throws IOException {
        // 特征点数据
        Map<Integer, Address> database = DataBase.getData();
        // 建立POST请求，循环获取各个点当前时间的小黄车数量
        OkHttpClient okHttpClient = new OkHttpClient();
        // 请求地址这里https转换为http
        String url = "http://san.ofo.so/ofo/Api/v2/nearby";

        // 循环遍历这个集合获取所有特征点的数据
        Set<Map.Entry<Integer, Address>> entrySet = database.entrySet();
        for (Map.Entry<Integer, Address> entry : entrySet) {
            RequestBody body = new FormBody.Builder()
                    .add("lat", entry.getValue().getLat().toString())
                    .add("lng", entry.getValue().getLng().toString())
                    .add("source", 1 + "")
                    .add("token", "de1b3120-8f92-11e6-9dca-2186d35c605d")
                    .build();
            // 获取Request对象
            Request request = buildRequest(url, body);
            Response response = okHttpClient.newCall(request).execute();
            if (response.isSuccessful()) {
                String result = response.body().string();
                JSONObject jsonObject = JSON.parseObject(result);
                String values = jsonObject.getString("values");
                String info = JSON.parseObject(values).getString("info");
                JSONObject infoObject = JSON.parseObject(info);
                System.out.println(entry.getValue().getName() + " : " + infoObject.getIntValue("num"));
            }
        }

        return null;
    }

    /**
     * 创建请求
     *
     * @param url  请求地址
     * @param body 请求数据
     * @return
     */
    private Request buildRequest(String url, RequestBody body) {
        Request request = new Request.Builder()
                .url(url)
                .post(body)
                .header("Cookie", "acw_tc=AQAAADCN2liWGQYAw7CsO3I9XzDFOljk")
                .header("Content-Type", "application/x-www-form-urlencoded")
                .header("User-Agent", "Bplan-ios/1.80.1 (iPhone; iOS 10.1.1; Scale/3.00)")
                .header("Accept-Language", "en-CN;q=1, zh-Hans-CN;q=0.9, fr-CN;q=0.8, ja-JP;q=0.7")
                .header("Host", "san.ofo.so")
                .header("If-None-Match", "W/\"4f-jt7Qv0kfclFqyjHYWXZLAA\"")
                .build();
        return request;
    }
}

package com.natsuki_kining.springboot.service;

import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class SmsClientImpl implements SmsClient {

    private static final String SEND_URL = "https://sms.yunpian.com/v2/sms/single_send.json";

    @Value("${yp.apiKey}")
    private String apiKey;

    @Override
    public String sendSms(String mobile) {
        Map<String, String> params = new HashMap<>();
        params.put("apiKey", apiKey);
        params.put("mobile", mobile);
        params.put("text", "短信信息");
        return post(SEND_URL, params);
    }

    private static String post(String url, Map<String, String> params) {
        CloseableHttpClient client = HttpClients.createDefault();
        String responseText = "";
        CloseableHttpResponse response = null;
        try {
            HttpPost method = new HttpPost(url);
            if (params != null) {
                List<NameValuePair> paramList = new ArrayList<>();
                params.forEach((key, value) -> {
                    NameValuePair nameValuePair = new BasicNameValuePair(key, value);
                    paramList.add(nameValuePair);
                });
                method.setEntity(new UrlEncodedFormEntity(paramList, "UTF-8"));
            }
            response = client.execute(method);
            HttpEntity entity = response.getEntity();
            if (entity != null) {
                responseText = EntityUtils.toString(entity, "UTF-8");
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (response != null) {
                try {
                    response.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return responseText;
    }
}

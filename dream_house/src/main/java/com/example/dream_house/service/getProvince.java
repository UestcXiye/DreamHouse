package com.example.dream_house.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestTemplate;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * @BelongsProject:dream_house
 * @BelongsPackage:com.example.dream_house.service
 * @Author:Uestc_Xiye
 * @CreateTime:2023-12-18 09:23:34
 */
public class getProvince {

    /**
     * @param ip
     * @return
     */
    public String get_Province(String ip) {
        try {
            URL realUrl = new URL("http://whois.pconline.com.cn/ipJson.jsp?ip=" + ip + "&json=true");
            HttpURLConnection conn = (HttpURLConnection) realUrl.openConnection();
            conn.setRequestMethod("GET");
            conn.setUseCaches(false);
            conn.setReadTimeout(6000);
            conn.setConnectTimeout(6000);
            conn.setInstanceFollowRedirects(false);
            int code = conn.getResponseCode();
            StringBuilder sb = new StringBuilder();
            String ipaddr = "";
            if (code == 200) {
                InputStream in = conn.getInputStream();
                BufferedReader reader = new BufferedReader(new InputStreamReader(in, "GBK"));//指定编码格式
                String line;
                while ((line = reader.readLine()) != null) {
                    sb.append(line);
                }
                System.out.println("==" + sb);
                String pro = sb.substring((sb.indexOf("pro") + 6), sb.indexOf("proCode") - 3);
                String city = sb.substring((sb.indexOf("city") + 7), sb.indexOf("cityCode") - 3);
                String addr = (sb.substring(sb.indexOf("addr") + 7, sb.indexOf("regionNames") - 3)).trim();
                if (pro.isEmpty()) {
                    ipaddr = addr;
                } else {
                    ipaddr = pro;
                }
            }
            return ipaddr;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}

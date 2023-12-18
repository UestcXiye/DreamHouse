package com.example.dream_house.service;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @BelongsProject:dream_house
 * @BelongsPackage:com.example.dream_house.service
 * @Author:Uestc_Xiye
 * @CreateTime:2023-12-18 09:29:50
 */
public class getTime {

    public String get_Time() {
        // 设置日期格式
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        // 获取当前系统时间并返回
        return simpleDateFormat.format(new Date());
    }
}

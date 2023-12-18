package com.example.dream_house.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

/**
 * @BelongsProject:dream_house
 * @BelongsPackage:com.example.dream_house.model
 * @Author:Uestc_Xiye
 * @CreateTime:2023-12-17 16:29:49
 */

@lombok.Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel("数据库字段")
public class Data {

    @ApiModelProperty(value = "信息所属ID", required = true, example = "1")
    private int id;

    @ApiModelProperty(value = "信息来源IP地址", required = true, example = "127.0.0.1")
    private String ip;

    @ApiModelProperty(value = "信息来源所属省份", required = true, example = "湖北")
    private String province;

    @ApiModelProperty(value = "内容发布时间", required = true, example = "2023-2-6 16:58:00")
    private String time;

    @ApiModelProperty(value = "梦想内容", required = true, example = "环游世界")
    private String str;

    @ApiModelProperty(value = "点赞数", required = true, example = "52")
    private int likes;

}

package com.example.dream_house.mapper;

import com.example.dream_house.model.Data;
import com.example.dream_house.model.getUser;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * @BelongsProject:dream_house
 * @BelongsPackage:com.example.dream_house.mapper
 * @Author:Uestc_Xiye
 * @CreateTime:2023-12-17 16:36:57
 */

@Mapper
public interface DataMapper {

    /**
     * 信息所属ID
     *
     * @param ip       信息来源省份
     * @param province 信息发出时间
     * @param time     信息内容
     * @param str      点赞数
     * @param likes
     */
    @Insert("INSERT INTO dream (ip, province, time, str, likes) VALUES (#{ip}, #{province}, #{time}, #{str}, #{likes})")
    int insert(@Param("ip") String ip,
               @Param("province") String province,
               @Param("time") String time,
               @Param("str") String str,
               @Param("likes") int likes);

    /**
     * 信息id
     *
     * @param id property属性对应Data对象中的成员名，column对应select出的字段名。
     */
    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "ip", column = "ip"),
            @Result(property = "province", column = "province"),
            @Result(property = "time", column = "time"),
            @Result(property = "str", column = "str"),
            @Result(property = "likes", column = "likes")
    })
    @Select("SELECT * FROM dream WHERE id = #{id}")
    Data findById(@Param("id") int id);

    /**
     * 用Data对象来作为传参,这样语句中的#{id}、#{ip}等数据就分别对应Data对象中的id和ip等属性。
     */
    @Update("UPDATE dream SET ip = #{ip}, province = #{province}, time = #{time}, str = #{str}, likes = #{likes} WHERE id = #{id}")
    void update(Data data);

    /**
     * 删除该id对应的信息
     *
     * @param id
     */
    @Delete("DELETE FROM dream WHERE id = #{id}")
    void delete(int id);

    /**
     * 查询点赞数前50名的信息
     */
    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "province", column = "province"),
            @Result(property = "str", column = "str"),
            @Result(property = "likes", column = "likes")
    })
    @Select("SELECT * FROM dream ORDER BY likes DESC LIMIT 50")
    List<getUser> findByLikes();

    /**
     * 查询最新的50条信息
     */
    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "province", column = "province"),
            @Result(property = "str", column = "str"),
            @Result(property = "likes", column = "likes")
    })
    @Select("SELECT * FROM dream ORDER BY time DESC LIMIT 50")
    List<getUser> findByTime();

    /**
     * 查询随机的50条信息
     */
    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "province", column = "province"),
            @Result(property = "str", column = "str"),
            @Result(property = "likes", column = "likes")
    })
    @Select("SELECT * FROM dream ORDER BY rand() DESC LIMIT 50")
    List<getUser> findByRand();

    /**
     * 更新指定id的点赞数+1
     */
    @Update("UPDATE dream SET likes = likes + 1 WHERE id = #{id}")
    void increaseLikesById(int id);

    /**
     * 更新指定id的点赞数-1
     */
    @Update("UPDATE dream SET likes = likes - 1 WHERE id = #{id}")
    void decreaseLikesById(int id);
}


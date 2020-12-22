package com.tensquare.article.pojo;

/**
 * 2 * @Author: Cai
 * 3 * @Date: 2020/7/21 13:33
 * 4
 */

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import java.io.Serializable;
import java.util.Date;


@TableName("tb_article")
@Data
public class Article implements Serializable {
    @TableId(type = IdType.INPUT)
    private String id;//ID
    private String columnid;    //专栏ID
    private String userid;      //用户ID
    private String title;       //标题
    private String content;     //文章正文
    private String image;       //文章封面
    private Date createtime;    //发表日期
    private Date updatetime;    //修改日期
    private String ispublic;    //是否公开
    private String istop;       //是否置顶
    private Integer visits = 0;     //浏览量
    private Integer thumbup = 0;    //点赞数
    private Integer comment = 0;    //评论数
    private String state;       //审核状态
    private String channelid;   //所属频道
    private String url;         //URL
    private String type;        //类型​
}
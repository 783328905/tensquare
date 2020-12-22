package com.tensquare.notice.pojo;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * 2 * @Author: Cai
 * 3 * @Date: 2020/7/24 11:32
 * 4
 */
@TableName("tb_notice_fresh")
@Data
public class NoticeFresh {
    private String userId;
    private String noticeId;
    //set get...
}
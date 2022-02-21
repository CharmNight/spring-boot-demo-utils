package com.night.bean.dao;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * @author night
 */
@Data
@TableName("t_user")
public class UserBean implements Serializable {
    private static final long serialVersionUID = 8018312153820119913L;

    @TableId
    private Long id;

    private String name;

    private Integer age;
}

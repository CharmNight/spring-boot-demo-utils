package com.night.bean.dao;

import com.baomidou.mybatisplus.annotation.*;
import com.night.bean.enums.DeleteEnum;
import com.night.handler.DeleteHandler;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author night
 */
@Data
@TableName("t_user")
public class UserBean implements Serializable {
    private static final long serialVersionUID = 8018312153820119913L;

    @TableId
    private Long id;

    /**
     * 名称
     */
    private String name;

    /**
     * 年龄
     */
    private Integer age;

    /**
     * 是否删除
     * default 0 delete 1
     */
    @TableLogic
    @TableField(value = "is_delete", typeHandler = DeleteHandler.class)
    private DeleteEnum isDelete;

    /**
     * 版本号 乐观锁
     */
    @Version
    private Integer version;

    /**
     * 创建时间
     */
    @TableField(fill = FieldFill.INSERT)
    private Date createTime;

    @TableField(fill = FieldFill.UPDATE)
    private Date updateTime;

    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Long operationBy;
}

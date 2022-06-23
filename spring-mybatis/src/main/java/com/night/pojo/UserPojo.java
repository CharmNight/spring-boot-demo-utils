package com.night.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserPojo implements Serializable {
    private static final long serialVersionUID = 8018312153820119913L;

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
    private Integer isDelete;

    /**
     * 版本号 乐观锁
     */
    private Integer version;

    /**
     * 创建时间
     */
    private Date createTime;

    private Date updateTime;

    private Long operationBy;
}

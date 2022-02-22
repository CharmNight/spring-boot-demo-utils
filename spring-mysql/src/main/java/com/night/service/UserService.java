package com.night.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.night.bean.dao.UserBean;

import java.util.List;

/**
 * @author night
 */
public interface UserService extends IService<UserBean> {
    /**
     * 保存
     * @return
     * @param name
     * @param age
     */
    public boolean saveBean(String name, Integer age);

    /**
     * 获取列表
     * @return
     */
    List<UserBean> doList();

    /**
     * 删除
     * @param id
     */
    void deleteBean(Long id);

    /**
     * 更新
     * @param id
     * @param name
     * @param age
     */
    void updateBean(Long id, String name, Integer age);
}

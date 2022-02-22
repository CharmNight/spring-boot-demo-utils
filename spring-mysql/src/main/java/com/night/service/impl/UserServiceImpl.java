package com.night.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.night.bean.dao.UserBean;
import com.night.mapper.UserMapper;
import com.night.service.UserService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author night
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, UserBean> implements UserService {
    @Override
    public boolean saveBean(String name, Integer age) {
        UserBean userBean = new UserBean();
        userBean.setName(name);
        userBean.setAge(age);
        return save(userBean);
    }

    @Override
    public List<UserBean> doList() {
        return list();
    }

    @Override
    public void deleteBean(Long id) {
        removeById(id);
    }

    @Override
    public void updateBean(Long id, String name, Integer age) {
        UserBean userBean = getById(id);
        userBean.setName(name);
        userBean.setAge(age);
        updateById(userBean);
    }
}

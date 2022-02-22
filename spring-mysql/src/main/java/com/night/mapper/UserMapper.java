package com.night.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.night.bean.dao.UserBean;
import org.apache.ibatis.annotations.CacheNamespace;

@CacheNamespace
public interface UserMapper extends BaseMapper<UserBean> {
}

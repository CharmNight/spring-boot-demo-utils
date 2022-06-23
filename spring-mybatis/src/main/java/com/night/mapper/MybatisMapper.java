package com.night.mapper;

import com.night.pojo.UserPojo;

import java.util.List;

public interface MybatisMapper {
    List<UserPojo> findAll();
}

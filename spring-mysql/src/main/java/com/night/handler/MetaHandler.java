package com.night.handler;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import com.night.bean.dao.UserBean;
import com.night.service.UserService;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.Objects;

/**
 * 操作人
 * 实现公共字段自动填充功能
 *
 * @author night
 */
@Component
public class MetaHandler implements MetaObjectHandler {

    private static final String OPERATION_USER = "operationBy";
    private static final String CREATE_TIME_FIELD = "createTime";
    private static final String UPDATE_TIME_FIELD = "updateTime";

    @Autowired
    private UserService userService;

    @Override
    public void insertFill(MetaObject metaObject) {
        if (metaObject.getOriginalObject() instanceof UserBean) {
            modifyUserBeanInsertFill(metaObject);
        }
    }


    @Override
    public void updateFill(MetaObject metaObject) {
        if (metaObject.getOriginalObject() instanceof UserBean) {
           modifyUserBeanUpdateFill(metaObject);
        }
    }

    private void modifyUserBeanUpdateFill(MetaObject metaObject) {
        setFieldValByName(UPDATE_TIME_FIELD, new Date(), metaObject);
        setFieldValByName(OPERATION_USER, userService.getOperationId(), metaObject);
    }

    private void modifyUserBeanInsertFill(MetaObject metaObject) {
        Object createTime = getFieldValByName(CREATE_TIME_FIELD, metaObject);
        if (Objects.isNull(createTime)) {
            setFieldValByName(CREATE_TIME_FIELD, new Date(), metaObject);
        }
        Object updateTime = getFieldValByName(UPDATE_TIME_FIELD, metaObject);
        if (Objects.isNull(updateTime)) {
            setFieldValByName(UPDATE_TIME_FIELD, new Date(), metaObject);
        }

        Object operationBy = getFieldValByName(OPERATION_USER, metaObject);
        if (Objects.isNull(operationBy)) {
            setFieldValByName(OPERATION_USER, userService.getOperationId(), metaObject);
        }
    }

}

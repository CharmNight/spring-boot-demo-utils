package com.night.serialize;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.parser.Feature;
import com.alibaba.fastjson.parser.ParserConfig;
import com.alibaba.fastjson.serializer.SerializerFeature;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.SerializationException;

import java.nio.charset.Charset;

/**
 *
 * Redis 序列化
 *
 * @author night
 */
public class FastJsonSerialize<T> implements RedisSerializer<T> {

    public static final Charset DEFAULT_CHARSET = Charset.forName("UTF-8");

    static final byte[] EMPTY_ARRAY = new byte[0];

    private Class<T> clazz;

    public FastJsonSerialize(Class<T> clazz) {
        super();
        this.clazz = clazz;
        // 自适应
        ParserConfig.getGlobalInstance().setAutoTypeSupport(true);
    }


    @Override
    public byte[] serialize(T t) throws SerializationException {
        if (t == null) {
            return EMPTY_ARRAY;
        }
        return JSON.toJSONBytes(t, SerializerFeature.WriteClassName, SerializerFeature.WriteDateUseDateFormat);
    }

    @Override
    public T deserialize(byte[] bytes) throws SerializationException {
        if (isEmpty(bytes)) {
            return null;
        }
        String data = new String(bytes, DEFAULT_CHARSET);
        return JSON.parseObject(data, clazz, Feature.AllowUnQuotedFieldNames);
    }

    /**
     * 判断是否数据为空
     *
     * @param data
     * @return
     */
    private static Boolean isEmpty(byte[] data) {
        return (data == null || data.length == 0);
    }


}

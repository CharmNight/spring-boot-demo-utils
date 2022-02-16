//package com.night.serializer;
//
//import com.alibaba.fastjson.JSON;
//import com.alibaba.fastjson.parser.Feature;
//import com.alibaba.fastjson.parser.ParserConfig;
//import com.alibaba.fastjson.serializer.SerializerFeature;
//import org.springframework.data.redis.serializer.RedisSerializer;
//import org.springframework.data.redis.serializer.SerializationException;
//
//import java.nio.charset.Charset;
//import java.nio.charset.StandardCharsets;
//
///**
// *
// * redis存储时使用 fastjson做序列化和反序列化
// *
// * @author night
// */
//public class FastJsonSerialize<T> implements RedisSerializer<T> {
//
//    public static final Charset DEFAULT_CHARSET = StandardCharsets.UTF_8;
//
//    private static final byte[] EMPTY_ARRAY = new byte[0];
//
//    private Class<T> clazz;
//
//    public FastJsonSerialize(Class<T> clazz) {
//        super();
//        this.clazz = clazz;
//        ParserConfig.getGlobalInstance().setAutoTypeSupport(true);
//    }
//
//    static boolean isEmpty(byte[] data){
//        return (data == null || data.length == 0);
//    }
//
//    @Override
//    public byte[] serialize(T t) throws SerializationException {
//        if (t == null) {
//            return EMPTY_ARRAY;
//        }
//        return JSON.toJSONBytes(t, SerializerFeature.WriteClassName, SerializerFeature.WriteDateUseDateFormat);
//    }
//
//    @Override
//    public T deserialize(byte[] bytes) throws SerializationException {
//        if (isEmpty(bytes)) {
//            return null;
//        }
//        final String tmp = new String(bytes, DEFAULT_CHARSET);
//        return JSON.parseObject(tmp, clazz, Feature.AllowUnQuotedFieldNames);
//    }
//}

package netty.serializer.impl;

import com.alibaba.fastjson.JSONObject;
import netty.serializer.Serializer;
import netty.serializer.SerializerAlgorithm;

/**
 * @author Cap_Sub
 */
public class JSONSerializer implements Serializer {

    @Override
    public byte getSerializerAlgorithm() {
        return SerializerAlgorithm.JSON;
    }

    @Override
    public byte[] serialize(Object object) {

        return JSONObject.toJSONBytes(object);
    }

    @Override
    public <T> T deserialize(Class<T> clazz, byte[] bytes) {

        return JSONObject.parseObject(bytes, clazz);
    }
}

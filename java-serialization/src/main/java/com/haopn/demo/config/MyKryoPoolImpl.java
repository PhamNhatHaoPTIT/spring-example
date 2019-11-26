package com.haopn.demo.config;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.serializers.CompatibleFieldSerializer;
import org.redisson.codec.KryoCodec;

import java.util.List;

public class MyKryoPoolImpl extends KryoCodec.KryoPoolImpl {

    List<Class<?>> classes;

    public MyKryoPoolImpl(List<Class<?>> classes, ClassLoader classLoader) {
        super(classes, classLoader);
        this.classes = classes;
    }

    @Override
    protected Kryo createInstance() {
        Kryo kryo = new Kryo();
        kryo.setReferences(false);
        for (Class<?> clazz : this.classes) {
            CompatibleFieldSerializer serializer = new CompatibleFieldSerializer(kryo, clazz);
            serializer.getCompatibleFieldSerializerConfig().setReadUnknownFieldData(true);
            kryo.register(clazz, serializer);
        }
        return kryo;
    }
}

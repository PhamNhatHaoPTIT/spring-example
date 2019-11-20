package com.haopn.demo.config;

import org.redisson.codec.KryoCodec;
import java.util.List;

public class ExtendKryoCodec extends KryoCodec {
    public ExtendKryoCodec(List<Class<?>> classes) {
        super(classes);
    }
}

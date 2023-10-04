package com.dengqn.mockjsongenerator.vo.gen.func;

import cn.hutool.core.lang.UUID;

public class Uuid implements GenFunc{
    @Override
    public Object genData() {
        return UUID.fastUUID().toString();
    }
}

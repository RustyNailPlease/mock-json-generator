package com.dengqn.mockjsongenerator.vo.gen.func;

import cn.hutool.core.util.RandomUtil;

public class Bool implements GenFunc {
    @Override
    public Object genData() {
        return RandomUtil.randomBoolean();
    }
}

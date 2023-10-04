package com.dengqn.mockjsongenerator.vo.gen.func;

import cn.hutool.core.util.RandomUtil;

public class Email implements GenFunc{
    @Override
    public Object genData() {
        return RandomUtil.randomString(10)
                + "@" + RandomUtil.randomString(8)
                + "." + RandomUtil.randomString(4);
    }
}

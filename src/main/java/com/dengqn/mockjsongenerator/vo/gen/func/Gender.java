package com.dengqn.mockjsongenerator.vo.gen.func;

import cn.hutool.core.util.RandomUtil;

public class Gender implements GenFunc{
    @Override
    public Object genData() {
            return RandomUtil.randomBoolean() ? "男" : "女";
    }
}

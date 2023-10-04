package com.dengqn.mockjsongenerator.vo.gen.func;

import cn.hutool.core.lang.Snowflake;
import cn.hutool.core.util.RandomUtil;

public class SnowId implements GenFunc {

    private Snowflake snowflake;

    public static SnowId getInstance() {
        return new SnowId(new Snowflake(RandomUtil.randomInt(2), RandomUtil.randomInt(2)));
    }

    public SnowId(Snowflake snowflake) {
        this.snowflake = snowflake;
    }
    @Override
    public Object genData() {
        return snowflake.nextId();
    }
}

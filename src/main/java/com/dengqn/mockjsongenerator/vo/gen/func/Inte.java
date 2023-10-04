package com.dengqn.mockjsongenerator.vo.gen.func;

import cn.hutool.core.util.RandomUtil;

public class Inte implements GenFunc {

    private int min;
    private int max;


    public Inte(int min, int max) {
        this.min = min;
        this.max = max;
    }

    public static Inte fromPattern(String pattern) {
        String replace = pattern.replace("{{", "").replace("}}", "");
        String[] split = replace.split("\\|");
        if (split.length == 1) {
            return new Inte(1, 3);
        }
        String[] range = split[1].split(",");
        if (range.length == 1) {
            return new Inte(1, Integer.parseInt(range[0]));
        }
        return new Inte(Integer.parseInt(range[0]), Integer.parseInt(range[1]));
    }

    @Override
    public Object genData() {
        return RandomUtil.randomInt(min, max + 1);
    }
}

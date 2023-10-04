package com.dengqn.mockjsongenerator.vo.gen.func;

import cn.hutool.core.util.RandomUtil;

public class Doubler implements GenFunc {


    private double min;
    private double max;

    public Doubler(double min, double max) {
        this.min = min;
        this.max = max;
    }

    @Override
    public Object genData() {
        return RandomUtil.randomDouble(min, max);
    }

    public static Doubler fromPattern(String pattern) {
        String replace = pattern.replace("{{", "").replace("}}", "");
        String[] split = replace.split("\\|");
        if (split.length == 1) {
            return new Doubler(1, 3);
        }
        String[] range = split[1].split(",");
        if (range.length == 1) {
            return new Doubler(1, Double.parseDouble(range[0]));
        }
        return new Doubler(Double.parseDouble(range[0]), Double.parseDouble(range[1]));
    }
}

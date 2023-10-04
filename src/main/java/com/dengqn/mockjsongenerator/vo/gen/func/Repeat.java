package com.dengqn.mockjsongenerator.vo.gen.func;

import cn.hutool.core.util.RandomUtil;

import java.io.Serializable;

public class Repeat implements Serializable {
    private static final long serialVersionUID = 605718922651483517L;

    private Integer min;

    private Integer max;

    public static Repeat fromPattern(String pattern) {
        String replace = pattern.replace("{{", "").replace("}}", "");
        String[] split = replace.split("\\|");
        if (split.length == 1) {
            return new Repeat(1, 3);
        }
        String[] range = split[1].split(",");
        if (range.length == 1) {
            return new Repeat(1, Integer.parseInt(range[0]));
        }
        return new Repeat(Integer.parseInt(range[0]), Integer.parseInt(range[1]));
    }

    public Repeat(Integer min, Integer max) {
        this.min = min;
        this.max = max;
    }

    public int randomSize() {
        return RandomUtil.randomInt(this.min, this.max + 1);
    }

    public Integer getMin() {
        return min;
    }

    public void setMin(Integer min) {
        this.min = min;
    }

    public Integer getMax() {
        return max;
    }

    public void setMax(Integer max) {
        this.max = max;
    }
}

package com.dengqn.mockjsongenerator.vo.gen;

import cn.hutool.core.util.RandomUtil;
import com.dengqn.mockjsongenerator.vo.gen.func.*;

public class GenFuncUtil {
    public static GenFunc fromPattern(String pattern) {
        String funcName = pattern
                .replace("{{", "")
                .replace("}}", "").split("\\|")[0];

        return switch (funcName) {
            case "snowId" -> SnowId.getInstance();
            case "uuid" -> new Uuid();
            case "bool" -> new Bool();
            case "address" -> new Address();
            case "name" -> new Name();
            case "email" -> new Email();
            case "integer" -> Inte.fromPattern(pattern);
            case "double" -> Doubler.fromPattern(pattern);
            case "gender" -> new Gender();
            case "text" -> Text.fromPattern(pattern);
            case "date" -> DateGen.fromPattern(pattern);
            default -> new Empty();
        };
    }

}

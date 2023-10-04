package com.dengqn.mockjsongenerator.vo.gen.func;

import cn.hutool.core.util.RandomUtil;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateGen implements GenFunc{

    private Date startDate;
    private Date stopDate;

    private String datePattern = "yyyy-MM-dd HH:mm:SS";

    public DateGen(Date startDate, Date stopDate, String datePattern) {
        this.startDate = startDate;
        this.stopDate = stopDate;
        this.datePattern = datePattern;
    }

    // date|2022-01-01 22:20:22,2023-01-01 23:30:23,yyyy-MM-dd HH:mm:SS
    public static DateGen fromPattern(String pattern) {
        String replace = pattern.replace("{{", "").replace("}}", "");
        String[] split = replace.split("\\|");

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:SS");

        if (split.length == 1) {
            return new DateGen(new Date(1970, Calendar.JANUARY, 1), new Date(), "yyyy-MM-dd HH:mm:SS");
        }
        String[] range = split[1].split(",");
        if (range.length == 1) {
            try {
                return new DateGen(new Date(1970, Calendar.JANUARY, 1),
                        dateFormat.parse(range[0]), "yyyy-MM-dd HH:mm:SS");
            } catch (ParseException e) {
                throw new RuntimeException(e);
            }
        }

        if (range.length == 2) {
            try {
                return new DateGen(dateFormat.parse(range[0]),
                        dateFormat.parse(range[1]), "yyyy-MM-dd HH:mm:SS");
            } catch (ParseException e) {
                throw new RuntimeException(e);
            }
        }

        if (range.length == 3) {
            try {
                return new DateGen(dateFormat.parse(range[0]),
                        dateFormat.parse(range[1]), range[2]);
            } catch (ParseException e) {
                throw new RuntimeException(e);
            }
        }


        return new DateGen(new Date(), new Date(), "yyyy-MM-dd HH:mm:SS");
    }

    @Override
    public Object genData() {
        return new SimpleDateFormat(datePattern)
                .format(new Date(RandomUtil.randomLong(startDate.getTime(), stopDate.getTime())));
    }
}

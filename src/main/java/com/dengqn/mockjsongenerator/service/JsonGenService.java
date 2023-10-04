package com.dengqn.mockjsongenerator.service;

import cn.hutool.core.util.RandomUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSON;
import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.dengqn.mockjsongenerator.vo.gen.GenFuncUtil;
import com.dengqn.mockjsongenerator.vo.gen.func.GenFunc;
import com.dengqn.mockjsongenerator.vo.gen.func.Repeat;
import org.springframework.stereotype.Service;

@Service
public class JsonGenService {

    /**
     * @param json [
     *             '{{repeat|2,7}}',
     *             [
     *             '{{repeat|3,4}}',
     *             {
     *             "id": "{{snowId}}",
     *             "gid":"{{uuid}}",
     *             "name": "{{name}}",
     *             "age": "{{integer|10,75}}",
     *             "gender": "{{gender}}",
     *             "address": "{{address}}",
     *             "remark": "{{text|10,100}}",
     *             "loginTime": "{{date|2022-10-01 11:11:11,2023-10-07 11:11:11,yyyy-MM-dd HH:mm:SS}}",
     *             "money": "{{double|2222.22,231111.22}}",
     *             "tags": [
     *             '{{repeat|6,10}}',
     *             "{{text|2,10}}"
     *             ]
     *             }
     *             ]
     *             ]
     * @return
     */
    public JSON genJson(JSON json) {
        // is array
        if (json instanceof JSONArray) {
            return genArray((JSONArray) json);
        }
        return genObj((JSONObject) json);
    }

    private JSONObject genObj(JSONObject templateObject) {
        JSONObject obj = JSONUtil.createObj();

        // 1.for each field
        for (String field : templateObject.keySet()) {
            Object object = templateObject.get(field);
            if (object instanceof JSONObject) {
                obj.set(field, genObj((JSONObject) object));
            } else if (object instanceof JSONArray) {
                obj.set(field, genArray((JSONArray) object));
            } else {
                // 2.1 not check template
                if (!isTemplateCheck(object.toString())) {
                    obj.set(field, object);
                }
                // 2.2 get func name
                GenFunc genFunc = GenFuncUtil.fromPattern(object.toString());
                obj.set(field, genFunc.genData());
            }
        }
        return obj;
    }

    private JSONArray genArray(JSONArray array) {
        // 1.null
        if (array == null || array.isEmpty()) {
            return JSONUtil.createArray();
        }
        // 2. 1 element
        if (array.size() == 1) {
            // 2.1 constant data.
            String content = array.get(0).toString();
            if (!isTemplateCheck(content)) {
                if (array.get(0) instanceof JSONArray) {
                    return JSONUtil.createArray().put(genArray((JSONArray) array.get(0)));
                } else if (array.get(0) instanceof JSONObject) {
                    return JSONUtil.createArray().put(genObj((JSONObject) array.get(0)));
                } else {
                    // 2.1 not check template
                    if (!isTemplateCheck(array.get(0).toString())) {
                        return JSONUtil.createArray().put(array.get(0));
                    } else {
                        // 2.2 get func name
                        GenFunc genFunc = GenFuncUtil.fromPattern(array.get(0).toString());
                        return JSONUtil.createArray().put(genFunc.genData());
                    }
                }
            }
            // 2.2 template func but not template data
            return JSONUtil.createArray();
        } else {
            // 2.3 template function
            // get function
            String content = array.get(0).toString();
            Repeat repeat = Repeat.fromPattern(content);
            // gen array
            JSONArray jsonArray = JSONUtil.createArray();
            // get 2nd json
            Object object = array.get(1);
            for (int i = 0; i < repeat.randomSize(); i++) {
                // recursive gen json data
                if (object instanceof JSONObject) {
                    jsonArray.put(genObj((JSONObject) object));
                } else if (object instanceof JSONArray) {
                    jsonArray.put(genArray((JSONArray) object));
                } else {
                    // 2.1 not check template
                    if (!isTemplateCheck(object.toString())) {
                        jsonArray.put(object);
                    } else {
                        // 2.2 get func name
                        GenFunc genFunc = GenFuncUtil.fromPattern(object.toString());
                        jsonArray.put(genFunc.genData());
                    }
                }
            }
            // if other data
            if (array.size() > 2) {
                for (int i = 2; i < array.size(); i++) {
                    jsonArray.put(array.get(i));
                }
            }

            return jsonArray;
        }
    }

    private boolean isTemplateCheck(String content) {
        return StrUtil.isNotBlank(content) && content.startsWith("{{") && content.endsWith("}}");
    }
}

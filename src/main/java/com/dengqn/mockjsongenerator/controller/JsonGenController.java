package com.dengqn.mockjsongenerator.controller;


import cn.hutool.json.JSON;
import cn.hutool.json.JSONUtil;
import com.dengqn.mockjsongenerator.service.JsonGenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/json")
public class JsonGenController {

    @Autowired
    private JsonGenService jsonGenService;


    /**
     * 生成
     *
     * @param jsonString
     * @return
     */
    @PostMapping("/gen")
    public ResponseEntity<Object> gen(@RequestBody String json) {
        return ResponseEntity.ok().body(jsonGenService.genJson(JSONUtil.parse(json)));
    }


}

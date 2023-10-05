package com.dengqn.mockjsongenerator.controller;


import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.RandomUtil;
import cn.hutool.json.JSONUtil;
import com.dengqn.mockjsongenerator.service.JsonGenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.nio.charset.StandardCharsets;
import java.util.function.Consumer;

@RestController
@RequestMapping("/api/json")
public class JsonGenController {

	@Value("${template.dir:./template}")
	private String templateDir;


	@Autowired
	private JsonGenService jsonGenService;


	/**
	 * 生成
	 *
	 * @param id
	 * @return
	 */
	@GetMapping("/gen/{id}")
	public ResponseEntity<Object> gen(@PathVariable("id") String id) {
		return ResponseEntity.ok().body(jsonGenService
				.genJson(JSONUtil.parse(FileUtil.readString(templateDir + File.separator + id + ".template", StandardCharsets.UTF_8))));
	}

	/**
	 * 添加模板
	 *
	 * @param template
	 * @return
	 */
	@PostMapping("/template")
	public ResponseEntity<String> addTemplate(@RequestBody String template) {
		String string = RandomUtil.randomString(20);

		try {
			gen(template);
		} catch (Exception e) {
			return ResponseEntity.internalServerError().body(e.getMessage());
		}


		FileUtil.writeBytes(template.getBytes(), templateDir + File.separator + string + ".template");

		return ResponseEntity.ok().body(string);


	}

}

package com.company.db.sys.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("/")
@Slf4j
public class PageController {

	@RequestMapping("doIndexUI")
	public String doIndexUI() {
		log.info("doIndexUI");
		return "starter";
	}

	@RequestMapping("doLoginUI")
	public String doLoginUI() {
		return "login";
	}

	@RequestMapping("doPageUI")
	public String doPageUI() {
		return "common/page";
	}
}

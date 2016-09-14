/**
 * Copyright (C) 2016 X-Forever.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.macrossx.server.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.macrossx.springframework.common.MapResponse;
import com.macrossx.springframework.validation.PhoneValidation;

import net.sf.oval.ConstraintViolation;
import net.sf.oval.Validator;

@Controller
@RequestMapping(value = "/client")
@SpringBootApplication
@ImportResource({"applicationContext.xml","/WEB-INF/dispatch-servlet.xml"})
public class ExampleConttoller {
	@Autowired
	private Validator validator;

	@RequestMapping(value = "/test", method = RequestMethod.GET, produces = { "application/json" })
	@ResponseBody
	public MapResponse login(@RequestParam(value = "appName", required = true) String appName) {
		List<ConstraintViolation> list =	validator.validate(new PhoneValidation(appName));
		return new MapResponse(null);
	}
	
	@RequestMapping(value = "/test2", method = RequestMethod.GET, produces = { "application/json" })
	public String login2(@RequestParam(value = "appName", required = true) String appName,
			@RequestParam(value = "appType", required = true) String appType) {
		return "/aaa/index";
	}
	

	public static void main(String...s){
		 SpringApplication.run(ExampleConttoller.class, s);
	}
}

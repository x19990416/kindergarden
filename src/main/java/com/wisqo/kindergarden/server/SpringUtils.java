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
package com.wisqo.kindergarden.server;

import org.apache.commons.lang3.StringUtils;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SpringUtils {
	private static ApplicationContext applicationContext;
	public static void init(String config){
		if (StringUtils.isBlank(config)) {
			config="applicationContext.xml";
		}
		applicationContext = new ClassPathXmlApplicationContext(config);
	}
	
	public static <T> T getBean(Class<T> bean) {
		return applicationContext.getBean(bean);
	}
	public static Object getBean(String beanName) {
		return applicationContext.getBean(beanName);
	}
}
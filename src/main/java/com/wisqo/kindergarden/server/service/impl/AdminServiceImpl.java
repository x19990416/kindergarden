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
package com.wisqo.kindergarden.server.service.impl;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.common.collect.Maps;
import com.wisqo.kindergarden.server.dao.AccountDao;
import com.wisqo.kindergarden.server.dao.bean.AccountView;
import com.wisqo.kindergarden.server.service.AdminService;

@Service
public class AdminServiceImpl implements AdminService {

	@Autowired
	private AccountDao accountDao;

	public Optional<AccountView> addKindergardenMaster(String realname, String username, String password, int postId) {
		if(accountDao.checkUsername(username)){
			return Optional.empty();
		}
		if (accountDao.saveAccount(realname, username, password, postId, 2) == 1) {
			Map<String, Object> params = Maps.newHashMap();
			params.put("realname", username);
			List<AccountView> list = accountDao.getAccountViewsByObject(params);
			if (!list.isEmpty()) {
				return Optional.of(list.get(0));
			}

		}
		return Optional.empty();

	}

}

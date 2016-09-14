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
package com.wisqo.kindergarden.server.dao;

import java.util.List;
import java.util.Map;

import com.wisqo.kindergarden.server.dao.bean.Account;
import com.wisqo.kindergarden.server.dao.bean.AccountPost;
import com.wisqo.kindergarden.server.dao.bean.AccountRole;
import com.wisqo.kindergarden.server.dao.bean.AccountView;

public interface AccountDao {
	public Account get(int id);
	public List<AccountView> getAccountViewsByObject(Map<String, Object> params);
	public int saveAccount(String username, String password, int postId, int roleId);
	public int updateAccount(Account accountBean);
	public List<AccountPost> getAccountPosts(Map<String, String> params);
	public List<AccountRole> getAccountRole(Map<String, String> params);
	public int saveAccountPost(String text);
	public int saveAccountRole(String text);
	public int updateAccountPost(AccountPost accountBean);
	public int updateAccountRole(AccountRole accountBean);
	
	
}

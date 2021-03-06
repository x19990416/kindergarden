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
package com.wisqo.kindergarden.server.dao.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.google.common.collect.Maps;
import com.wisqo.kindergarden.server.Constant;
import com.wisqo.kindergarden.server.SpringUtils;
import com.wisqo.kindergarden.server.dao.AccountDao;
import com.wisqo.kindergarden.server.dao.bean.Account;
import com.wisqo.kindergarden.server.dao.bean.AccountPost;
import com.wisqo.kindergarden.server.dao.bean.AccountRole;
import com.wisqo.kindergarden.server.dao.bean.AccountView;

@Repository
public class AccountDaoImpl extends BaseDaoImpl implements AccountDao {
	public Account get(int id) {
		List<Account> list = this.listObj("select * from account_view where id=?", Account.class, id);
		if (list.size() == 1)
			return list.get(0);
		return null;

	}

	public List<AccountView> getAccountViewsByObject(Map<String, Object> params) {
		return this.listObj("select * from account_view", AccountView.class, params);
	}

	public List<AccountPost> getAccountPosts(Map<String, String> params) {
		return this.listObj("select * from account_post", AccountPost.class, params);
	}

	public List<AccountRole> getAccountRole(Map<String, String> params) {
		return this.listObj("select * from account_role", AccountRole.class, params);
	}

	public int saveAccount(String realname,String username, String password, int postId, int roleId) {
		String sql = "insert into account(realname,username,password,post_id,role_id,status,insert_time)values(?,?,?,?,?,?,SYSDATE())";
		return update(sql, new Object[] { realname,username, password, postId, roleId, Constant.USER_STATUS.ON.id() });
	}

	public int saveAccountPost(String text) {
		String sql = "insert into account_post(text)values(?)";
		return update(sql, new Object[] { text });
	}

	public int saveAccountRole(String text) {
		String sql = "insert into account_role(text)values(?)";
		return update(sql, new Object[] { text });
	}

	public int updateAccountPost(AccountPost accountBean) {
		String sql = "update account_post set text=? where id = ?";
		return update(sql, new Object[] { accountBean.getText(), accountBean.getId() });
	}

	public int updateAccountRole(AccountRole accountBean) {
		String sql = "update account_role set text=?,authority=? where id = ?";
		return update(sql, new Object[] { accountBean.getText(), accountBean.getAuthority(), accountBean.getId() });
	}

	public int updateAccount(Account accountBean) {
		String sql = "update account set username=?,password=?,realname=?,phone_no=?,role_id=?,post_id=?,device_token=?,status=?,update_time=SYSDATE() where id = ?";
		return update(sql,
				new Object[] { accountBean.getUsername(), accountBean.getPassword(), accountBean.getRealname(),
						accountBean.getPhoneNo(), accountBean.getRoleId(), accountBean.getPostId(),
						accountBean.getDeviceToken(), accountBean.getStatus(), accountBean.getId() });
	}

	public static void main(String[] args) {
		SpringUtils.init("applicationContext.xml");
		AccountDao accountDao = SpringUtils.getBean(AccountDao.class);
		Map<String, Object> map = Maps.newHashMap();
		map.put("id", "1");

		AccountView account = accountDao.getAccountViewsByObject(map).get(0);
		System.err.println(account);
		// account.setDeviceToken("test");
		// accountDao.updateAccount(account);
		// System.err.println(new AccountDaoImpl().getAll(new Account()));
		// System.err.println(accountDao.saveAccount("a","1",3,"4"));

	}

	@Override
	public boolean checkUsername(String userame) {
		Map<String,Object> params=Maps.newHashMap();
		params.put("username", userame);
		return !this.listObj("select username from account", Account.class, params).isEmpty();
	}

}

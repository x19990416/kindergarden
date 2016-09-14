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

import java.lang.reflect.Field;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.stereotype.Repository;

import com.google.common.collect.Maps;
import com.wisqo.kindergarden.server.Constant;
import com.wisqo.kindergarden.server.SpringUtils;
import com.wisqo.kindergarden.server.dao.AccountDao;
import com.wisqo.kindergarden.server.dao.bean.Account;

@Repository
public class AccountDaoImpl extends BaseDaoImpl implements AccountDao {
	public Account get(int id) {
		List<Account> list = this.listObj("select * from AccountView where id=?", Account.class, id);
		if (list.size() == 1)
			return list.get(0);
		return null;

	}

	public List<Account> getAccountsByObject(Map<String, String> params) {
		StringBuilder sql = new StringBuilder("select * from AccountView ");
		if (params.isEmpty()) {
			return this.listObj(sql.toString(), Account.class, new Object[] {});
		}
		String key = "";
		sql.append("where ");
		Set<String> set = params.keySet();
		Iterator<String> it = set.iterator();
		while (it.hasNext()) {
			key = it.next();
			sql.append(key).append("=?");
			break;
		}

		return this.listObj(sql.toString(), Account.class, new Object[] { params.get(key) });
	}

	public int saveAccount(String username, String password, int postId, int roleId) {
		String sql = "insert into account(username,password,post_id,role_id,status,insert_time)values(?,?,?,?,?,SYSDATE())";
		return update(sql, new Object[] { username, password, postId, roleId, Constant.USER_STATUS.ON.id() });
	}
	
	public int updateAccount(Account accountBean) {
		String sql = "update account set username=?,password=?,realname=?,phone_no=?,role_id=?,post_id=?,device_token=?,status=?,update_time=SYSDATE() where id = ?";
		return update(sql, new Object[]{accountBean.getUsername(),accountBean.getPassword(),accountBean.getRealname(),accountBean.getPhoneNo(),accountBean.getRoleId(),accountBean.getPostId(),accountBean.getDeviceToken(),accountBean.getStatus(),accountBean.getId()});
	}
	
	public static void main(String[] args) {
		SpringUtils.init("applicationContext.xml");
		AccountDao accountDao = SpringUtils.getBean(AccountDao.class);
		Map<String, String> map = Maps.newHashMap();
		map.put("id", "1");
		
		Account account = accountDao.getAccountsByObject(map).get(0);
		System.err.println(account);
		account.setDeviceToken("test");
		accountDao.updateAccount(account);
//		System.err.println(new AccountDaoImpl().getAll(new Account()));
//		System.err.println(accountDao.saveAccount("a","1",3,"4"));

	}

}

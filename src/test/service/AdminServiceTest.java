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
package service;

import java.util.Optional;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.wisqo.kindergarden.server.dao.bean.AccountView;
import com.wisqo.kindergarden.server.service.AdminService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext.xml")
public class AdminServiceTest {
	@Autowired
	private AdminService adminService;

	@Test
	public void saveTest() {
		String username="aaaa";
		Optional<AccountView> op = adminService.addKindergardenMaster("测试1", username, "bbbb", 1);;
		if(op.isPresent()){

			System.err.println(op.get());
			Assert.assertSame(op.get().getUsername(), username);
			
		}
		else {
			System.err.println("empty");
			Assert.assertSame(true,op.equals(Optional.empty()));
		}
	}

}

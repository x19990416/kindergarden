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

import com.wisqo.kindergarden.server.dao.bean.School;
import com.wisqo.kindergarden.server.dao.bean.SchoolClass;
import com.wisqo.kindergarden.server.dao.bean.SchoolGroup;
import com.wisqo.kindergarden.server.dao.bean.SchoolStudent;

public interface SchoolDao {
	public School getSchool();
	public int saveSchool();
	public int updateSchool();

	public SchoolClass getSchoolClass();
	public int saveSchoolClass();
	public int updateSchoolClass();
	
	
	public SchoolStudent getSchoolStudent();
	public int saveShcoolStudent();
	public int updateSchoolStudent();
	
	
	public SchoolGroup getSchoolGroup();
	public int saveSchoolGroup();
	public int updateSchoolGroup();
	
	
	
	
	
	
	
}

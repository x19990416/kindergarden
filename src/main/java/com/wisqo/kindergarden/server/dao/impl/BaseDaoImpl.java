package com.wisqo.kindergarden.server.dao.impl;


import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Repository;

import com.wisqo.kindergarden.server.dao.BaseDao;

@Repository
public class BaseDaoImpl implements BaseDao {
	protected Logger logger = Logger.getLogger(getClass());
	private static final String lastId_mysql = "select LAST_INSERT_ID()";
	@Resource
	protected JdbcTemplate jdbcTemplate;

	@Override
	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}
	
	@Override
	public <T> T get(String sql, Class<T> requiredType, Object... args) {
		RowMapper<T> rm = BeanPropertyRowMapper.newInstance(requiredType);
		if (null == args || args.length == 0) {
			return jdbcTemplate.queryForObject(sql, rm);
		}
		return jdbcTemplate.queryForObject(sql, rm, args);
		
	}

	@Override
	public int update(String sql, Object... args) {
		if (null == args || args.length == 0) {
			return jdbcTemplate.update(sql);
		}
		return jdbcTemplate.update(sql, args);
	}

	@Override
	public <T> List<T> listObj(String sql, Class<T> elementType, Object... args) {
		if (null == args || args.length == 0) {
			return jdbcTemplate.query(sql, new BeanPropertyRowMapper(elementType));
		}
		return jdbcTemplate.query(sql, new BeanPropertyRowMapper(elementType),args);
	}

	@Override
	public Map<String, Object> listMap(String sql, Object... args) {
		if (null == args || args.length == 0) {
			return jdbcTemplate.queryForMap(sql);
		}
		return jdbcTemplate.queryForMap(sql, args);
	}

	@Override
	public <T> List<T> listObj(String sql, Class<T> elementType, Map<String, Object> pMap) {
		Object[] args=null;
		if (pMap!=null && pMap.size()>0) {
			args=new Object[pMap.size()];
			sql+=" where ";
			int index=0;
			for (Map.Entry<String , Object> e: pMap.entrySet()) {
				if (index+1==pMap.size()) {
					sql+=e.getKey()+"=?";
				}else {
					sql+=e.getKey()+"=? and ";
				}
				args[index]=e.getValue();
				index++;
			}
		}
		return listObj(sql, elementType, args);
	}

	@Override
	public long getLastInsertId() {
		return jdbcTemplate.queryForObject(lastId_mysql, Long.class);
	}

	@Override
	public SqlRowSet queryForRowSet(String string, Object... args) {
		return jdbcTemplate.queryForRowSet(string,args);
	}
	
	@Override
	public <T> List<T> queryForList(String string, Class<T> type,Object... args) {
		return jdbcTemplate.queryForList(string, type, args);
	}
	@Override
	public long getCountBySql(String sql, String countName,Object... args) {
		 Map<String,Object> counts = jdbcTemplate.queryForMap(sql, args);
		return (long) counts.get(countName);
	}	
	


}

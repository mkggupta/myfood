package com.myfood.dao.modules.base;

import com.ibatis.sqlmap.client.SqlMapClient;

public class AbstractDBManager {

	protected SqlMapClient sqlMapClient_;
	
	public SqlMapClient getSqlMapClient_() {
		return sqlMapClient_;
	}

	public void setSqlMapClient_(SqlMapClient sqlMapClient_) {
		this.sqlMapClient_ = sqlMapClient_;
	}


}
package com.gracebrother.strategy;

import com.gracebrother.pojo.User;
import com.gracebrother.sharding.shard.Strategy;

public class UserStrategy implements Strategy {
	@Override
	public String getTargetSql(String oldSql, Object parm, String[] tables) {
		String targetSql = oldSql;
		User u = (User) parm;
		if (tables != null) {
			for (String table : tables) {
				if (u.getId() % 2 == 1) {
					targetSql = targetSql.replace("$[" + table + "]", table + "1");
				} else {
					targetSql = targetSql.replace("$[" + table + "]", table + "2");
				}
			}
		}
		return targetSql;
	}

}

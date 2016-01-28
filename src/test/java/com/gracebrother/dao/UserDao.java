package com.gracebrother.dao;

import com.gracebrother.pojo.User;
import com.gracebrother.sharding.annotation.Params;
import com.gracebrother.sharding.annotation.Shard;
import com.gracebrother.strategy.UserStrategy;
@Shard
public interface UserDao {
	@Params(tables = {"user"}, strategy =UserStrategy.class)
	public void insert(User u);
}

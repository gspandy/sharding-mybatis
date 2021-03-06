package com.gracebrother.sharding.builder;

import java.util.HashMap;
import java.util.Map;

public class ShardHolder {
	private static final ShardHolder instance = new ShardHolder();
	private Map<String, ShardObject> strategyRegister = new HashMap<String, ShardObject>();

	public static ShardHolder getInstance() {
		return instance;
	}

	public void register(String mapperId, String[] tables, Class<?> clazz) {
		ShardObject obj = new ShardObject();
		obj.setMapperId(mapperId);
		obj.setTables(tables);
		obj.setStrategy(clazz);
		this.strategyRegister.put(mapperId, obj);
	}

	public ShardObject getShardObject(String nameSpace) {
		return this.strategyRegister.get(nameSpace);
	}

	public boolean hasShard(String nameSpace) {
		if (getShardObject(nameSpace) != null) {
			return true;
		} else {
			return false;
		}
	}
}

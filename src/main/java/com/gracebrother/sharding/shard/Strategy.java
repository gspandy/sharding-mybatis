package com.gracebrother.sharding.shard;

/**
 * Every sharding pojo must implements Strategy 
 * @author bin
 *
 */
public interface Strategy {
	public abstract String getTargetSql(String oldSql,Object parm,String[] tables);
}	

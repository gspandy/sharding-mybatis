支持JTA分布式事务，支持多数据源切换，支持分库分表。

例如：

切换数据源使用：CustomerContextHolder.setContextType("目标数据源");

分表策略：
使用分表步骤如下:

1、使用自定义注解标记需要分表的Dao以及方法。
@Shard
public interface UserDao {
	@Params(tables = {"user"}, strategy =UserStrategy.class)
	public void insert(User u);
}

2、在SQL中使用上面定义的表名。
<insert id="insert" parameterType="com.gracebrother.pojo.User">
	insert into $[user] (id,
	name
	)
	values (#{id,jdbcType=INTEGER},
	#{name,jdbcType=VARCHAR}
	)
</insert>

3、实现分表接口
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


Support the JTA, Support multiple data source, support Sharding.

EG:

DataSource switch code :CustomerContextHolder.setContextType("targetDataSource");

Useing sharding you need:
1、Use annotation like this
@Shard
public interface UserDao {
	@Params(tables = {"user"}, strategy =UserStrategy.class)
	public void insert(User u);
}
2、Use Defined table in SQL
<insert id="insert" parameterType="com.gracebrother.pojo.User">
	insert into $[user] (id,
	name
	)
	values (#{id,jdbcType=INTEGER},
	#{name,jdbcType=VARCHAR}
	)
</insert>
3、Implements Strategy
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

支持JTA分布式事务，支持多数据源切换，支持分库分表。

例如：

切换数据源使用：CustomerContextHolder.setContextType("目标数据源");

分表策略：
使用分表步骤如下:

1、使用自定义注解标记需要分表的Dao以及方法。<br>
@Shard<br>
public interface UserDao {
    @Params(tables = {"user"}, strategy =UserStrategy.class)<br>
    public void insert(User u);<br>
}

2、在SQL中使用上面定义的表名。<br>
<insert id="insert" parameterType="com.gracebrother.pojo.User">
	insert into "$[user]" (id,
	name
	)
	values (#{id,jdbcType=INTEGER},
	#{name,jdbcType=VARCHAR}
	)
</insert>

3、实现分表接口<br>
public class UserStrategy implements Strategy {<br>
	@Override<br>
	public String getTargetSql(String oldSql, Object parm, String[] tables) {<br>
		String targetSql = oldSql;<br>
		User u = (User) parm;<br>
		if (tables != null) {<br>
			for (String table : tables) {<br>
				if (u.getId() % 2 == 1) {<br>
					targetSql = targetSql.replace("$[" + table + "]", table + "1");<br>
				} else {<br>
					targetSql = targetSql.replace("$[" + table + "]", table + "2");<br>
				}<br>
			}<br>
		}<br>
		return targetSql;<br>
	}<br>

}<br>


Support the JTA, Support multiple data source, support Sharding.<br>

EG:<br>

DataSource switch code :CustomerContextHolder.setContextType("targetDataSource");<br>

Useing sharding you need:<br>
1、Use annotation like this<br>
@Shard<br>
public interface UserDao {<br>
	@Params(tables = {"user"}, strategy =UserStrategy.class)<br>
	public void insert(User u);<br>
}<br>
2、Use Defined table in SQL<br>
<insert id="insert" parameterType="com.gracebrother.pojo.User"><br>
	insert into "$[user]" (id,<br>
	name<br>
	)<br>
	values (#{id,jdbcType=INTEGER},<br>
	#{name,jdbcType=VARCHAR}<br>
	)<br>
</insert><br>
3、Implements Strategy<br>
public class UserStrategy implements Strategy {<br>
	@Override<br>
	public String getTargetSql(String oldSql, Object parm, String[] tables) {<br>
		String targetSql = oldSql;<br>
		User u = (User) parm;<br>
		if (tables != null) {<br>
			for (String table : tables) {<br>
				if (u.getId() % 2 == 1) {<br>
					targetSql = targetSql.replace("$[" + table + "]", table + "1");<br>
				} else {<br>
					targetSql = targetSql.replace("$[" + table + "]", table + "2");<br>
				}<br>
			}<br>
		}<br>
		return targetSql;<br>
	}<br>

}<br>

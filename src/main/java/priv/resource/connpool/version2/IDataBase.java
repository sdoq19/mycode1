package priv.resource.connpool.version2;

/**
 * 数据库连接信息接口 说明：将驱动、连接、数据库名、数据库密码等数据连接基础信息做成接口
 * */
public interface IDataBase {
	/**
	 * @return 数据库驱动名 注意必须加入数据库驱动包
	 * */
	public String getDirver();

	/**
	 * @return 数据库连接
	 * */
	public String getConnUrl();

	/**
	 * @return 数据库用户名
	 * */
	public String getUserName();

	/**
	 * @return 数据库密码
	 * */
	public String getPassword();
}
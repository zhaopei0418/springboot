package online.zhaopei.myproject.sqlprovide;

import org.apache.ibatis.jdbc.SQL;

public class UserTestProvide {

	public String getAllUsers() {
		return new SQL() {{
			this.SELECT("*").FROM("user_test");
		}}.toString();
	}
}

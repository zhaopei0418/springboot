package online.zhaopei.myproject.resource;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * The domain model class
 * 
 * @author bright.zheng
 *
 */
@ApiModel(description = "Hello 类")
public class HelloResource {

	@ApiModelProperty(value = "消息", required = true)
	private String msg;

	public String getMsg() {
		return this.msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}
}
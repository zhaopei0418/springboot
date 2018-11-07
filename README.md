用java 语言 + springboot + springmvc + mybatis + (maven/gradle) 搭成的框架

增加redis的发布订阅，及消息队列例子

1.用maven启动命令是
	mvn spring-boot:run

添加restful风格API
```
文档地址：http://localhost:8081/apidocs/index.html
```

启动之后输入localhost:8081  返回home 就成功了

测试redis订阅发布：发出如下命令
```
curl -v http://localhost:8081/redis/publish\?message\=zhaopei-123
```

或者在浏览器中打开：
会显示日志如下：
o.z.m.config.IntegrationConfiguration    : subscribeMessage = zhaopei-123
发布订阅已成功

## 注意如出现以下错误:
```
org.springframework.amqp.AmqpAuthenticationException: com.rabbitmq.client.AuthenticationFailureException: ACCESS_REFUSED - Login was refused using authentication mechanism PLAIN. For details see the broker logfile.
```
请修改src/main/resources/application.yml中的
```
rabbitmq:
  username: 自己队列的用户名
  password: 自己队列的密码
```

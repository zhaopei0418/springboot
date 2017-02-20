用java 语言 + springboot + springmvc + mybatis + (maven/gradle) 搭成的框架

1.用maven启动命令是
	mvn spring-boot:run

2.用gradle启动的命令是
	gradle bootRun

添加restful风格API
```
文档地址：http://localhost:8081/apidocs/index.html
```

启动之后输入localhost:8081  返回home 就成功了

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

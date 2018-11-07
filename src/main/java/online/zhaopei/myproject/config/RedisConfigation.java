package online.zhaopei.myproject.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

@Configuration
@ImportResource(locations = {"classpath:application-*.xml"})
public class RedisConfigation {

}

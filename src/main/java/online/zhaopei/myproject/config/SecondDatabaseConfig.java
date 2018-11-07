package online.zhaopei.myproject.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.github.pagehelper.PageHelper;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;
import java.util.Properties;

@Configuration
@MapperScan(basePackages = "online.zhaopei.myproject.mapper.second", sqlSessionFactoryRef = "secondSqlSessionFactory")
public class SecondDatabaseConfig {

	private static Logger logger = LoggerFactory.getLogger(SecondDatabaseConfig.class);

	@Bean(name = "secondDataSource")
	@ConfigurationProperties(prefix = "spring.datasource.second")
	public DataSource secondDataSource() {
		return new DruidDataSource();
	}
	
	@Bean(name = "secondSqlSessionFactory")
	public SqlSessionFactory secondSqlSessionFactory() throws Exception {
		SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
		logger.info("second sqlsession--" + this.secondDataSource().hashCode());
		sqlSessionFactoryBean.setDataSource(this.secondDataSource());
		PageHelper pageHelper = new PageHelper();
		Properties properties = new Properties();
		properties.setProperty("dialect", "mysql");
		properties.setProperty("pageSizeZero", "true");
		properties.setProperty("reasonable", "false");
		properties.setProperty("params", "pageNum=pageHelperStart;pageSize=pageHelperRows;");
		properties.setProperty("supportMethodsArguments", "true");
		properties.setProperty("returnPageInfo", "none");
		pageHelper.setProperties(properties);
		Interceptor[] interceptors = new Interceptor[] { pageHelper };
		sqlSessionFactoryBean.setPlugins(interceptors);
		return sqlSessionFactoryBean.getObject();
	}
	

	@Bean(name = "secondTxMan")
	public PlatformTransactionManager secondTransactionManager() {
		logger.info("second dataSource--" + this.secondDataSource().hashCode());
		return new DataSourceTransactionManager(this.secondDataSource());
	}
}

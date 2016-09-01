package online.zhaopei.myproject.config;

import java.sql.SQLException;
import java.util.Properties;

import javax.annotation.Resource;
import javax.sql.DataSource;

import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;
import com.github.pagehelper.PageHelper;

@Configuration
public class DatabaseConfiguration {

	@Bean
	public ServletRegistrationBean druidServlet() {
		ServletRegistrationBean reg = new ServletRegistrationBean();
		reg.setServlet(new StatViewServlet());
		reg.addUrlMappings("/druid/*");
		// reg.addInitParameter("allow", "127.0.0.1");
		// reg.addInitParameter("deny","");
		reg.addInitParameter("loginUsername", "admin");
		reg.addInitParameter("loginPassword", "admin");
		return reg;
	}

	@Bean
	public DataSource druidDataSource(@Value("${spring.datasource.driver-class-name}") String driver,
			@Value("${spring.datasource.url}") String url, @Value("${spring.datasource.username}") String username,
			@Value("${spring.datasource.password}") String password) {
		DruidDataSource druidDataSource = new DruidDataSource();
		druidDataSource.setDriverClassName(driver);
		druidDataSource.setUrl(url);
		druidDataSource.setUsername(username);
		druidDataSource.setPassword(password);
		try {
			druidDataSource.setFilters("stat, wall, log4j");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return druidDataSource;
	}
	
	@Bean
	@Resource(name = "druidDataSource")
	public SqlSessionFactory sqlSessionFactory(DataSource dataSource) throws Exception {
		SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
		sqlSessionFactoryBean.setDataSource(dataSource);
		PageHelper pageHelper = new PageHelper();
		Properties properties = new Properties();
		properties.setProperty("dialect", "mysql");
		properties.setProperty("pageSizeZero", "true");
		properties.setProperty("reasonable", "true");
		properties.setProperty("params", "pageNum=pageHelperStart;pageSize=pageHelperRows;");
		properties.setProperty("supportMethodsArguments", "true");
		properties.setProperty("returnPageInfo", "none");
		pageHelper.setProperties(properties);
		Interceptor[] interceptors = new Interceptor[] { pageHelper };
		sqlSessionFactoryBean.setPlugins(interceptors);
		return sqlSessionFactoryBean.getObject();
	}

	@Bean
	@Resource(name = "druidDataSource")
	public PlatformTransactionManager transactionManager(DataSource dataSource) {
		return new DataSourceTransactionManager(dataSource);
	}
	
	@Bean
	public FilterRegistrationBean filterRegistrationBean() {
		FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
		filterRegistrationBean.setFilter(new WebStatFilter());
		filterRegistrationBean.addUrlPatterns("/*");
		filterRegistrationBean.addInitParameter("exclusions", "*.js,*.gif,*.jpg,*.png,*.css,*.ico,/druid/*");
		return filterRegistrationBean;
	}
}

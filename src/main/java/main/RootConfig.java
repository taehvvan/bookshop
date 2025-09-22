package main;

import javax.sql.DataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

@Configuration
// Service, Component 등 Controller를 제외한 모든 것을 스캔합니다.
@ComponentScan(basePackages = {"admin", "bag", "join", "login", "main", "pay", "view", "chart"})
// MyBatis Mapper 인터페이스를 스캔합니다.
@MapperScan(basePackages = {"admin", "bag", "join", "login", "main", "pay", "view", "chart"})
// db.properties 파일을 로드합니다.
@PropertySource("classpath:db.properties")
@EnableTransactionManagement
public class RootConfig {

    @Value("${jdbc.driver}") private String driverClassName;
    @Value("${jdbc.url}") private String jdbcUrl;
    @Value("${jdbc.username}") private String username;
    @Value("${jdbc.password}") private String password;

    @Bean
    public HikariConfig hikariConfig() {
        HikariConfig config = new HikariConfig();
        config.setDriverClassName(driverClassName);
        config.setJdbcUrl(jdbcUrl);
        config.setUsername(username);
        config.setPassword(password);
        return config;
    }

    @Bean(destroyMethod = "close")
    public DataSource dataSource() {
        return new HikariDataSource(hikariConfig());
    }

    @Bean
    public SqlSessionFactory sqlSessionFactory() throws Exception {
        SqlSessionFactoryBean sqlSessionFactory = new SqlSessionFactoryBean();
        sqlSessionFactory.setDataSource(dataSource());
        // XML Mapper 파일들의 위치를 지정합니다.
        sqlSessionFactory.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath*:**/resources/**/*Mapper.xml"));
        return sqlSessionFactory.getObject();
    }
    
    @Bean
    public DataSourceTransactionManager transactionManager() {
        return new DataSourceTransactionManager(dataSource());
    }
}
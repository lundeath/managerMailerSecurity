package app.manage.config;

import app.manage.filter.RoutingFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.view.AbstractTemplateViewResolver;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;
import org.springframework.web.servlet.view.UrlBasedViewResolver;

import java.util.Properties;

@EnableWebMvc
@EnableJpaRepositories(basePackages = "app.manage.repositories")
@Configuration
@ComponentScan({ "app.manage" })
@Import({ WebSecurityConfig.class })
public class AppConfig {

    @Autowired
    private Environment env;

    @Bean(name = "dataSource")
    public DriverManagerDataSource dataSource() {
        DriverManagerDataSource driverManagerDataSource = new DriverManagerDataSource();
        driverManagerDataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
        driverManagerDataSource.setUrl("jdbc:mysql://localhost:3306/manager");
        driverManagerDataSource.setUsername("root");
        driverManagerDataSource.setPassword("root");
        return driverManagerDataSource;
    }


    @Bean
    public InternalResourceViewResolver templateResolver(){
        InternalResourceViewResolver resolver = new InternalResourceViewResolver();
        resolver.setViewClass(JstlView.class);
        resolver.setPrefix("/WEB-INF/static/");
        resolver.setSuffix(".jsp");
        return resolver;
    }

    @Bean
    public RoutingFilter simpleFilter() {
        return new RoutingFilter();
    }

    @Bean
    public LocalSessionFactoryBean getSessionFactory() {

        LocalSessionFactoryBean factoryBean = new LocalSessionFactoryBean();
        factoryBean.setDataSource(dataSource());

        Properties props = new Properties();

        props.put("hibernate.dialect", env.getProperty("hibernate.dialect", "org.hibernate.dialect.MySQLDialect"));
        props.put("hibernate.show_sql", env.getProperty("hibernate.show_sql", "false"));
        props.put("hibernate.format_sql", env.getProperty("hibernate.format_sql", "true"));
        props.put("hibernate.hbm2ddl.auto", env.getProperty("hibernate.hbm2ddl.auto", "create"));
        props.put("hibernate.connection.characterEncoding", env.getProperty("hibernate.connection.characterEncoding", "UTF-8"));
        props.put("hibernate.connection.useUnicode", env.getProperty("hibernate.connection.useUnicode", "true"));


        factoryBean.setHibernateProperties(props);
        factoryBean.setPackagesToScan("app.manage.domain");

        return factoryBean;

    }
}
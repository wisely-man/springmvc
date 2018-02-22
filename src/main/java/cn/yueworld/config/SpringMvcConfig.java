package cn.yueworld.config;

import cn.yueworld.common.tools.MyConfiguration;
import cn.yueworld.core.interceptor.MyInterceptor;
import org.apache.commons.dbcp.BasicDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.*;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

import java.util.List;

/**
 * Created by jiangyi on 2017/4/18.
 */
@Configuration
@EnableWebMvc
@EnableAspectJAutoProxy
@ComponentScan(value={"cn.yueworld.core.actions.aop", "cn.yueworld.service", "cn.yueworld.controller"})
public class SpringMvcConfig extends WebMvcConfigurerAdapter{

    @Bean
    public MyConfiguration configBean(){
        MyConfiguration config =  new MyConfiguration();
        config.setLocation(SpringApp.getInstance().getResource("classpath:conf/jdbc.properties"));
        return config;
    }


    @Bean
    public BusinessHttpMessageConverter businessHttpMessageConverter(){
        return new BusinessHttpMessageConverter();
    }


    @Bean
    public ViewResolver viewResolver(){
        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
        viewResolver.setViewClass(JstlView.class);
        viewResolver.setPrefix("/views/");
        viewResolver.setSuffix(".jsp");

        return viewResolver;
    }

    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        converters.add(businessHttpMessageConverter());
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/views/*").addResourceLocations("/views/*");
    }

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/toIndex").setViewName("index");
    }

//    @Bean
//    public ReloadableResourceBundleMessageSource reloadableResourceBundleMessageSource(){
//        ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
//        messageSource.setCacheSeconds(60);
//        messageSource.setUseCodeAsDefaultMessage(false);
//        messageSource.setDefaultEncoding("UTF-8");
//        messageSource.setBasenames(
//                "classpath:validate_messages/message",
//                "classpath:org/hibernate/validator/ValidationMessages"
//        );
//        return messageSource;
//    }


//    @Bean
//    public LocalValidatorFactoryBean localValidatorFactoryBean(){
//        LocalValidatorFactoryBean factoryBean = new LocalValidatorFactoryBean();
//        factoryBean.setProviderClass(HibernateValidator.class);
//        factoryBean.setValidationMessageSource(reloadableResourceBundleMessageSource());
//        return  factoryBean;
//    }
//
//    @Override
//    public Validator getValidator() {
//        return localValidatorFactoryBean();
//    }


    @Bean
    public MessageSource messageSource(){
        ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
        messageSource.setCacheSeconds(60);
        messageSource.setUseCodeAsDefaultMessage(false);
        messageSource.setDefaultEncoding("UTF-8");
        messageSource.setBasenames(
                "message"
//                "classpath:validate_messages/message",
//                "classpath:org/hibernate/validator/ValidationMessages"
        );
        return messageSource;
    }


    @Bean
    public MyInterceptor myInterceptor(){
        return new MyInterceptor();
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(myInterceptor());
    }

    @Bean
    public BasicDataSource basicDataSource(){
        BasicDataSource dataSource = new BasicDataSource();
        dataSource.setPassword("");
        return dataSource;
    }

    @Bean
    public BasicDataSource dataSource(){
        BasicDataSource dataSource = new BasicDataSource();
        dataSource.setDriverClassName(MyConfiguration.getProperty("db_driverClassName"));
        dataSource.setUrl(MyConfiguration.getProperty("db_url"));
        dataSource.setUsername(MyConfiguration.getProperty("db_username"));
        dataSource.setPassword(MyConfiguration.getProperty("db_password"));
        dataSource.setMaxActive(Integer.parseInt(MyConfiguration.getProperty("db_maxActive")));
        dataSource.setMaxIdle(Integer.parseInt(MyConfiguration.getProperty("db_maxIdle")));
        dataSource.setMaxWait(Long.parseLong(MyConfiguration.getProperty("db_maxWait")));
        return dataSource;
    }
    @Bean
    public SqlSessionFactoryBean sqlSessionFactoryBean(){
        SqlSessionFactoryBean factoryBean = new SqlSessionFactoryBean();
        factoryBean.setDataSource(dataSource());
        factoryBean.setMapperLocations(new Resource[]{SpringApp.getInstance().getResource(MyConfiguration.getProperty("mapper_location"))});
        return factoryBean;
    }
    @Bean
    public SqlSessionTemplate sqlSessionTemplate(){
        SqlSessionTemplate sql = null;
        try {
            sql = new SqlSessionTemplate(sqlSessionFactoryBean().getObject());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return sql;
    }

}

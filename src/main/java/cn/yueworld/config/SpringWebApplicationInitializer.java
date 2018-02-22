package cn.yueworld.config;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.DispatcherServlet;

import javax.servlet.*;
import java.util.EnumSet;


/**
 * Created by jiangyi on 2017/4/18.
 */
public class SpringWebApplicationInitializer implements WebApplicationInitializer {


    private static AnnotationConfigWebApplicationContext context=
            new AnnotationConfigWebApplicationContext();

    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {

        // 初始化WEB容器
        AnnotationConfigWebApplicationContext context =
                new AnnotationConfigWebApplicationContext();
        context.register(SpringMvcConfig.class);
        context.setServletContext(servletContext);
        // 保存容器方便获取
        SpringApp.getInstance().setContext(context);

        // 设置servlet
        ServletRegistration.Dynamic servlet =
                servletContext.addServlet("dispatcher", new DispatcherServlet(context));
        servlet.addMapping("/");
        servlet.setLoadOnStartup(1);

        CharacterEncodingFilter characterEncodingFilter = new CharacterEncodingFilter();
        characterEncodingFilter.setEncoding("UTF-8");
        characterEncodingFilter.setForceEncoding(true);
        FilterRegistration.Dynamic encodingFilter =
                servletContext.addFilter("encodingFilter", characterEncodingFilter);
        encodingFilter.addMappingForUrlPatterns(
                EnumSet.allOf(DispatcherType.class), false, "/*");
    }
}

package main;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;
import login.SecurityContext;

public class MyWebAppInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

    @Override
    protected Class<?>[] getRootConfigClasses() {
        // DB, Service, Security 설정을 담당하는 클래스들을 지정합니다.
        return new Class<?>[] { RootConfig.class, SecurityContext.class };
    }

    @Override
    protected Class<?>[] getServletConfigClasses() {
        // Controller, View 등 웹 관련 설정을 담당하는 클래스를 지정합니다.
        return new Class<?>[] { WebConfig.class };
    }

    @Override
    protected String[] getServletMappings() {
        // 모든 요청("/")을 Spring이 처리하도록 합니다.
        return new String[] { "/" };
    }
}
package main;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

public class MyWebAppInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

    @Override
    protected Class<?>[] getRootConfigClasses() {
        // Root 설정 클래스 (지금은 사용하지 않음)
        return null;
    }

    @Override
    protected Class<?>[] getServletConfigClasses() {
        // Servlet 설정 클래스 (dispatcher-servlet.xml을 대체)
        return new Class<?>[] { WebConfig.class };
    }

    @Override
    protected String[] getServletMappings() {
        // 모든 요청("/")을 DispatcherServlet이 처리하도록 함
        return new String[] { "/" };
    }
}
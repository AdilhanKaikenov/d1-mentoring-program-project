package com.epam.mentoring.springmvc.config;

import com.epam.mentoring.springmvc.filter.EncodingFilter;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import javax.servlet.Filter;
import javax.servlet.MultipartConfigElement;
import javax.servlet.ServletRegistration;

/**
 * The class that we use to register the configuration in the Spring context
 *
 * @author Kaikenov Adilhan
**/
public class DispatcherServletInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

    // This method should contain configurations that initialize Beans to initialize the bins we used the @Bean annotation
    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class[]{
                RootApplicationContextConfiguration.class
        };
    }

    // Add the configuration in which we initialize the ViewResolver
    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class[] {
                WebApplicationContextConfiguration.class
        };
    }

    @Override
    protected String[] getServletMappings() {
        return new String[] {"/"};
    }

    @Override
    protected Filter[] getServletFilters() {
        return new Filter[] { new EncodingFilter() };
    }

    @Override
    protected void customizeRegistration(ServletRegistration.Dynamic registration) {
        registration.setMultipartConfig(getMultipartConfigElement()); // Registration of MultiPartConfigElement
    }

/*
    That implementations works well even in Servlet 3.0 environment. In this post, we will implement same example again,
    but using Servlet 3.0 specific javax.servlet.MultipartConfigElement
*/
    private MultipartConfigElement getMultipartConfigElement() {
        return new MultipartConfigElement(LOCATION, MAX_FILE_SIZE, MAX_REQUEST_SIZE, FILE_SIZE_THRESHOLD);
    }

    private static final String LOCATION = "D:/temp/"; // Temporary location where files will be stored

    private static final long MAX_FILE_SIZE = 5242880; // 5MB : Max file size.
    // Beyond that size spring will throw exception.
    private static final long MAX_REQUEST_SIZE = 20971520; // 20MB : Total request size containing Multi part.

    private static final int FILE_SIZE_THRESHOLD = 0; // Size threshold after which files will be written to disk
}

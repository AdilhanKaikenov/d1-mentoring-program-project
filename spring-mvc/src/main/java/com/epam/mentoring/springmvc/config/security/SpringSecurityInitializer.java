package com.epam.mentoring.springmvc.config.security;

import org.springframework.security.web.context.AbstractSecurityWebApplicationInitializer;
import org.springframework.web.multipart.support.MultipartFilter;

import javax.servlet.ServletContext;

/*
 * Automatically register the springSecurityFilterChain Filter for every URL in your application
 * Add a ContextLoaderListener that loads the SecurityConfiguration.
 *
 * AbstractSecurityWebApplicationInitializer registers an instance of DelegatingFilterProxy.
 * The DelegatingFilterProxy#targetBeanName property is set with bean name "springSecurityFilterChain".
 *
 * The bean named "springSecurityFilterChain" is registered in the configuration imported by @EnableWebSecurity (SecurityConfiguration.class).
 *
 * web.xml example:
 * <filter>
 *     <filter-name>springSecurityFilterChain</filter-name>
 *     <filter-class>org.springframework.web.filter.DelegatingFilterProxy</filter-class>
 * </filter>
 * <filter-mapping>
 *     <filter-name>springSecurityFilterChain</filter-name>
 *     <url-pattern>/*</url-pattern>
 * </filter-mapping>
 *
 * @author Kaikenov Adilhan
*/
public class SpringSecurityInitializer extends AbstractSecurityWebApplicationInitializer {

    @Override
    protected void beforeSpringSecurityFilterChain(final ServletContext servletContext) {
        // Since we are using Spring Security we need to insert a MultipartFilter in a AbstractSecurityWebApplicationInitializer
        // implementation or else you will get a 403 error when trying to upload a file.
        insertFilters(servletContext, new MultipartFilter());
    }
}

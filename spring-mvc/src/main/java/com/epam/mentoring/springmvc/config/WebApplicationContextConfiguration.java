package com.epam.mentoring.springmvc.config;

import com.epam.mentoring.springmvc.interceptor.LoggedUserInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.CookieLocaleResolver;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.view.UrlBasedViewResolver;
import org.springframework.web.servlet.view.tiles3.TilesConfigurer;
import org.springframework.web.servlet.view.tiles3.TilesView;

import java.util.Locale;

/**
 * @author Kaikenov Adilhan
**/
@Configuration
@EnableWebMvc
@ComponentScan("com.epam.mentoring.springmvc.controller")
public class WebApplicationContextConfiguration implements WebMvcConfigurer {

    private static final int SECONDS_PER_DAY = 24 * 60 * 60;

    @Autowired
    private LoggedUserInterceptor loggedUserInterceptor;

    @Bean
    public TilesConfigurer tilesConfigurer() {
        final String[] definitions = new String[]{
                "/WEB-INF/views/defs/components.xml",
                "/WEB-INF/views/defs/templates.xml",
                "/WEB-INF/views/defs/pages.xml"};

        final TilesConfigurer tilesConfigurer = new TilesConfigurer();
        tilesConfigurer.setDefinitions(definitions);
        tilesConfigurer.setCheckRefresh(true);

        return tilesConfigurer;
    }

/*
        To utilize Apache Commons FileUpload for handling multipart requests, all we need to do is configure multipartResolver
    bean with class as org.springframework.web.multipart.commons.CommonsMultipartResolver.

        Spring provides file upload support using MultiPartResolver interface and provides two out-of-box implementations
    for that but it works equally well with Servlet 3.x containers.

        Spring CommonsMultipartResolver is a MultipartResolver implementation for use with Apache Commons FileUpload.
    It requires apache commons-fileupload.jar to be present on classpath. It’s not specific to Servlet 3 environment

    @Bean
    public CommonsMultipartResolver multipartResolver() {
        final CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver();
        multipartResolver.setMaxUploadSize(5000000);
        multipartResolver.setDefaultEncoding("UTF-8");
        return multipartResolver;
    }
*/

    @Bean
    public MessageSource messageSource() {
        // ReloadableResourceBundleMessageSource is responsible for loading the messages property files.
        final ReloadableResourceBundleMessageSource messageResource = new ReloadableResourceBundleMessageSource();
        messageResource.setBasename("classpath:messages");
        messageResource.setDefaultEncoding("UTF-8");
        return messageResource;
    }

    @Bean
    public LocaleChangeInterceptor localeInterceptor() {
        // LocaleChangeInterceptor allows to change the current locale on every request via a configurable request parameter.
        LocaleChangeInterceptor interceptor = new LocaleChangeInterceptor();
        interceptor.setParamName("lang");
        return interceptor;
    }

    // In Spring MVC application, if you do not configure the Spring’s LocaleResolver,
    // it will use the default AcceptHeaderLocaleResolver, which does not allow to change the locale.
    @Bean
    public LocaleResolver localeResolver() {
        final CookieLocaleResolver localeResolver = new CookieLocaleResolver();
        localeResolver.setDefaultLocale(Locale.ENGLISH);
        localeResolver.setCookieName("locale");

        localeResolver.setCookieMaxAge(SECONDS_PER_DAY);
        return localeResolver;
    }

    // Resolve logical String-based view names returned from a handler to an actual View with which to render to the response
    @Override
    public void configureViewResolvers(final ViewResolverRegistry registry) {
        final UrlBasedViewResolver viewResolver = new UrlBasedViewResolver();
        viewResolver.setViewClass(TilesView.class);

        registry.viewResolver(viewResolver);
    }

    @Override
    public void addResourceHandlers(final ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/resources/**").addResourceLocations("/resources/");
    }

    @Override
    public void addInterceptors(final InterceptorRegistry registry) {
        registry.addInterceptor(localeInterceptor());
        registry.addInterceptor(loggedUserInterceptor).addPathPatterns("/**");
//        .excludePathPatterns(); we can exclude some url paths
    }

    @Override
    public LocalValidatorFactoryBean getValidator() {
        final LocalValidatorFactoryBean bean = new LocalValidatorFactoryBean();
        bean.setValidationMessageSource(messageSource());
        return bean;
    }
}

package com.epam.mentoring.springmvc.config;

import com.epam.mentoring.springmvc.config.security.SecurityConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * @author Kaikenov Adilhan
**/
@Configuration
@Import({DataBaseConfiguration.class, SecurityConfiguration.class})
@ComponentScan("com.epam.mentoring.springmvc")
public class RootApplicationContextConfiguration {

}

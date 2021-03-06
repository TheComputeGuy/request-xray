package dev.maverick.xray.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.PathMatchConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.util.UrlPathHelper;

/**
 * 
 * Configuration for embedded tomcat and spring startup
 * 
 * @author Shubham Pharande
 *
 */
@Configuration
public class SpringApplicationConfig implements WebMvcConfigurer {

	@Override
    public void configurePathMatch(PathMatchConfigurer configurer) {
        UrlPathHelper helper = new UrlPathHelper();
        helper.setUrlDecode(false);
        configurer.setUrlPathHelper(helper);
    }
	
}

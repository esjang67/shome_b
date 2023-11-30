package com.esjang.sthome.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

// react와 연결(ip)

@Configuration
public class WebConfig implements WebMvcConfigurer {

	@Override
	public void addCorsMappings(CorsRegistry registry) {
		registry.addMapping("/**")
		.allowedOrigins("http://localhost:3000")
		.allowedOrigins("https://sweethome-24e1b.web.app")	// react 서버(배포후 변경해야함)
		.allowedMethods("GET","POST","DELETE","PUT");		// get, post만 기본적으로 허용되어있므로 재설정함
	}

}

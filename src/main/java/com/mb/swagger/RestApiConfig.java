package com.mb.swagger;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * Swagger的配置实际上就是自定义一个Config类，通过java编码的方式实现配置
 */
@EnableWebMvc
@EnableSwagger2
@Configuration
@ComponentScan("com.mb.test.controller") // 需要扫描的包路径
public class RestApiConfig {

	@Bean
	public Docket createRestApi() {
		return new Docket(DocumentationType.SWAGGER_2)
				.apiInfo(apiInfo())
				.select()
				.apis(RequestHandlerSelectors.basePackage("com.mb.test.controller"))// 需要扫描的包路径
				.paths(PathSelectors.any())
				.build();
	}

	private ApiInfo apiInfo() {
		return new ApiInfoBuilder()
				.title("SpringMVC中使用Swagger2构建RESTful APIs")
				.termsOfServiceUrl("https://github.com/lidong1665")
				.contact(new Contact("liujie", "http://blog.csdn.net/u010046908", "liujie13@metersbonwe.com"))
				.version("1.0.0")
				.build();
	}

}

package com.yiyang.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * SpringDoc 接口帮助文档
 */
@Configuration
public class SpringDocConfig {
    @Bean
    public OpenAPI yiyangOpenAPI() {
        OpenAPI openAPI = new OpenAPI();
        openAPI.info(new Info()
                // 文档标题
                .title("颐养中心接口文档")
                // 描述
                .description("基于 SpringBoot 3.3.4 构建项目")
                // 版本
                .version("v1.0.0")
                // 许可证
                .license(new License().name("Apache 2.0"))
                // 关于作者
                .contact(new Contact().name("新明").email("Ming2243274325@outlook.com"))
        );
        return openAPI;
    }
}

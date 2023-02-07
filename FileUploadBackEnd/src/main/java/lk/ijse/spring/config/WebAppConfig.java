/**
 * created by kasun weerasinghe
 * Date: 2023-02-07
 * Time: 10:07
 * Project Name: FileUploadBackEnd
 */

package lk.ijse.spring.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages = "lk.ijse.spring")
public class WebAppConfig {
}

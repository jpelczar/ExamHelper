package io.jpelczar.examhelper.security

import org.springframework.context.annotation.Configuration
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer
import org.springframework.core.Ordered
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry



@Configuration
class MvcSecurityConfiguration: WebMvcConfigurer {
    override fun addViewControllers(registry: ViewControllerRegistry) {
        registry.addViewController("/login").setViewName("login")
        registry.setOrder(Ordered.HIGHEST_PRECEDENCE)
    }
}
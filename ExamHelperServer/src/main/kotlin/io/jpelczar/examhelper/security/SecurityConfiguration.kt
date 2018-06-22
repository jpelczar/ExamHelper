package io.jpelczar.examhelper.security

import io.jpelczar.examhelper.user.Role
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.context.annotation.Configuration
import org.springframework.security.authentication.dao.DaoAuthenticationProvider
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.web.access.AccessDeniedHandler


@Configuration
class SecurityConfiguration : WebSecurityConfigurerAdapter() {

    @Autowired
    lateinit var accessDeniedHandler: AccessDeniedHandler

    @Autowired
    @Qualifier("examUserDetailsService")
    lateinit var userDetailsService: UserDetailsService

    override fun configure(http: HttpSecurity) {

        http.authorizeRequests()
                .antMatchers("/", "/home", "/about", "/h2-console/**").permitAll()
                .antMatchers("/admin/**").hasAnyAuthority(Role.ADMIN.toString())
                .antMatchers("/student/**").hasAnyAuthority(Role.STUDENT.toString())
                .anyRequest().authenticated()
                .and()
                .formLogin().loginPage("/login").permitAll()
                .and()
                .logout().permitAll()
                .and()
                .exceptionHandling().accessDeniedHandler(accessDeniedHandler)
    }

    override fun configure(auth: AuthenticationManagerBuilder) {
        val daoAuthenticationProvider = DaoAuthenticationProvider()
        daoAuthenticationProvider.setUserDetailsService(userDetailsService)
        daoAuthenticationProvider.setPasswordEncoder(BCryptPasswordEncoder(11))
        auth.authenticationProvider(daoAuthenticationProvider)
    }
}
package br.com.amorim.supermarket.configuration.security;

import br.com.amorim.supermarket.service.jwt.JwtAuthFilter;
import br.com.amorim.supermarket.service.jwt.JwtService;
import br.com.amorim.supermarket.service.userdata.userdetail.UserLoginServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.filter.OncePerRequestFilter;

import static br.com.amorim.supermarket.configuration.security.roles.RoleType.*;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Lazy
    @Autowired
    private UserLoginServiceImpl userLoginService;
    @Autowired
    private JwtService jwtService;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    public OncePerRequestFilter jwtFilter() {
        return new JwtAuthFilter(jwtService, userLoginService);
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userLoginService)
                .passwordEncoder(passwordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .authorizeRequests()
                .antMatchers(HttpMethod.GET, "/api/salary/{id}")
                .hasAnyRole(EMPLOYEE.role)
                    .antMatchers(HttpMethod.GET, "/api/person/{id}")
                    .hasAnyRole(EMPLOYEE.role)
                .antMatchers(HttpMethod.GET, "/api/employee/{id}")
                .hasAnyRole(EMPLOYEE.role)
                    .antMatchers(HttpMethod.GET, "/api/subsection/{id}")
                    .hasAnyRole(EMPLOYEE.role)
                .antMatchers(HttpMethod.GET, "/api/mainsection/{id}")
                .hasAnyRole(EMPLOYEE.role)
                    .antMatchers(HttpMethod.GET, "/api/department/{id}")
                    .hasAnyRole(EMPLOYEE.role)
                .antMatchers(HttpMethod.GET, "/api/establishment/{id}")
                .hasAnyRole(EMPLOYEE.role)
                    .antMatchers(HttpMethod.GET, "/api/user/{id}")
                    .hasAnyRole(EMPLOYEE.role)
                .antMatchers("/api/employee/**")
                .hasAnyRole(ADMIN.role, HEAD.role, HR.role)
                    .antMatchers("/api/jobposition/**")
                    .hasAnyRole(ADMIN.role, HEAD.role, HR.role)
                .antMatchers("/api/salary/**")
                .hasAnyRole(ADMIN.role, HEAD.role, HR.role)
                    .antMatchers("/api/otheraddition/**")
                    .hasAnyRole(ADMIN.role, HEAD.role, HR.role)
                .antMatchers("/api/otherdiscount/**")
                .hasAnyRole(ADMIN.role, HEAD.role, HR.role)
                    .antMatchers("/api/person/**")
                    .hasAnyRole(ADMIN.role, HEAD.role, HR.role)
                .antMatchers("/api/establishment/**")
                .hasAnyRole(ADMIN.role, HEAD.role, MANAGER.role, HR.role)
                    .antMatchers("/api/provider/**")
                    .hasAnyRole(ADMIN.role, HEAD.role, MANAGER.role, DEPARTMENT_MANAGER.role, BUYER.role)
                .antMatchers("/api/product/**")
                .hasAnyRole(ADMIN.role, HEAD.role, MANAGER.role, DEPARTMENT_MANAGER.role, SECTION_MANAGER.role, BUYER.role)
                    .antMatchers("/api/subsection/**")
                    .hasAnyRole(ADMIN.role, HEAD.role, MANAGER.role, DEPARTMENT_MANAGER.role, SECTION_MANAGER.role, BUYER.role, HR.role)
                .antMatchers("/api/mainsection/**")
                .hasAnyRole(ADMIN.role, HEAD.role, MANAGER.role, DEPARTMENT_MANAGER.role, SECTION_MANAGER.role, BUYER.role, HR.role)
                    .antMatchers("/api/department/**")
                    .hasAnyRole(ADMIN.role, HEAD.role, DEPARTMENT_MANAGER.role, MANAGER.role, BUYER.role, HR.role)
                .antMatchers("/api/user/hr/**")
                .hasAnyRole(ADMIN.role, HEAD.role, DEPARTMENT_MANAGER.role, MANAGER.role, HR.role)
                    .antMatchers(HttpMethod.GET,"/api/user/**")
                    .hasAnyRole(ADMIN.role, HEAD.role, DEPARTMENT_MANAGER.role, MANAGER.role, HR.role)
                .antMatchers(HttpMethod.GET,"/api/financial-statement/**")
                .hasAnyRole(ADMIN.role, DEPARTMENT_MANAGER.role, MANAGER.role, FINANCE.role, SECTION_MANAGER.role, BUYER.role)
                    .antMatchers(HttpMethod.GET,"/api/goods-issue/**")
                    .hasAnyRole(ADMIN.role, DEPARTMENT_MANAGER.role, MANAGER.role, FINANCE.role, SECTION_MANAGER.role, BUYER.role)
                .antMatchers(HttpMethod.GET,"/api/goods-receipt/**")
                .hasAnyRole(ADMIN.role, DEPARTMENT_MANAGER.role, MANAGER.role, FINANCE.role, SECTION_MANAGER.role, BUYER.role)
                    .antMatchers("/api/financial-statement/**")
                    .hasAnyRole(FINANCE.role, HEAD.role)
                .antMatchers("/api/goods-issue/**")
                .hasAnyRole(FINANCE.role, HEAD.role)
                    .antMatchers("/api/goods-receipt/**")
                    .hasAnyRole(FINANCE.role, HEAD.role)
                .antMatchers(HttpMethod.POST, "/api/user/**")
                .permitAll()
                .anyRequest().authenticated()
                .and().sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and().addFilterBefore(jwtFilter(), UsernamePasswordAuthenticationFilter.class);
    }
}

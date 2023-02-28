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

import static br.com.amorim.supermarket.configuration.security.roles.RoleType.ADMIN;

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
                .antMatchers("/api/employee/**")
                .hasAnyRole(ADMIN.role)
                .antMatchers("/api/depatment/**")
                .hasAnyRole(ADMIN.role)
                .antMatchers("/api/establishment/**")
                .hasAnyRole(ADMIN.role)
                .antMatchers("/api/jobposition/**")
                .hasAnyRole(ADMIN.role)
                .antMatchers("/api/mainsection/**")
                .hasAnyRole(ADMIN.role)
                .antMatchers("/api/otheraddition/**")
                .hasAnyRole(ADMIN.role)
                .antMatchers("/api/otherdiscount/**")
                .hasAnyRole(ADMIN.role)
                .antMatchers("/api/person/**")
                .hasAnyRole(ADMIN.role)
                .antMatchers("/api/product/**")
                .hasAnyRole(ADMIN.role)
                .antMatchers("/api/provider/**")
                .hasAnyRole(ADMIN.role)
                .antMatchers("/api/salary/**")
                .hasAnyRole(ADMIN.role)
                .antMatchers("/api/subsection/**")
                .hasAnyRole(ADMIN.role)
                .antMatchers(HttpMethod.POST, "/api/user/**")
                .permitAll()
                .anyRequest().authenticated()
                .and().sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and().addFilterBefore(jwtFilter(), UsernamePasswordAuthenticationFilter.class);
    }
}

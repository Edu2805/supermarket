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
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.filter.OncePerRequestFilter;

import static br.com.amorim.supermarket.configuration.security.roles.RoleType.ADMIN;
import static br.com.amorim.supermarket.configuration.security.roles.RoleType.BUYER;
import static br.com.amorim.supermarket.configuration.security.roles.RoleType.DEPARTMENT_MANAGER;
import static br.com.amorim.supermarket.configuration.security.roles.RoleType.EMPLOYEE;
import static br.com.amorim.supermarket.configuration.security.roles.RoleType.FINANCE;
import static br.com.amorim.supermarket.configuration.security.roles.RoleType.HEAD;
import static br.com.amorim.supermarket.configuration.security.roles.RoleType.HR;
import static br.com.amorim.supermarket.configuration.security.roles.RoleType.MANAGER;
import static br.com.amorim.supermarket.configuration.security.roles.RoleType.RECEIPT;
import static br.com.amorim.supermarket.configuration.security.roles.RoleType.SECTION_MANAGER;

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
                .antMatchers(HttpMethod.POST,"/api/user/username/**")
                .hasAnyRole(ADMIN.role, DEPARTMENT_MANAGER.role, MANAGER.role, FINANCE.role, SECTION_MANAGER.role, BUYER.role, EMPLOYEE.role, HEAD.role, HR.role, RECEIPT.role)

                //Establishment
                .antMatchers("/api/establishment/**")
                .hasAnyRole(ADMIN.role, HEAD.role, MANAGER.role, HR.role)

                //Department
                .antMatchers("/api/department/**")
                .hasAnyRole(ADMIN.role, HEAD.role, DEPARTMENT_MANAGER.role, MANAGER.role, BUYER.role, HR.role)

                //Mainsection
                .antMatchers("/api/mainsection/**")
                .hasAnyRole(ADMIN.role, HEAD.role, MANAGER.role, DEPARTMENT_MANAGER.role, SECTION_MANAGER.role, BUYER.role, HR.role)

                //Subsection
                .antMatchers("/api/subsection/**")
                .hasAnyRole(ADMIN.role, HEAD.role, MANAGER.role, DEPARTMENT_MANAGER.role, SECTION_MANAGER.role, BUYER.role, HR.role)

                //Provider
                .antMatchers("/api/provider/**")
                .hasAnyRole(ADMIN.role, HEAD.role, MANAGER.role, DEPARTMENT_MANAGER.role, BUYER.role)

                //Unity
                .antMatchers("/api/unity/**")
                .hasAnyRole(ADMIN.role, HEAD.role, MANAGER.role, DEPARTMENT_MANAGER.role, SECTION_MANAGER.role, BUYER.role)

                //Product
                .antMatchers("/api/product/**")
                .hasAnyRole(ADMIN.role, HEAD.role, MANAGER.role, DEPARTMENT_MANAGER.role, SECTION_MANAGER.role, BUYER.role)

                //Person
                .antMatchers("/api/person/**")
                .hasAnyRole(ADMIN.role, HEAD.role, HR.role)

                //Scholarity
                .antMatchers("/api/scholarity/**")
                .hasAnyRole(ADMIN.role, HEAD.role, HR.role)

                //Jobposition
                .antMatchers("/api/jobposition/**")
                .hasAnyRole(ADMIN.role, HEAD.role, HR.role)

                //Salary
                .antMatchers("/api/salary/**")
                .hasAnyRole(ADMIN.role, HEAD.role, HR.role)

                //Employee
                .antMatchers("/api/employee/**")
                .hasAnyRole(ADMIN.role, HEAD.role, HR.role)

                //User (hr)
                .antMatchers("/api/user/hr/**")
                .hasAnyRole(ADMIN.role, HEAD.role, DEPARTMENT_MANAGER.role, MANAGER.role, HR.role)

                //Otheraddition
                .antMatchers("/api/otheraddition/**")
                .hasAnyRole(ADMIN.role, HEAD.role, HR.role)

                //Otherdiscount
                .antMatchers("/api/otherdiscount/**")
                .hasAnyRole(ADMIN.role, HEAD.role, HR.role)

                //Goods issue
                .antMatchers("/api/goods-issue/**")
                .hasAnyRole(ADMIN.role, BUYER.role, RECEIPT.role)

                //Goods receipt
                .antMatchers("/api/goods-receipt/**")
                .hasAnyRole(ADMIN.role, BUYER.role, RECEIPT.role)

                //Historical goods issue
                .antMatchers(HttpMethod.GET,"/api/historical-goods-issue/**")
                .hasAnyRole(ADMIN.role, DEPARTMENT_MANAGER.role, MANAGER.role, FINANCE.role, SECTION_MANAGER.role, BUYER.role)

                //Historical goods receipt
                .antMatchers(HttpMethod.GET,"/api/historical-goods-receipt/**")
                .hasAnyRole(ADMIN.role, DEPARTMENT_MANAGER.role, MANAGER.role, FINANCE.role, SECTION_MANAGER.role, BUYER.role)

                //Sale number
                .antMatchers("/api/salenumber/**")
                .hasAnyRole(ADMIN.role, DEPARTMENT_MANAGER.role, MANAGER.role, FINANCE.role, SECTION_MANAGER.role, BUYER.role, EMPLOYEE.role, HEAD.role, HR.role, RECEIPT.role)

                //Operacional expensies reports
                .antMatchers("/api/financial-report/expensies")
                .hasAnyRole(ADMIN.role, DEPARTMENT_MANAGER.role, MANAGER.role, FINANCE.role, SECTION_MANAGER.role, BUYER.role, HEAD.role, HR.role, RECEIPT.role)

                //Operacional sales reports
                .antMatchers("/api/financial-report/sales")
                .hasAnyRole(ADMIN.role, DEPARTMENT_MANAGER.role, MANAGER.role, FINANCE.role, SECTION_MANAGER.role, BUYER.role, HEAD.role, HR.role, RECEIPT.role)

                //Operacional historical expensies reports
                .antMatchers("/api/financial-report/expensies/historical")
                .hasAnyRole(ADMIN.role, DEPARTMENT_MANAGER.role, MANAGER.role, FINANCE.role, SECTION_MANAGER.role, BUYER.role, HEAD.role, HR.role, RECEIPT.role)

                //Operacional historical sales reports
                .antMatchers("/api/financial-report/sales/historical")
                .hasAnyRole(ADMIN.role, DEPARTMENT_MANAGER.role, MANAGER.role, FINANCE.role, SECTION_MANAGER.role, BUYER.role, HEAD.role, HR.role, RECEIPT.role)

                //Financial reports
                .antMatchers("/api/financial-report/**")
                .hasAnyRole(ADMIN.role, MANAGER.role, FINANCE.role, HEAD.role)

                //User (GET)
                .antMatchers(HttpMethod.GET,"/api/user/**")
                .hasAnyRole(ADMIN.role, HEAD.role, DEPARTMENT_MANAGER.role, MANAGER.role, HR.role)

                //Goods issue(GET)
                .antMatchers(HttpMethod.GET,"/api/goods-issue/**")
                .hasAnyRole(ADMIN.role, DEPARTMENT_MANAGER.role, MANAGER.role, FINANCE.role, SECTION_MANAGER.role, BUYER.role)

                //Goods receipt(GET)
                .antMatchers(HttpMethod.GET,"/api/goods-receipt/**")
                .hasAnyRole(ADMIN.role, DEPARTMENT_MANAGER.role, MANAGER.role, FINANCE.role, SECTION_MANAGER.role, BUYER.role)

                //Financial report(GET)
                .antMatchers(HttpMethod.GET,"/api/financial-report/**")
                .hasAnyRole(ADMIN.role, DEPARTMENT_MANAGER.role, MANAGER.role, FINANCE.role, SECTION_MANAGER.role, BUYER.role)

                //Establishment(GET)
                .antMatchers(HttpMethod.GET, "/api/establishment/{id}")
                .hasAnyRole(EMPLOYEE.role)

                //Salary(GET)
                .antMatchers(HttpMethod.GET, "/api/salary/{id}")
                .hasAnyRole(EMPLOYEE.role)

                //Person(GET)
                .antMatchers(HttpMethod.GET, "/api/person/{id}")
                .hasAnyRole(EMPLOYEE.role)

                //Employee(GET)
                .antMatchers(HttpMethod.GET, "/api/employee/{id}")
                .hasAnyRole(EMPLOYEE.role)

                //Subsection(GET)
                .antMatchers(HttpMethod.GET, "/api/subsection/{id}")
                .hasAnyRole(EMPLOYEE.role)

                //Main section(GET)
                .antMatchers(HttpMethod.GET, "/api/mainsection/{id}")
                .hasAnyRole(EMPLOYEE.role)

                //Department(GET)
                .antMatchers(HttpMethod.GET, "/api/department/{id}")
                .hasAnyRole(EMPLOYEE.role)

                //User(GET)
                .antMatchers(HttpMethod.GET, "/api/user/{id}")
                .hasAnyRole(EMPLOYEE.role)

                //Salariesavailable(Get)
                .antMatchers(HttpMethod.GET, "/api/salary/salariesavailable/**")
                .hasAnyRole(ADMIN.role, HEAD.role, DEPARTMENT_MANAGER.role, MANAGER.role, HR.role)

                //Jobpositionsavailable(Get)
                .antMatchers(HttpMethod.GET, "/api/jobposition/jobpositionsavailable/**")
                .hasAnyRole(ADMIN.role, HEAD.role, DEPARTMENT_MANAGER.role, MANAGER.role, HR.role)

                //Peopleavailable(Get)
                .antMatchers(HttpMethod.GET, "/api/jobposition/peopleavailable/**")
                .hasAnyRole(ADMIN.role, HEAD.role, DEPARTMENT_MANAGER.role, MANAGER.role, HR.role)

                //User(POST)
                .antMatchers(HttpMethod.POST, "/api/user/**")
                .permitAll()

                //Attachment
                .antMatchers("/api/attachment/**")
                .permitAll()

                //Role(GET)
                .antMatchers(HttpMethod.GET, "/api/role/**")
                .permitAll()
                .anyRequest().authenticated()
                .and().sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and().addFilterBefore(jwtFilter(), UsernamePasswordAuthenticationFilter.class);

    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers(
                "/v2/api-docs",
                "/configuration/ui",
                "/swagger-resources/**",
                "/configuration/security",
                "/swagger-ui/**",
                "/webjars/**");
    }
}

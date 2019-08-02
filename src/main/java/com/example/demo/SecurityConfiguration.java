package com.example.demo;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter{
    @Bean
    public BCryptPasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Autowired
    private SSUserDetailsService userDetailsService;

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetailsService userDetailsServiceBean() throws Exception {
        return new SSUserDetailsService(userRepository);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                //.antMatchers("/", "/h2-console/**","/register").permitAll()
                //.antMatchers("/").access("hasAnyAuthority('USER','ADMIN')")
                /* working previous ...
                .antMatchers("/", "/h2-console/**","/register", "/add").access("hasAnyAuthority('USER','ADMIN')")
                .antMatchers("/admin").access("hasAuthority('ADMIN')")
                */
                //the following two line to replace above commented two lines
                .antMatchers("/", "/add", "/process","/h2-console/**").permitAll()
                .antMatchers("/register", "/logoutconfirm", "/detail/**").permitAll()
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .loginPage("/login").permitAll()
                .and()
                .logout()
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                //working previous     .logoutSuccessUrl("/login").permitAll()
                //the following one line to replace above //the following two line to replace above two
                .logoutSuccessUrl("/logoutconfirm").permitAll() // if logout is successful it'll take us back to logout page.
                .and()
                .httpBasic();
                http
                    .csrf().disable();
                http
                    .headers().frameOptions().disable();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception{
//        auth.inMemoryAuthentication()
//                .withUser("dave")
//                .password(passwordEncoder().encode("begreat"))
//                .authorities("ADMIN")
//
//            .and()
//                .withUser("user").password(passwordEncoder().encode("password"))
//                .authorities("USER");

        auth.userDetailsService(userDetailsServiceBean())
                .passwordEncoder(passwordEncoder());
        }
    }


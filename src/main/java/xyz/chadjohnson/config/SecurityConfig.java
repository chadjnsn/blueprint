package xyz.chadjohnson.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import xyz.chadjohnson.services.DemoSecurityService;

/**
 * A class to setup and configure spring security.
 *
 * <p>
 *     Here we are enabling web security and global method security so we can control which users have access
 *     to which parts of the system. Spring security recommends using global method security at the service
 *     layer instead of the controller layer, which is why you will find security annotations in the
 *     {@link DemoSecurityService} interface. We're also setting up users with a simple in-memory
 *     authentication scheme, but in a real project you would want to do something more permanent (and without
 *     passwords in the code base...).
 * </p>
 *
 * @author Chad Johnson
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter{

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeRequests()
                    .antMatchers("/demo/secure/**").hasAnyRole("USER", "ADMIN")
                    .antMatchers("/demo/admin/**").hasAnyRole("ADMIN")
                    .anyRequest().permitAll()
                .and().httpBasic();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication().withUser("chad").password("password").roles("USER");
        auth.inMemoryAuthentication().withUser("admin").password("password").roles("ADMIN", "USER");
    }
}

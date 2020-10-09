package top.fx7.yinlu.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    UserDetailsServiceImpl userDetailsService;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService);
    }
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        String loginPageUrl = "/login.html";
        String loginProcessingUrl = "/login";
        String loginFailureUrl = "/login.html?error";
        String loginSuccessUrl = "/index.html";
        String logoutUrl = "/logout";
        String logoutSuccessUrl = "/login.html?logout";
        //白名单--不需要登录就能访问的页面
        String[] antPatterns = {
                loginPageUrl,
                "/css/**",
                "/img/**",
                "/js/**",
                "/fonts/**",
                "/font-awesome-4.7.0/**"
        };
        http.csrf().disable()
                .authorizeRequests()
                .antMatchers(antPatterns).permitAll()
                .anyRequest().authenticated()
                .and().formLogin()
                .loginPage(loginPageUrl)
                .loginProcessingUrl(loginProcessingUrl)
                .failureUrl(loginFailureUrl)
                .defaultSuccessUrl(loginSuccessUrl)
                .and().logout()
                .logoutUrl(logoutUrl)
                .logoutSuccessUrl(logoutSuccessUrl);
    }
}


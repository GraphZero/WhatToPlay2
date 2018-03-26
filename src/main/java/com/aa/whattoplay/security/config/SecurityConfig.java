
package com.aa.whattoplay.security.config;
import com.aa.whattoplay.security.application.UserAuthService;
import com.aa.whattoplay.security.config.rest.RESTAuthenticationEntryPoint;
import com.aa.whattoplay.security.config.rest.RESTAuthenticationFailureHandler;
import com.aa.whattoplay.security.config.rest.RESTAuthenticationSuccessHandler;
import com.aa.whattoplay.security.config.rest.RESTLogoutSuccessHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity @RequiredArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    private final RESTAuthenticationEntryPoint authenticationEntryPoint;
    private final RESTAuthenticationFailureHandler authenticationFailureHandler;
    private final RESTAuthenticationSuccessHandler authenticationSuccessHandler;
    private final RESTLogoutSuccessHandler restLogoutSuccessHandler;
    private UserAuthService userAuthService;
    private static final String REALM="MY_TEST_REALM";

    @Autowired
    public void setUserAuthService(UserAuthService userAuthService) {
        this.userAuthService = userAuthService;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
            .csrf().disable()
            .authorizeRequests()
                .anyRequest().permitAll()
            .and()
                .exceptionHandling()
                .authenticationEntryPoint(authenticationEntryPoint)
            .and()
                .formLogin()
                .successHandler(authenticationSuccessHandler)
                .failureHandler(authenticationFailureHandler)
            .and()
                .logout()
                .logoutSuccessHandler(restLogoutSuccessHandler)
            .and()
                .httpBasic()
                .realmName(REALM)
                .authenticationEntryPoint(authenticationEntryPoint)
            .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS);

    }


        @Override
        protected void configure(AuthenticationManagerBuilder auth) throws Exception {
            auth.authenticationProvider(authenticationProvider());
        }

        @Override
        public void configure(WebSecurity web) {
            web.ignoring().antMatchers(HttpMethod.OPTIONS, "/**");
        }

        @Bean
        public DaoAuthenticationProvider authenticationProvider() {
            DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
            authProvider.setPasswordEncoder(encoder());
            authProvider.setUserDetailsService(userAuthService);
            return authProvider;
        }

        @Bean
        public PasswordEncoder encoder() {
            return new BCryptPasswordEncoder(11);
        }



}

package io.pivotal.workshop.directory.config;

import io.pivotal.workshop.directory.security.DirectoryUserDetailsService;
import org.springframework.boot.actuate.autoconfigure.security.EndpointRequest;
import org.springframework.boot.autoconfigure.security.StaticResourceRequest;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;



@Configuration
public class DirectorySecurityConfig extends WebSecurityConfigurerAdapter{


    private DirectoryUserDetailsService userDetailsService;

    public DirectorySecurityConfig(DirectoryUserDetailsService userDetailsService){
        this.userDetailsService = userDetailsService;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .requestMatchers(EndpointRequest.to("status", "info"))
                .permitAll()

                .requestMatchers(EndpointRequest.toAnyEndpoint())
                .hasRole("ACTUATOR")


                .requestMatchers(StaticResourceRequest.toCommonLocations())
                .permitAll()

                .antMatchers("/api/**").hasRole("ADMIN")
                .antMatchers("/").permitAll()   //.antMatchers("/").hasRole("USER")

                .and()
                .formLogin();  //httpBasic();
    }

    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(this.userDetailsService);
    }

}


//Simple override for Username and Password

/*
@Configuration
public class DirectorySecurityConfig {

    @Bean
    public UserDetailsService userDetailsService() {
        return new InMemoryUserDetailsManager(
                User
                        .withDefaultPasswordEncoder()
                        .username("springboot")
                        .password("workshop")
                        .roles("USER")
                        .build());
    }


}
*/


// Simple override with WebSecurityConfigurerAdapter
/*
@Configuration
public class DirectorySecurityConfig extends WebSecurityConfigurerAdapter{

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .inMemoryAuthentication().passwordEncoder(passwordEncoder)
                    .withUser("springboot").password(passwordEncoder.encode("workshop")).roles("USER")
                .and()
                    .withUser("admin").password(passwordEncoder.encode("admin")).roles("ADMIN");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                    .anyRequest().fullyAuthenticated()
                .and()
                    .httpBasic();
    }
}
*/

// Using JDBC: https://github.com/spring-projects/spring-boot/tree/master/spring-boot-samples/spring-boot-sample-web-secure-jdbc
/*
@Configuration
public class DirectorySecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests().antMatchers("/css/**").permitAll().anyRequest()
                .fullyAuthenticated().and().formLogin().loginPage("/login")
                .failureUrl("/login?error").permitAll().and().logout().permitAll();
    }

    @Bean
    public JdbcUserDetailsManager jdbcUserDetailsManager(DataSource dataSource) {
        JdbcUserDetailsManager jdbcUserDetailsManager = new JdbcUserDetailsManager();
        jdbcUserDetailsManager.setDataSource(dataSource);
        return jdbcUserDetailsManager;
    }

}
*/

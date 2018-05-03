package configurations;

import filters.JWTAuthenticationFilter;
import filters.JWTLoginFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.http.HttpMethod;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private HibernateTransactionManager manager;
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable().authorizeRequests()
                .antMatchers("/").permitAll()
                .antMatchers(HttpMethod.POST, "/login").permitAll()
                .antMatchers(HttpMethod.GET, "/getallusers").permitAll()
                .antMatchers(HttpMethod.GET, "/getuserbyid/*").permitAll()
                .antMatchers(HttpMethod.DELETE, "/delete/*").permitAll()
                .antMatchers(HttpMethod.PUT, "/update/*").permitAll()
                .antMatchers(HttpMethod.POST, "/add").permitAll()
                .antMatchers(HttpMethod.GET, "/getallcategories").permitAll()
                .antMatchers(HttpMethod.GET, "/getcategorybyid/*").permitAll()
                .antMatchers(HttpMethod.DELETE, "/deletecategory/*").permitAll()
                .antMatchers(HttpMethod.PUT, "/updatecategory/*").permitAll()
                .antMatchers(HttpMethod.POST, "/addcategory").permitAll()
                .antMatchers(HttpMethod.GET, "/getallquestiontypes").permitAll()
                .antMatchers(HttpMethod.GET, "/getquestiontypebyid/*").permitAll()
                .antMatchers(HttpMethod.DELETE, "/deletequestiontype/*").permitAll()
                .antMatchers(HttpMethod.PUT, "/updatequestiontype/*").permitAll()
                .antMatchers(HttpMethod.POST, "/addquestiontype").permitAll()
                .antMatchers(HttpMethod.GET, "/getallquestions").permitAll()
                .antMatchers(HttpMethod.GET, "/getquestionbyid/*").permitAll()
                .antMatchers(HttpMethod.DELETE, "/deletequestion/*").permitAll()
                .antMatchers(HttpMethod.PUT, "/updatequestion/*").permitAll()
                .antMatchers(HttpMethod.POST, "/addquestion").permitAll()
                .antMatchers(HttpMethod.GET, "/getallanswers").permitAll()
                .antMatchers(HttpMethod.GET, "/getanswerbyid/*").permitAll()
                .antMatchers(HttpMethod.DELETE, "/deleteanswer/*").permitAll()
                .antMatchers(HttpMethod.PUT, "/updateanswer/*").permitAll()
                .antMatchers(HttpMethod.POST, "/addanswer").permitAll()
                .antMatchers(HttpMethod.GET, "/getallinterviews").permitAll()
                .antMatchers(HttpMethod.GET, "/getinterviewbyid/*").permitAll()
                .antMatchers(HttpMethod.DELETE, "/deleteinterview/*").permitAll()
                .antMatchers(HttpMethod.PUT, "/updateinterview/*").permitAll()
                .antMatchers(HttpMethod.POST, "/addinterview").permitAll()
                .antMatchers(HttpMethod.POST, "/uploadfile").permitAll()
                .antMatchers(HttpMethod.GET, "/exportPDF/*/*").permitAll()
                .anyRequest().authenticated()
                .and()
                .addFilterBefore(new JWTLoginFilter("/login", authenticationManager()), UsernamePasswordAuthenticationFilter.class)
                .addFilterBefore(new JWTAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {

             auth.jdbcAuthentication().dataSource(this.manager.getDataSource())
               .usersByUsernameQuery("select username,password,true as enabled from user where username=?")
               .authoritiesByUsernameQuery("select username, role from user where username=?");

    }
}

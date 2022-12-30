package ru.mikhelson.springcourse.SpringApp.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import org.springframework.web.cors.CorsConfiguration;
import ru.mikhelson.springcourse.SpringApp.security.MySimpleUrlAuthenticationSuccessHandler;
import ru.mikhelson.springcourse.SpringApp.services.PersonDetailsService;

@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true) // для проверки, что пользователь имеет доступ к тому или иному методу в контроллере
public class SecurityConfig extends WebSecurityConfigurerAdapter
{
    private final PersonDetailsService personDetailsService;

    @Autowired
    public SecurityConfig(PersonDetailsService personDetailsService)
    {
        this.personDetailsService = personDetailsService;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception
    {
        // конфигурируем сам Spring Security
        // конфигурируем авторизацию
        http.cors().and()
                .csrf().disable()
                .authorizeRequests()
                .antMatchers("/employee", "client").access("hasRole('ROLE_ADMIN')") // страницы, которые доступны только админу
                .antMatchers("client").access("hasRole('ROLE_USER')") // страницы, которые доступны только пользователю
                .antMatchers("/auth/login").permitAll() // то куда допускается любой пользователь без авторизации
                .anyRequest().authenticated()
                .and()
                .formLogin().loginPage("/auth/login")
                .loginProcessingUrl("/process_login") // в process_login отправляем полученные данные
                .successHandler(myAuthenticationSuccessHandler()) // в зависимости от роли пользователя переход на соответствущую страницу
                .failureUrl( "/auth/login?error=true") // в случае провала отправляемся на страницу авторизации
                .and()
                .logout().logoutUrl("/logout").logoutSuccessUrl("/auth/login"); // разлогинивание
    }

    // Настраиваем аутентификацию
    protected void configure(AuthenticationManagerBuilder auth)
    {
        try {
            auth.userDetailsService(personDetailsService);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    // для шифрования пароля
    @Bean
    public PasswordEncoder getPasswordEncoder()
    {
        return NoOpPasswordEncoder.getInstance();
    }

    @Bean
    CorsConfigurationSource corsConfigurationSource()
    {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", new CorsConfiguration().applyPermitDefaultValues());
        return source;
    }

    @Bean
    public AuthenticationSuccessHandler myAuthenticationSuccessHandler(){
        return new MySimpleUrlAuthenticationSuccessHandler();
    }
}

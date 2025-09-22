package login;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import lombok.RequiredArgsConstructor;

@Configuration
@EnableWebSecurity
public class SecurityContext {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            // CSRF 보호 비활성화 (배포 초기 단계에서 테스트를 위해)
            .csrf(csrf -> csrf.disable())
            // 모든 요청에 대한 접근을 허용하도록 설정 (가장 중요한 변경 부분)
            .authorizeHttpRequests(authz -> authz
                .antMatchers("/**").permitAll() // 모든 경로(/**)를 허용
                .anyRequest().authenticated()
            );

        return http.build();
    }
}
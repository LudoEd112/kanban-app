package hacaton.securityConfig;

import hacaton.controller.JwtTokenProvider;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @Autowired
    private UserDetailsService userDetailsService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        try {
            // Получаем JWT токен из запроса
            String token = jwtTokenProvider.getTokenFromRequest(request);

            // Проверяем валидность токена
            if (token != null && jwtTokenProvider.validateToken(token)) {
                // Извлекаем username из токена
                String username = jwtTokenProvider.getUsernameFromToken(token);

                // Загружаем пользователя по username
                UserDetails userDetails = userDetailsService.loadUserByUsername(username);

                // Проверяем, что пользователь не null и контекст безопасности пустой
                if (userDetails != null && SecurityContextHolder.getContext().getAuthentication() == null) {
                    // Получаем объект аутентификации на основе токена
                    var authentication = jwtTokenProvider.getAuthentication(token, userDetails);

                    // Устанавливаем детали аутентификации (WebAuthenticationDetailsSource)
                    UsernamePasswordAuthenticationToken authWithDetails = new UsernamePasswordAuthenticationToken(
                            authentication.getPrincipal(),
                            authentication.getCredentials(),
                            authentication.getAuthorities()
                    );
                    authWithDetails.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

                    // Устанавливаем аутентификацию в контексте безопасности
                    SecurityContextHolder.getContext().setAuthentication(authWithDetails);
                }

            }
        } catch (Exception ex) {
            // Логирование ошибок при необходимости
            System.err.println("Не удалось установить аутентификацию пользователя: " + ex.getMessage());
        }

        // Продолжаем цепочку фильтров
        filterChain.doFilter(request, response);
    }
}

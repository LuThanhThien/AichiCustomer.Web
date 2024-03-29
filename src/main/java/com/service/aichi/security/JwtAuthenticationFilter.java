    package com.service.aichi.security;

    import com.service.aichi.entity.Token;
    import com.service.aichi.entity.User;
    import com.service.aichi.repository.interfaces.ITokenRepository;
    import com.service.aichi.service.UserService;
    import jakarta.servlet.FilterChain;
    import jakarta.servlet.ServletException;
    import jakarta.servlet.http.HttpServletRequest;
    import jakarta.servlet.http.HttpServletResponse;
    import lombok.AllArgsConstructor;
    import lombok.NoArgsConstructor;
    import lombok.NonNull;
    import lombok.RequiredArgsConstructor;
    import lombok.extern.slf4j.Slf4j;
    import org.springframework.beans.factory.annotation.Autowired;
    import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
    import org.springframework.security.core.context.SecurityContextHolder;
    import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
    import org.springframework.stereotype.Component;
    import org.springframework.web.filter.OncePerRequestFilter;

    import java.io.IOException;

    @Component
    @RequiredArgsConstructor
    @Slf4j
    public class JwtAuthenticationFilter extends OncePerRequestFilter {

        private final JwtService jwtService;

        private final UserService userService;

        private final ITokenRepository tokenRepository;

        @Override
        protected void doFilterInternal(
                @NonNull HttpServletRequest request,
                @NonNull HttpServletResponse response,
                @NonNull FilterChain filterChain
        ) throws ServletException, IOException {
            if (request.getServletPath().contains("/api/v1/auth")) {
                // ignore the endpoint "api/v1/auth"
                filterChain.doFilter(request, response);
                return;
            }

            final String jwt = getJwtFromRequest(request);
            final String username;

            if (jwt == null) {
                filterChain.doFilter(request, response);
                return;
            }

            username = jwtService.extractUsername(jwt);

            if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
                User user = this.userService.loadUserByUsername(username);
                var isTokenValid = tokenRepository.findByToken(jwt)
                        .map(t -> !t.isExpired() && !t.isRevoked())
                        .orElse(false);
                if (jwtService.isValidToken(jwt, user) && isTokenValid) {
                    UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                            user,
                            null,
                            user.getAuthorities()
                    );
                    authToken.setDetails(
                            new WebAuthenticationDetailsSource().buildDetails(request)
                    );
                    SecurityContextHolder.getContext().setAuthentication(authToken);
                };
            }

            filterChain.doFilter(request, response);
        }


        private String getJwtFromRequest(HttpServletRequest request) {
            String bearerToken = request.getHeader("Authorization");
            if (bearerToken == null || !bearerToken.startsWith("Bearer ")) {
                return null;
            }
            return bearerToken.substring(7);
        }
    }

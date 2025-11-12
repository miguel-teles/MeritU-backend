package io.meritu.meritubackend.security;

import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.google.gson.Gson;
import io.meritu.meritubackend.domain.dto.ErrorResponseDTO;
import io.meritu.meritubackend.repo.UserRepository;
import io.meritu.meritubackend.service.login.TokenService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class SecurityFilter extends OncePerRequestFilter {

    private final TokenService tokenService;
    private final UserRepository userRepository;
    private final Gson gson;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String token = pegaTokenDaRequisicao(request);
        if (token != null) {
            try {
                validaToken(token);
            } catch (TokenExpiredException ex) {
                trataErroTokenExpirado(response);
                return;
            } catch (JWTVerificationException ex) {
                trataErroTokenInvalido(response, ex);
            }
        }
        filterChain.doFilter(request, response);
    }

    private void validaToken(String token) {
        String user = tokenService.validarToken(token);
        UserDetails userDetails = userRepository.findByUsername(user);
        UsernamePasswordAuthenticationToken userPasswordAuthorization = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(userPasswordAuthorization);
    }

    private void trataErroTokenExpirado(HttpServletResponse response) throws IOException {
        response.setStatus(HttpStatus.UNAUTHORIZED.value());
        response.setContentType("application/json");
        response.getWriter().write(gson.toJson(new ErrorResponseDTO(HttpStatus.UNAUTHORIZED.value(), "Login expirado, favor logar novamente")));
    }

    private void trataErroTokenInvalido(HttpServletResponse response, JWTVerificationException ex) throws IOException {
        response.setStatus(HttpStatus.UNAUTHORIZED.value());
        response.setContentType("application/json");
        response.getWriter().write(gson.toJson(new ErrorResponseDTO(HttpStatus.UNAUTHORIZED.value(), ex.getMessage())));
    }

    private String pegaTokenDaRequisicao(HttpServletRequest request) {
        String authorization = request.getHeader("Authorization");
        if (authorization != null && authorization.startsWith("Bearer ")) {
            return authorization.replace("Bearer ", "");
        }
        return null;
    }

}
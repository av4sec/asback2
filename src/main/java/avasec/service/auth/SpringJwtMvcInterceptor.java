package avasec.service.auth;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureException;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by martinlinha on 30.04.17.
 */
public class SpringJwtMvcInterceptor extends HandlerInterceptorAdapter {

    private static final String AUTHENTICATION_BEARER = "Bearer ";
    private static final String AUTHENTICATION_HEADER = "Authorization";

    private String secretkey;

    public SpringJwtMvcInterceptor(String secretkey) {
        this.secretkey = secretkey;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (handler instanceof HandlerMethod) {
            HandlerMethod method = (HandlerMethod) handler;
            if (method.hasMethodAnnotation(JwtSecured.class)) {
                String authorizationHeader = request.getHeader(AUTHENTICATION_HEADER);
                if (authorizationHeader == null || !authorizationHeader.startsWith(AUTHENTICATION_BEARER)) {
                    response.sendError(HttpServletResponse.SC_UNAUTHORIZED);
                    return false;
                } else {
                    try {
                        String authenticationHeaderBearerless = authorizationHeader.
                                substring(AUTHENTICATION_BEARER.length(), authorizationHeader.length());
                        Jwts.parser()
                                .setSigningKey(secretkey)
                                .parseClaimsJws(authenticationHeaderBearerless).getBody();
                    } catch (MalformedJwtException | SignatureException | ExpiredJwtException e) {
                        response.sendError(HttpServletResponse.SC_UNAUTHORIZED);
                        return false;
                    }
                }
            }
        }
        return true;
    }
}

package services;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class TokenAuthenticationService {
    static final long EXPIRATIONTIME = 1_200_000; // 10 minutes
    static final String SECRET = "ThisIsASecret";
    static final String TOKEN_PREFIX = "Bearer";
    static final String HEADER_STRING = "Authorization";
    static Map<String,Authentication> authentication = new HashMap<String, Authentication>();
    public static void addAuthentication(HttpServletResponse res, Authentication authen) {
        authentication.put(authen.getName(),authen);//temp
        String JWT = Jwts.builder()
                .setSubject(authen.getName())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATIONTIME))
                .signWith(SignatureAlgorithm.HS512, SECRET)
                .compact();
        //res.addHeader(HEADER_STRING, TOKEN_PREFIX + " " + JWT);
        try {
            res.getWriter().write(TOKEN_PREFIX + " " + JWT);
            res.getWriter().flush();
            res.getWriter().close();
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    public static Authentication getAuthentication(HttpServletRequest request) {
        String token = request.getHeader(HEADER_STRING);
        if (token != null) {
            try {
                // parse the token.
                String user = Jwts.parser()
                        .setSigningKey(SECRET)
                        .parseClaimsJws(token.replace(TOKEN_PREFIX, ""))
                        .getBody()
                        .getSubject();
                return user != null ?
                        new UsernamePasswordAuthenticationToken(user, null, authentication.get(user).getAuthorities()) :
                        null;
            }
            catch (SignatureException e){
                return null;
            }
        }
        return null;
    }
}
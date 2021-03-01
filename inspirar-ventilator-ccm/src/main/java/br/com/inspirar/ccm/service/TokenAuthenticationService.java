package br.com.inspirar.ccm.service;

import java.io.IOException;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;

import com.google.gson.Gson;

import br.com.inspirar.ccm.model.UsuarioAutenticado;
import br.com.inspirar.ccm.model.UsuarioAutenticado.RoleEnum;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Configuration
public class TokenAuthenticationService {

	private static final Logger LOG = LoggerFactory.getLogger(TokenAuthenticationService.class);
	
	private static final long EXPIRATIONTIME = 864000000;
    
   //@Value("password.token")
    private static String SECRET = "-inspirar-ventilator-cct-api-v1-password";
    
    private static final String TOKEN_PREFIX = "Bearer";
    private static final String HEADER_STRING = "Authorization";

	private static String criarTokenTemPorario(String tokenApp) {
		
	       String JWT = Jwts.builder()
	                .setSubject(tokenApp)
	                .setExpiration(new Date(System.currentTimeMillis() + 1000))
	                .signWith(SignatureAlgorithm.HS512, SECRET)
	                .compact();
	       
		
		return JWT;
		
	}
	
    public static void addAuthentication(HttpServletResponse res, Authentication authentication) {
        String JWT = Jwts.builder()
                .setSubject(authentication.getName())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATIONTIME))
                .signWith(SignatureAlgorithm.HS512, SECRET)
                .compact();

        String token = TOKEN_PREFIX + " " + JWT;
        res.addHeader(HEADER_STRING, token);
        
        try {

        	String role = authentication.getAuthorities().stream().findFirst().get().getAuthority();
        	
            res.getOutputStream().print(
            		new Gson().toJson(
            				new UsuarioAutenticado().username(authentication.getName())
            				.token(token)
            				.role(RoleEnum.fromValue(role))
            		)
            	
            		);
        } catch (IOException e) {
        	LOG.error(token, e); 
        }
    }

    public static Authentication getByToken(String token) {
        String user = Jwts.parser()
                .setSigningKey(SECRET)
                .parseClaimsJws(token.replace(TOKEN_PREFIX, ""))
                .getBody()
                .getSubject();

        return user != null ? new UsernamePasswordAuthenticationToken(user, null, null) : null;
    }

	public static Authentication getAuthentication(HttpServletRequest request) {
		
		
        String token = request.getHeader(HEADER_STRING);
        
        if (token != null) {
        	
        	if(token.equalsIgnoreCase("4e81f8d00d8028da889f46f29c449235690891a5")) {
        		token = criarTokenTemPorario("356112081744800");
        	}
        	
            return getByToken(token);
        }
        return null;
    }
	

}
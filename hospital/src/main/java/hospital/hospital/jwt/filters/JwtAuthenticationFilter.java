package hospital.hospital.jwt.filters;

import hospital.hospital.jwt.services.CustomUserDetailsService;
import hospital.hospital.jwt.utils.JwtUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
@Slf4j
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    @Autowired
    private CustomUserDetailsService userDetailsService;

    @Autowired
    private JwtUtil jwtUtil;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        //get jwtToken request header
        //validate jwt token
        String bearerToken = request.getHeader("Authorization");
        String username = null;
        String token = null;
        //check if token exists or has bearer text
        if(bearerToken != null && bearerToken.startsWith("Bearer ")){
            //extract jwt token from bearer token
            token = bearerToken.substring(7);
            try{
                //extract username from that token
                username = jwtUtil.extractUsername(token);
                //get userdetails from this user
                UserDetails userDetails = userDetailsService.loadUserByUsername(username);
                //security checks
                if(username!=null && SecurityContextHolder.getContext().getAuthentication() == null){
                    UsernamePasswordAuthenticationToken upat = new UsernamePasswordAuthenticationToken(userDetails,null,userDetails.getAuthorities());
                    upat.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                    SecurityContextHolder.getContext().setAuthentication(upat);
                }
                else {
                    log.info("Invalid token!");
                }

            }catch (Exception ex){
                log.info("Invalid token!");
            }
        }else{
            log.info("Invalid bearer token");
        }
        //if all is well forward request to the requested endpoint
        filterChain.doFilter(request,response);
    }
}

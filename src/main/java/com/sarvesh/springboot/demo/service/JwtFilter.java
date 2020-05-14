package com.sarvesh.springboot.demo.service;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.sarvesh.springboot.demo.service.CustomUserDetailsService;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class JwtFilter extends OncePerRequestFilter {

    @Autowired
    private JwtUtil jwtUtil;
    @Autowired
    private CustomUserDetailsService service;


    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {

        String authorizationHeader = httpServletRequest.getHeader("Authorization");
        httpServletResponse.setHeader("Access-Control-Allow-Origin", "*");

        String token = null;
        String userName = null;

        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            token = authorizationHeader.substring(7);
            userName = jwtUtil.extractUsername(token);
        }

        if (userName != null && SecurityContextHolder.getContext().getAuthentication() == null) {

            UserDetails userDetails = service.loadUserByUsername(userName);

            if (jwtUtil.validateToken(token, userDetails)) {

                UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
                        new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                usernamePasswordAuthenticationToken
                        .setDetails(new WebAuthenticationDetailsSource().buildDetails(httpServletRequest));
                SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
            }
        }
        
        if (!(httpServletRequest.getMethod().equalsIgnoreCase("OPTIONS"))) {
            try {
                filterChain.doFilter(httpServletRequest, httpServletResponse);
            } catch(Exception e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("Pre-flight");
            httpServletResponse.setHeader("Access-Control-Allow-Origin", "*");
            httpServletResponse.setHeader("Access-Control-Allow-Methods", "POST,GET,DELETE,PUT");
            httpServletResponse.setHeader("Access-Control-Max-Age", "3600");
            httpServletResponse.setHeader("Access-Control-Allow-Headers", "Access-Control-Expose-Headers"+"Authorization, content-type," +
            "USERID"+"ROLE"+
                    "access-control-request-headers,access-control-request-method,accept,origin,authorization,x-requested-with,responseType,observe");
            httpServletResponse.setStatus(HttpServletResponse.SC_OK);
        }
        
        
        
        
//        {{short description|Indian actor}}
//        {{Use dmy dates|date=March 2020}}
//        {{Use Indian English|date=April 2014}}
//        {{Infobox person
//        | name               = Karan Bendre
//        | occupation         = Actor
//        | yearsactive        = 2019 – present
//        | known_for          = Alap in ''[[Prem Poison Panga]]'' Marathi Serial
//        }}
//
//        '''Karan Bendre''' <ref>https://www.imdb.com/name/nm11072905/</ref> is an [[India]]n [[actor]]. He is famous for appearing in the  role as Alap in ''[[Prem Poison Panga]]'' .
//
//
//        ==Television==
//        {| class="wikitable"
//        |-
//        ! Year!! Title !! Character !! Ref
//        |-
//        |2015
//        |''[[Prem Poison Panga]]''|| Alap || <ref>https://www.zee5.com/tvshows/details/prem-poison-panga/0-6-2125</ref>
//        |-

        
        
        
        
        
        
        
        
    }
}
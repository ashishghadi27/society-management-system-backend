package com.root.sms.auth_service.helpers;

import com.root.sms.auth_service.config.ConsulConfig;
import com.root.sms.auth_service.constants.ExceptionConstants;
import com.root.sms.auth_service.exception.GlobalException;
import com.root.sms.auth_service.vo.MemberVO;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CookieHelper {
    private final JWTHelper jwtHelper;
    private final ConsulConfig config;

    private HttpServletRequest httpServletRequest;

    private HttpServletResponse httpServletResponse;

    @Autowired
    public CookieHelper(JWTHelper jwtHelper,
                        ConsulConfig config,
                        HttpServletRequest httpServletRequest,
                        HttpServletResponse httpServletResponse) {
        this.jwtHelper = jwtHelper;
        this.config = config;
        this.httpServletRequest = httpServletRequest;
        this.httpServletResponse = httpServletResponse;
    }

    public void setCookie(MemberVO memberVO) throws GlobalException {

        int cookieTimeout = config.getConfigValueByKey("COOKIE_TIMEOUT", 1080);
        Cookie sessionCookie = null;
        Cookie[] cookies = httpServletRequest.getCookies();
        for(Cookie cookie : cookies){
            if("session-id".equals(cookie.getName())){
                sessionCookie = cookie;
            }
        }

        if(sessionCookie == null){
            throw new GlobalException.Builder()
                    .errorMessage(ExceptionConstants.INVALID_REQUEST.name())
                    .description(ExceptionConstants.INVALID_REQUEST.description).build();
        }

        httpServletResponse.addCookie(getSessionCookie(sessionCookie, cookieTimeout));
        httpServletResponse.addCookie(getJwtCookie(memberVO, cookieTimeout));
    }

    public void setCookie() throws GlobalException {

        int cookieTimeout = config.getConfigValueByKey("COOKIE_TIMEOUT", 1080);
        Cookie sessionCookie = null;
        Cookie[] cookies = httpServletRequest.getCookies();
        for(Cookie cookie : cookies){
            if("session-id".equals(cookie.getName())){
                sessionCookie = cookie;
            }
        }

        if(sessionCookie == null){
            throw new GlobalException.Builder()
                    .errorMessage(ExceptionConstants.INVALID_REQUEST.name())
                    .description(ExceptionConstants.INVALID_REQUEST.description).build();
        }

        httpServletResponse.addCookie(getSessionCookie(sessionCookie, cookieTimeout));
    }

    private Cookie getSessionCookie(Cookie sessionCookie, int cookieTimeout){
        sessionCookie.setMaxAge(cookieTimeout);
        //sessionCookie.setSecure(true);
        sessionCookie.setHttpOnly(true);
        sessionCookie.setDomain("localhost");
        sessionCookie.setPath("/");
        return sessionCookie;
    }
    private Cookie getJwtCookie(MemberVO memberVO, int cookieTimeout){
        Cookie jwtTokenCookie = new Cookie("auth", jwtHelper.getJwtToken(memberVO));
        jwtTokenCookie.setMaxAge(cookieTimeout);
        //jwtTokenCookie.setSecure(true);
        jwtTokenCookie.setHttpOnly(true);
        jwtTokenCookie.setDomain("localhost");
        jwtTokenCookie.setPath("/");
        return jwtTokenCookie;
    }
}

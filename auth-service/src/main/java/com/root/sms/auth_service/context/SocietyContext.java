package com.root.sms.auth_service.context;

import com.root.redis.context.RedisSessionContext;
import com.root.sms.auth_service.vo.MemberVO;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SocietyContext extends RedisSessionContext {

    private MemberVO memberVO;
    private String otp;
    private String otpSentTime;

    @Override
    public String getContextIdentifier() {
        return "SOCIETY";
    }

    @Override
    public Integer sessionExpiryTime() {
        return null;
    }
}

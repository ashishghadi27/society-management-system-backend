package com.root.sms.auth_service.utils;

import com.root.sms.auth_service.entity.MemberEntity;
import com.root.sms.auth_service.vo.MemberVO;

public class MapperUtil {

    public static MemberVO getMemberVO(MemberEntity memberEntity){
        MemberVO memberVO = new MemberVO();
        memberVO.setFirstName(memberEntity.getFirstName());
        memberVO.setLastName(memberEntity.getLastName());
        memberVO.setEmail(memberEntity.getEmail());
        memberVO.setHashedPassword(memberEntity.getHashedPassword());
        memberVO.setType(memberEntity.getType());
        memberVO.setRoomId(memberEntity.getRoomId());
        return memberVO;
    }

    public static MemberEntity getMemberEntity(MemberVO memberVO){
        MemberEntity memberEntity = new MemberEntity();
        memberEntity.setFirstName(memberVO.getFirstName());
        memberEntity.setLastName(memberVO.getLastName());
        memberEntity.setEmail(memberVO.getEmail());
        memberEntity.setHashedPassword(memberVO.getHashedPassword());
        memberEntity.setType(memberVO.getType());
        memberEntity.setRoomId(memberVO.getRoomId());
        return memberEntity;
    }

}

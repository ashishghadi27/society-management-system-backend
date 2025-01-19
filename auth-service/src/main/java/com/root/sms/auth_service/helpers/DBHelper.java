package com.root.sms.auth_service.helpers;

import com.root.sms.auth_service.constants.ExceptionConstants;
import com.root.sms.auth_service.entity.MemberEntity;
import com.root.sms.auth_service.exception.GlobalException;
import com.root.sms.auth_service.repo.MembersRepository;
import com.root.sms.auth_service.utils.MapperUtil;
import com.root.sms.auth_service.vo.MemberVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@Slf4j
@RequiredArgsConstructor
public class DBHelper {

    private final MembersRepository membersRepository;
    public MemberVO getMemberByEmail(String emailId) throws GlobalException {
        Optional<MemberEntity> memberEntityOptional = membersRepository.findByEmail(emailId);
        return memberEntityOptional.map(MapperUtil::getMemberVO).orElseThrow(() -> new GlobalException.Builder()
                .errorMessage(ExceptionConstants.DATA_NOT_FOUND.name())
                .description(ExceptionConstants.DATA_NOT_FOUND.description).build());
    }

    public Long registerMember(MemberVO requestVO) throws GlobalException {
        try{
            MemberEntity entity = MapperUtil.getMemberEntity(requestVO);
            return membersRepository.save(entity).getId();
        }
        catch (Exception e){
            throw new GlobalException.Builder()
                    .errorMessage(ExceptionConstants.INTERNAL_ERROR.name())
                    .description(ExceptionConstants.INTERNAL_ERROR.description).build();
        }
    }
}

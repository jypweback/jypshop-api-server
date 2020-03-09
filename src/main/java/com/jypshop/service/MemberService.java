package com.jypshop.service;

import com.jypshop.domain.Member;
import com.jypshop.dto.MemberDto;
import com.jypshop.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by qkrwpdud1@gmail.com on 2020/03/08
 * Github : http://github.com/jypweback
 * Description :
 */

@RequiredArgsConstructor
@Service
public class MemberService {

    private final MemberRepository memberRepository;

    @Transactional
    public MemberDto createMember(MemberDto memberDto){

        Member member = memberRepository.save(memberDto.toEntity());
        return convertEntityToDto(member);
    }

    @Transactional
    public MemberDto updateMember(Long id, MemberDto dto){

        Member member = getMemberEntity(id);
        member.update(dto.toEntity());

        return convertEntityToDto(member);
    }

    @Transactional(readOnly = true)
    public MemberDto getMember(Long id){

        Member member = getMemberEntity(id);
        return convertEntityToDto(member);
    }

    @Transactional
    public void removeMember(Long id){
        Member member = getMemberEntity(id);
        memberRepository.delete(member);
    }

    private Member getMemberEntity(Long id){

        return memberRepository
                .findById(id)
                .orElseThrow(() -> new IllegalArgumentException(id + " 회원이 존재하지 않습니다."));
    }

    /**
     * member 엔티티를 dto로 변환한다.
     * @param member
     * @return
     */
    private MemberDto convertEntityToDto(Member member){

        return MemberDto
                .builder()
                .memberId(member.getId())
                .name(member.getName())
                .city(member.getAddress() != null ? member.getAddress().getCity() : null)
                .street(member.getAddress() != null ? member.getAddress().getStreet() : null)
                .zipcode(member.getAddress() != null ? member.getAddress().getZipcode() : null)
                .build();
    }

}

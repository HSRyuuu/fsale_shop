package com.hsryuuu.flashsale;

import com.hsryuuu.flashsale.member.Member;
import com.hsryuuu.flashsale.member.MemberRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Tag(name = "테스트")
@RequiredArgsConstructor
@RestController("/api/test")
public class TestController {

    private final MemberRepository memberRepository;
 
    @Operation(summary = "TEST")
    @GetMapping("/success")
    public List<Member> test() {
        return memberRepository.findAll();
    }

    @Operation(summary = "Exception")
    @GetMapping("/exception")
    public List<Member> exception() {
        throw new IllegalArgumentException("ex" +
                " test");
    }
}

package hello.core.member;

public class MemberServiceImpl implements MemberService {

    private final MemberRepository memberRepository;
    // 인터페이스 뿐만아니라 구현체에 의존하고 있다.
    // 추상 + 구현 모두 의존하고있다


    public MemberServiceImpl(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Override
    public void join(Member member) {
        memberRepository.save(member);
    }

    @Override
    public Member findMember(Long memberId) {
        return memberRepository.findById(memberId);
    }

    public MemberRepository getMemberRepository() {
        return memberRepository;
    }
}

package hello.core.order;

import hello.core.annotation.MainDiscountPolicy;
import hello.core.discount.DiscountPolicy;
import hello.core.member.Member;
import hello.core.member.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
//@RequiredArgsConstructor // final 붙은 애들로 생성자를 만들어준다
public class OrderServiceImpl implements OrderService{

    private final MemberRepository memberRepository;

    private final DiscountPolicy discountPolicy;

    @Autowired
    public OrderServiceImpl(MemberRepository memberRepository, @MainDiscountPolicy DiscountPolicy discountPolicy) {
        this.memberRepository = memberRepository;
        this.discountPolicy = discountPolicy;
    }

    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {
        Member member = memberRepository.findById(memberId); // 회원 정보 조회
        int discountPrice = discountPolicy.discount(member, itemPrice); // 할인 정책에 회원 정보 넘기기 -

        return new Order(memberId, itemName, itemPrice, discountPrice); // 주문 생성
   }

    // 테스트 용도
    public MemberRepository getMemberRepository() {
        return memberRepository;
    }


}

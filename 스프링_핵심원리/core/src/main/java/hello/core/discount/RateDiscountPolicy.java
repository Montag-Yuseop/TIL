package hello.core.discount;

import hello.core.member.Grade;
import hello.core.member.Member;
import org.springframework.stereotype.Component;

@Component
public class RateDiscountPolicy implements DiscountPolicy {

    @Override
    public int discount(Member member, int price) {
        int discountPercent = 10;
        if(member.getGrade() == Grade.VIP) return price * discountPercent / 100;
        return 0;
    }
}

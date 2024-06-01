package com.example.sample;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

class CalculationRequestTest {

    @Test
    public void 유효한_숫자를_파싱할_수_있다(){
        // given
        String[] parts = new String[]{"2222", "+", "3333"};

        // when
        CalculationRequest calculationRequest = new CalculationRequest(parts);

        // then
        assertThat(calculationRequest.getNum1()).isEqualTo(2222);
        assertThat(calculationRequest.getNum2()).isEqualTo(3333);
        assertThat(calculationRequest.getOperator()).isEqualTo("+");
    }

    @Test
    public void 유효한_길이의_숫자가_들어오지_않으면_에러를_던진다(){
        // given
        String[] parts = new String[]{"22", "+"};

        // when

        // then
        assertThrows(BadRequestException.class, () -> {
            CalculationRequest calculationRequest = new CalculationRequest(parts);
        });
    }

    @Test
    public void 유효하지_않은_연산자가_들어오면_에러를_던진다(){
        // given
        String[] parts = new String[]{"22", "x", "333"};

        // when

        // then
        assertThrows(InvalidOperatorException.class, () -> {
            CalculationRequest calculationRequest = new CalculationRequest(parts);
        });
    }

    @Test
    public void 유효하지_않은_길이의_연산자가_들어오면_에러를_던진다(){
        // given
        String[] parts = new String[]{"22", "+-", "333"};

        // when

        // then
        assertThrows(InvalidOperatorException.class, () -> {
            CalculationRequest calculationRequest = new CalculationRequest(parts);
        });
    }

}

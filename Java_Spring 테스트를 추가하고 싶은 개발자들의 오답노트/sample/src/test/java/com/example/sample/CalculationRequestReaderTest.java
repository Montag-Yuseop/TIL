package com.example.sample;

import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;

import static org.assertj.core.api.Assertions.*;

class CalculationRequestReaderTest {
    
    @Test
    public void System_in으로_데이터를_읽어들일_수_있다() throws Exception {
        // given
        CalculationRequestReader calculationRequestReader = new CalculationRequestReader();

        // when
        System.setIn(new ByteArrayInputStream("2 + 3".getBytes()));
        CalculationRequest result = calculationRequestReader.read();
        
        // then
        assertThat(result.getNum1()).isEqualTo(2);
        assertThat(result.getOperator()).isEqualTo("+");
        assertThat(result.getNum2()).isEqualTo(3);

    }

}

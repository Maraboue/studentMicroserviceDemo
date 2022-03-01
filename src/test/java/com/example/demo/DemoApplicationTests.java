package com.example.demo;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;


@SpringBootTest
class DemoApplicationTests {

    @Test
    void shouldAddNmr(){

        Calc calc = new Calc();
        int add = calc.add(5,5);


        assertThat(add).isEqualTo(10);

    }

    class Calc {

        public Calc(){

        }

        public int add(int a, int b){
            return a+b;
        }

    }
 
}

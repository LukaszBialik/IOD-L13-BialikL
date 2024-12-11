package put.io.testing.junit;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CalculatorTest {

    private Calculator calculator;

    @Test

    public void testAdd(){
        int addition = calculator.add(5,12);
        assert addition == 17;
        System.out.println(addition);
    }

    @Test

    public void testMul(){
        int multi = calculator.multiply(5,12);
        assert multi == 60;
        System.out.println(multi);
    }

    @Test
    public void testAddPositiveNumbers(){
        int addPos = calculator.addPositiveNumbers(5,12);
        assert addPos == 17;
        System.out.println(addPos);
    }

    @Test
    void testExpectedException() {
        assertThrows(IllegalArgumentException.class, () -> {
            calculator.addPositiveNumbers(-5, 12);
        });
    }



    @BeforeEach
    public void Setup(){
        calculator = new Calculator();
    }

}
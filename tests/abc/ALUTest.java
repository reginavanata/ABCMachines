package abc;

import static org.junit.Assert.*;
import org.junit.Test;

/**
 *
 */
public class ALUTest {
    ABCMachine machine = new ABCMachine("programs/program2.abc");
    short num1 = 0b1010;
    short num2 = 0b0011;
    @Test
    public void testOperate() {


        assertEquals(0b1101, machine.getAlu().operate(num1, Operator.ADD, num2));
        assertEquals(0b0111, machine.getAlu().operate(num1, Operator.SUB, num2));
        assertEquals(0b0001_1110, machine.getAlu().operate(num1, Operator.MULT, num2));
        assertEquals(0b0011, machine.getAlu().operate(num1, Operator.DIV, num2));
    }

    @Test
    public void testGetStatus() {
        assertEquals(Nzp.ZERO, machine.getAlu().getStatus());

    }

}
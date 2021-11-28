package abc;

/**
 *This class represents the ALU for the ABC Machine
 * It performs all arithmetic instructions
 * @author Regina Vanata
 * @version 1.0
 */
public class ALU {

    private Nzp status; //holds Nzp.NEGATIVE, Nzp.ZERO, or Nzp.POSITIVE

    /**
     * Initialize the ALU status to Nzp.ZERO
     */
    public ALU() {
        status = Nzp.ZERO;

    }

    /**
     * This method will perform a math operation on two numbers and set the nzp status
     * appropriately based on whether the math operation resulting in a postive, negative, or zero value
     * @param num1 first number
     * @param operator Operation to perform on numbers
     * @param num2 second number
     * @return result after performing operation on numbers
     */
    public short operate(short num1, Operator operator, short num2) {
        short result = 0;
        switch(operator){

            case ADD:
                result = (short)(num1 + num2);
                break;
            case SUB:
                result = (short)(num1 - num2);
                break;

            case DIV:
                result = (short)(num1 / num2);
                break;
            case MULT:
                result = (short)(num1 * num2);
                break;
        }
        // remember to set nzp status
        if(result == 0){
            status = Nzp.ZERO;
        } else {
            status = (result < 0) ? Nzp.NEGATIVE : Nzp.POSITIVE;
        }
        return result;//answer to ADD, SUB, MULT, DIV

    }

    /**
     * an accessor for the getStatus() method
     * @return ALU status
     */
    public Nzp getStatus() {
        return this.status;
    }



}

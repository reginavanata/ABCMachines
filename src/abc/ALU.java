package abc;

public class ALU {

    private Nzp status; //holds Nzp.NEGATIVE, Nzp.ZERO, or Nzp.POSITIVE

    // TODO: Initialize the ALU status to Nzp.ZERO
    public ALU() {
        status = Nzp.ZERO;

    }

    //TODO: This method will perform a math operation on two numbers and set the nzp status
    // appropriately based on whether the math operation resulting in a postive, negative, or zero value

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

                break;
            case MULT:

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

    // TODO: Write an accessor for the getStatus() method
    public Nzp getStatus() {
        return this.status;
    }



}

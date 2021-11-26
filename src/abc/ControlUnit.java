package abc;

/**
 * This class represents the Control Unit for the
 * ABC Machine
 *
 */
public class ControlUnit {

    private ABCMachine machine;

    /**
     * @param machine The ABC machine this ControlUnit belongs to
     */
    public ControlUnit(ABCMachine machine) {
        this.machine = machine;
    }

    /**
     * This method will continuously fetch, decode, execute, and store instructions/data that are
     * loaded into the ABCMachine's memory map. The program "halts" when it reaches an instruction
     * that is zero.
     */
    public void startProcessing() {

        while (!halt()) {
            fetch();
            decodeExecuteStore();
        }

    }

    /**
     * This method performs the fetch step for the ControlUnit
     *1. Access the memory address for the next instruction in RAM(memory array) (use the PC register)
     *  2. Load this instruction from memory into the Instruction Register  (IR)
     *  3. Increment the Program Counter (PC) register
     */
    public void fetch() {
        // get the value in the pc register
        byte pc = machine.getPc();

        // retrieve from the memory array the instructions at the pc index
        short[] mem = machine.getMemory();

        // retrieve instruction from the memory array at pc index
        short instruction = mem[pc];

        // place instruction into instruction register
        machine.setIr(instruction);

        // increment pc value and set it into the pc register
        pc++;
        machine.setPc(pc);

    }

    /**
     * This method decodes and executes the instruction according to the
     * ABCMachine Instruction Set and stores the result. You may want to create private
     * helper methods for use by this method.
     */
    public void decodeExecuteStore() {
        // IR - 101 000 000 000 1110
        // get opcode (how?)
        short ir = machine.getIr();

        // mask constant for opcodes
        // 111 000 000 000 0000
        short condition = (short)(ir & 0b111_000_000_000_0000);
        // we need registers array
        short[] reg = machine.getRegisters();
        short src1 = isolateThreeBitsIr(10);
        short src2 = isolateThreeBitsIr(7);
        short dest = isolateThreeBitsIr(4);
        short opCode = isolateThreeBitsIr(13);

        // make decision
        switch(condition){
            case 0: //ADD
                //do add stuff
                // we need src1
                // shift ir value
                //reg[dest];
                ir = (short)(machine.getIr() >>> 10);
                src1 = (short)(ir & 0b111);

                // get src2 register
                ir = (short)(machine.getIr() >>> 7);
                src2 = (short)(ir & 0b111);

                // get dest register
                ir = (short)(machine.getIr() >>> 4);
                dest = (short)(ir & 0b111);

                // add values in src1 and src2 registers
                // pace sum in dest register
                // make use of ALU
                reg[dest] = machine.getAlu().operate(reg[src1], Operator.ADD, reg[src2]);
                break;
            case (short)(0b101_000_000_000_0000): //LOAD
                //do load stuff
                // we need address - last 4 bits right to left
                short address = (short)(ir & 0b1111);
                // we need src1
                // shift ir value
                ir = (short)(machine.getIr() >>> 10);
                src1 = (short)(ir & 0b111);

                // we need memory array
                short[] mem = machine.getMemory();

                //another to do reg[src1] = mem[addr]
                reg[src1] = mem[address];
                break;
        }

    }

    private short isolateThreeBitsIr(int shift){
        short ir = (short) (machine.getIr() >>> shift);
        return (short) (ir & 0b111);
    }

    private short isolateAddressIR() {
        return (short) (machine.getIr() & 0b1111);
    }

    //Branch program if and ALU status match






    /**
     *
     * @return true if next instruction contains all zeros, otherwise false
     */
    public boolean halt() {
        return machine.getMemory()[machine.getPc()] == 0;
    }
}

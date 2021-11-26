package abc;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * This class represents the small ABC Machine. The simple architecture
 * has a control unit and alu, as well as NUM_REGISTERS(8) registers. Memory addresses
 * use 4 bits for a total of NUM_MEMORY(16) memory locations and each word size is
 * 16 bits.
 *
 * @author Josh Archer, Susan Uland
 * @version 1.1
 */
public class ABCMachine {

    //architecture attributes
    public static final int NUM_REGISTERS = 8;
    public static final int NUM_MEMORY = 16;


    //memory areas
    private byte pc = 0; //declare byte since only 4 bits needed
    private short ir = 0; //declare short since 16 bits for an instruction
    private short[] registers = new short[NUM_REGISTERS]; //8 registers
    private short[] memory = new short[NUM_MEMORY]; //16 memory locations

    //components
    private ALU alu;
    private ControlUnit controlUnit;

    /**
     * Creates a new ABC machine with a memory map that contains
     * instructions in the lower memory addresses and data in
     * the higher memory addresses.
     *
     * @param programFile a program with ABC machine instructions
     */
    public ABCMachine(String programFile) {
        //components of the machine
        controlUnit = new ControlUnit(this);
        alu = new ALU();

        //load the program instructions and data into the memory
        //of the ABC machine
        List<String> fileContents = readFile(programFile);
        loadMemory(fileContents);
    }

    //reads the machine instructions from the file specified
    private List<String> readFile(String programFile) {

        try (Stream<String> lineStream = Files.lines(Paths.get(programFile))) {

            return lineStream
                    .collect(Collectors.toList());


        } catch (IOException e) {
            System.err.println("Error reading from file: " + e.getMessage());
        }

        return new ArrayList<>();
    }

    //loads each word from the file into memory
    private void loadMemory(List<String> fileContents) {
        for (int i = 0; i < fileContents.size(); i++) {
            //convert string to integer
            memory[i] = (short) Integer.parseInt(fileContents.get(i), 2);
        }
    }

    /**
     * Passes control of the program to the control unit to fetch, decode
     * execute and store instructions.
     */
    public void runProgram() {
        controlUnit.startProcessing();
    }

    /**
     * Prints out the state of the registers of the ABC machine.
     */
    public void printRegisters() {
        for (short register : registers) {
            String binaryString = Integer.toBinaryString(Short.toUnsignedInt(register));
            binaryString = padZeros(binaryString);
            System.out.println(binaryString);
        }
    }

    /**
     * Prints out the memory of the ABC machine.
     */
    public void printMemory() {
        for (short register : memory) {
            String binaryString = Integer.toBinaryString(Short.toUnsignedInt(register));
            binaryString = padZeros(binaryString);
            System.out.println(binaryString);
        }
    }

    //this method ensures that each word printed has all bits accounted for
    private String padZeros(String binaryString) {
        StringBuilder binaryStringBuilder = new StringBuilder(binaryString);
        while (binaryStringBuilder.length() < NUM_MEMORY) {
            binaryStringBuilder.insert(0, "0");
        }
        binaryString = binaryStringBuilder.toString();
        return binaryString;
    }

    /**
     * Retrieves the ALU for the ABC machine.
     *
     * @return the arithmetic logic unit
     */
    public ALU getAlu() {
        return alu;
    }

    /**
     * Returns the registers of the ABC machine.
     *
     * @return an array of 8 registers
     */
    public short[] getRegisters() {
        return registers;
    }

    /**
     * Returns the memory of the ABC machine.
     *
     * @return an array of 16 memory locations
     */
    public short[] getMemory() {
        return memory;
    }

    /**
     * Retrieves the program counter of the ABC machine.
     *
     * @return the address of the next instruction to execute.
     */
    public byte getPc() {
        return pc;
    }

    /**
     * Alters the program counter of the ABC machine.
     *
     * @param pc a new address that contains an instruction to
     *           execute next
     */
    public void setPc(byte pc) {
        this.pc = pc;
    }

    /**
     * Returns the instruction register for the ABC machine. This
     * register holds the current instruction being decoded/executed.
     *
     * @return the current instruction being used by the machine
     */
    public short getIr() {
        return ir;
    }

    /**
     * Loads a new instruction into the instruction register.
     *
     * @param ir the next instruction to use
     */
    public void setIr(short ir) {
        this.ir = ir;
    }
}

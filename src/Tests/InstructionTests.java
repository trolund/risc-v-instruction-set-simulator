import Core.ISASimulator;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class InstructionTests {

    private final TestUtil test = new TestUtil();

    @Test
    public void add() throws IOException {
        assertTrue(test.runTestWithName(6, "test_add"));
    }

    @Test
    public void addi() throws IOException {
        assertTrue(test.runTestWithName(6, "test_addi"));
    }

    @Test
    public void and() throws IOException {
        assertTrue(test.runTestWithName(6, "test_and"));
    }

    @Test
    public void andi() throws IOException {
        assertTrue(test.runTestWithName(6, "test_andi"));
    }

    @Test
    public void auipc() throws IOException {
        assertTrue(test.runTestWithName(6, "test_auipc"));
    }

    @Test
    public void beq() throws IOException {
        assertTrue(test.runTestWithName(6, "test_beq"));
    }

    @Test
    public void bge() throws IOException {
        assertTrue(test.runTestWithName(6, "test_bge"));
    }

    @Test
    public void bgeu() throws IOException {
        assertTrue(test.runTestWithName(6, "test_bgeu"));
    }

    @Test
    public void blt() throws IOException {
        assertTrue(test.runTestWithName(6, "test_blt"));
    }

    @Test
    public void bltu() throws IOException {
        assertTrue(test.runTestWithName(6, "test_bltu"));
    }

    @Test
    public void bne() throws IOException {
        assertTrue(test.runTestWithName(6, "test_bne"));
    }

/*    @Test
    public void div() throws IOException {
        assertTrue(test.runTestWithName(6, "test_div"));
    }*/

/*    @Test
    public void divu() throws IOException {
        assertTrue(test.runTestWithName(6, "test_divu"));
    }*/

    @Test
    public void ecall() throws IOException {
        ISASimulator vm = new ISASimulator("test_sim", true, true, false, 10, 11, false);
        assertTrue(test.runTestWithName(6, "test_ecall", vm));
    }

    @Test
    public void jal() throws IOException {
        assertTrue(test.runTestWithName(6, "test_jal"));
    }

    @Test
    public void jalr() throws IOException {
        assertTrue(test.runTestWithName(6, "test_jalr"));
    }

    @Test
    public void lb() throws IOException {
        assertTrue(test.runTestWithName(6, "test_lb"));
    }

    @Test
    public void lbu() throws IOException {
        assertTrue(test.runTestWithName(6, "test_lbu"));
    }

    @Test
    public void lh() throws IOException {
        assertTrue(test.runTestWithName(6, "test_lh"));
    }

    @Test
    public void lhu() throws IOException {
        assertTrue(test.runTestWithName(6, "test_lhu"));
    }

    @Test
    public void li() throws IOException {
        assertTrue(test.runTestWithName(6, "test_li"));
    }

    @Test
    public void lui() throws IOException {
        assertTrue(test.runTestWithName(6, "test_lui"));
    }

    @Test
    public void lw() throws IOException {
        assertTrue(test.runTestWithName(6, "test_lw"));
    }

/*    @Test
    public void mul() throws IOException {
        assertTrue(test.runTestWithName(6, "test_mul"));
    }*/

/*    @Test
    public void mulh() throws IOException {
        assertTrue(test.runTestWithName(6, "test_mulh"));
    }*/

/*    @Test
    public void mulhsu() throws IOException {
        assertTrue(test.runTestWithName(6, "test_mulhsu"));
    }*/

/*    @Test
    public void mulhu() throws IOException {
        assertTrue(test.runTestWithName(6, "test_mulhu"));
    }*/

    @Test
    public void or() throws IOException {
        assertTrue(test.runTestWithName(6, "test_or"));
    }

    @Test
    public void ori() throws IOException {
        assertTrue(test.runTestWithName(6, "test_ori"));
    }

//    @Test
//    public void random1() throws IOException {
//        assertTrue(test.runTestWithName(6, "test_random1"));
//    }
//
//    @Test
//    public void random2() throws IOException {
//        assertTrue(test.runTestWithName(6, "test_random2"));
//    }
//
//    @Test
//    public void random3() throws IOException {
//        assertTrue(test.runTestWithName(6, "test_random3"));
//    }
//
//    @Test
//    public void random4() throws IOException {
//        assertTrue(test.runTestWithName(6, "test_random4"));
//    }
//
//    @Test
//    public void random5() throws IOException {
//        assertTrue(test.runTestWithName(6, "test_random5"));
//    }
//
//    @Test
//    public void random6() throws IOException {
//        assertTrue(test.runTestWithName(6, "test_random6"));
//    }
//    @Test
//    public void random7() throws IOException {
//        assertTrue(test.runTestWithName(6, "test_random7"));
//    }
//
//    @Test
//    public void random8() throws IOException {
//        assertTrue(test.runTestWithName(6, "test_random8"));
//    }
//
//    @Test
//    public void random9() throws IOException {
//        assertTrue(test.runTestWithName(6, "test_random9"));
//    }
//
//    @Test
//    public void random10() throws IOException {
//        assertTrue(test.runTestWithName(6, "test_random10"));
//    }
//
///*    @Test
/*    public void rem() throws IOException {
        assertTrue(test.runTestWithName(6, "test_rem"));
    }*/

/*    @Test
    public void remu() throws IOException {
        assertTrue(test.runTestWithName(6, "test_remu"));
    }*/

    @Test
    public void sb() throws IOException {
        assertTrue(test.runTestWithName(6, "test_sb"));
    }

    @Test
    public void sh() throws IOException {
        assertTrue(test.runTestWithName(6, "test_sh"));
    }

    @Test
    public void sll() throws IOException {
        assertTrue(test.runTestWithName(6, "test_sll"));
    }

    @Test
    public void slli() throws IOException {
        assertTrue(test.runTestWithName(6, "test_slli"));
    }

    @Test
    public void slt() throws IOException {
        assertTrue(test.runTestWithName(6, "test_slt"));
    }

    @Test
    public void slti() throws IOException {
        assertTrue(test.runTestWithName(6, "test_slti"));
    }

    @Test
    public void sltiu() throws IOException {
        assertTrue(test.runTestWithName(6, "test_sltiu"));
    }

    @Test
    public void sra() throws IOException {
        assertTrue(test.runTestWithName(6, "test_sra"));
    }

    @Test
    public void srai() throws IOException {
        assertTrue(test.runTestWithName(6, "test_srai"));
    }

    @Test
    public void srl() throws IOException {
        assertTrue(test.runTestWithName(6, "test_srl"));
    }

    @Test
    public void srli() throws IOException {
        assertTrue(test.runTestWithName(6, "test_srli"));
    }

    @Test
    public void sub() throws IOException {
        assertTrue(test.runTestWithName(6, "test_sub"));
    }

    @Test
    public void sw() throws IOException {
        assertTrue(test.runTestWithName(6, "test_sw"));
    }

    @Test
    public void xor() throws IOException {
        assertTrue(test.runTestWithName(6, "test_xor"));
    }

    @Test
    public void xori() throws IOException {
        assertTrue(test.runTestWithName(6, "test_xori"));
    }

}

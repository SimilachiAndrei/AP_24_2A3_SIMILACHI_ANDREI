
import com.example.Test;

public class TestsClass {


    private int sm = 1;
    private boolean nm = false;
    @Test
    public static void m1() {
        throw new RuntimeException("Boom");
    }

    @Test
    public static void m2() {
        throw new RuntimeException("Boom");
    }

    @Test
    public static void m3() {
        throw new RuntimeException("Crash");
    }

    @Test
    public static void m4() {
        System.out.println("Success!");
    }

    public static void main(String[] args) {

    }
}


import java.lang.reflect.Field;

public class Test {

    public static final Integer MAX = 1;

    @org.junit.Test
    public void test() throws NoSuchFieldException, IllegalAccessException {

        Field field = Test.class.getField("MAX");
        field.setAccessible(true);
        field.setInt(Test.class, new Integer(12));
        System.out.println(Test.MAX);

    }
}

package replaceMethod;

/**
 * Created by rxia on 2015/11/12.
 */
public class TestChangeMethod {

    public TestChangeMethod(String a,String b) {
        System.out.println(" a  "+ a);
        System.out.println(" b " + b);
    }

    public TestChangeMethod(String a) {
        System.out.println(" a  "+ a);

    }

    public void changeMe(){
        System.out.println(" change me !");
    }
}

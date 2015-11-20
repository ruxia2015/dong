package proxy.dynamicProxy;

/**
 * Created by rxia on 2015/11/20.
 */
public class BookFacadeImpl implements BookFace {
    @Override
    public void addBook() {
        System.out.println("增加图书方法。。。。。。");
    }
}

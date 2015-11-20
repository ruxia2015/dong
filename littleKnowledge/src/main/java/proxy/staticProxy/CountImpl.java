package proxy.staticProxy;

/**
 * Created by rxia on 2015/11/20.
 */
public class CountImpl implements Count {
    @Override
    public void queryCount() {
        System.out.println("查看账户。。。。");
    }

    @Override
    public void updateCount() {

        System.out.print("更新账户。。。。");
    }
}

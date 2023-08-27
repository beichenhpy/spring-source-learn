package cn.beichenhpy.aop.proxy;

public class DefaultEchoService implements EchoService {
    @Override
    public void echo(String message) {
        System.out.println(message);
//        int i = 1 / 0;
    }
}

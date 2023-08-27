package cn.beichenhpy.aop.proxy;

public class ProxyEchoService implements EchoService {

    private final EchoService echoService;

    public ProxyEchoService(EchoService echoService) {
        this.echoService = echoService;
    }

    @Override
    public void echo(String message) {
        System.out.println("before enhance");
        echoService.echo(message);
        System.out.println("after enhance");
    }
}

package cn.beichenhpy.annotations;


import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;

/**
 * {@link org.springframework.context.annotation.ComponentScan} 示例
 *
 * @see org.springframework.stereotype.Component
 * @see org.springframework.context.annotation.ComponentScan
 */

@ComponentScan(basePackages = "cn.beichenhpy.annotations")
public class ComponentScanDemo {


    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        //注册当前为配置类
        applicationContext.register(ComponentScanDemo.class);
        //容器实例化
        applicationContext.refresh();



        //容器关闭
        applicationContext.close();
    }
}

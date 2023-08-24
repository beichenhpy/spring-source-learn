package cn.beichenhpy.annotations;


import org.springframework.context.annotation.AnnotationConfigApplicationContext;

@Father
public class AnnotationAttributeOverridesDemo {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        //注册当前为配置类
        applicationContext.register(AnnotationAttributeOverridesDemo.class);
        //容器实例化
        applicationContext.refresh();

        TestBean bean = applicationContext.getBean(TestBean.class);
        System.out.println(bean);

        //容器关闭
        applicationContext.close();
    }
}

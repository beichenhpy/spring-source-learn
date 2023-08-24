package cn.beichenhpy.dependency.source;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.io.Resource;

import javax.annotation.PostConstruct;

/**
 * 外部化配置依赖来源
 */
@Configuration
@PropertySource(value = "classpath:/META-INF/default.properties", encoding = "UTF-8")
public class ExternalDependencySourceDemo {

    @Value("${user.id:-1}")
    private Long id;

    @Value("${file.name}")
    private String text;

    @Value("${user.fileLocation:classpath:/default.properties}")
    private Resource location;

    @PostConstruct
    public void init() {
        System.out.println("id: " + id);
        System.out.println("location: " + location);
        System.out.println("text: " + text);
    }

    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        //注册
        applicationContext.register(ExternalDependencySourceDemo.class);
        //刷新
        applicationContext.refresh();


        //关闭
        applicationContext.close();
    }
}

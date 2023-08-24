package cn.beichenhpy.domain;

import cn.beichenhpy.enums.CityEnum;
import org.springframework.beans.factory.BeanNameAware;
import org.springframework.core.io.Resource;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class User implements BeanNameAware {
    private Integer id;

    private String name;

    private Resource configLocation;

    private CityEnum city;

    private CityEnum[] loveCities;

    private transient String beanName;

    public List<CityEnum> getWorkCities() {
        return workCities;
    }

    public void setWorkCities(List<CityEnum> workCities) {
        this.workCities = workCities;
    }

    private List<CityEnum> workCities;

    public Resource getConfigLocation() {
        return configLocation;
    }

    public void setConfigLocation(Resource configLocation) {
        this.configLocation = configLocation;
    }

    public CityEnum getCity() {
        return city;
    }

    public void setCity(CityEnum city) {
        this.city = city;
    }

    public CityEnum[] getLoveCities() {
        return loveCities;
    }

    public void setLoveCities(CityEnum[] loveCities) {
        this.loveCities = loveCities;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public User() {
    }

    public User(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", configLocation=" + configLocation +
                ", city=" + city +
                ", loveCities=" + Arrays.toString(loveCities) +
                ", workCities=" + workCities +
                '}';
    }

    public static User createUser(String name) {
        return new User(new Random().nextInt(), name);
    }

    @Override
    protected void finalize() throws Throwable {
        super.finalize();
        System.out.println("当前对象正在被gc。。。");
    }


    @PostConstruct
    public void init() {
        System.out.println(this.beanName + "对象正在初始化...");
    }


    @PreDestroy
    public void destroy() {
        System.out.println(this.beanName + "对象正在销毁...");
    }

    @Override
    public void setBeanName(String name) {
        this.beanName = name;
    }
}

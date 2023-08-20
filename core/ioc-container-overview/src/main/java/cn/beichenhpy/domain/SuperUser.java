package cn.beichenhpy.domain;

import cn.beichenhpy.annotation.Super;
import org.springframework.beans.factory.BeanFactory;

/**
 * 超级用户
 *
 */
@Super
public class SuperUser extends User{

    private String superPermission;


    public String getSuperPermission() {
        return superPermission;
    }

    public void setSuperPermission(String superPermission) {
        this.superPermission = superPermission;
    }

    @Override
    public String toString() {
        return "SuperUser{" +
                "superPermission='" + superPermission + '\'' +
                "} " + super.toString();
    }
}

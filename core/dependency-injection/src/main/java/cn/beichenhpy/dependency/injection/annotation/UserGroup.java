package cn.beichenhpy.dependency.injection.annotation;

import org.springframework.beans.factory.annotation.Qualifier;

import java.lang.annotation.*;

/**
 * userGroup分组 扩展{@link Qualifier}
 */
@Target({ElementType.FIELD, ElementType.METHOD, ElementType.PARAMETER, ElementType.TYPE, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Documented
@Qualifier
public @interface UserGroup {
}

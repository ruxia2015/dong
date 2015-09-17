package common.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by rxia on 2015/9/9.
 */

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface ColumnAnnotation {
    /**
     * 字段的类型
     * @return
     */
    String column_type() default "";

    /**
     * 字段的大小，0表示不设置
     * @return
     */
    int type_size() default  0;

}

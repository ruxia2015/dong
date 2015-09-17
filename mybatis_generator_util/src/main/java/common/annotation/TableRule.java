package common.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by rxia on 2015/9/8.
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface TableRule {
    /**
     * 字段或者表名间单词间的分隔符
     */
    String column_split() default "_";

    String table_name() default  "";

    /**
     * bean的真实的包名，空则为当前的包名为主
     * @return
     */
    String bean_real_packageName() default "";

    /**
     * DAO的包名，如果config里面没有配置，则这里必须配置
     * @return
     */
    String dao_real_packageName() default "";




}

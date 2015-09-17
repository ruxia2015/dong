package common.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


/**
 * sql语句注解
 * <一句话功能简述>
 * <功能详细描述>
 *
 * @author xuruxia
 * @version [版本号, 2015年4月15日]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD})
public @interface SQLAnnotation {

    /**
     * 更新操作时，是否为查询条件(更新和条件只能二选一)
     * <功能详细描述>
     *
     * @return boolean [返回类型说明]
     * @throws throws [违例类型] [违例说明]
     * @see [类、类#方法、类#成员]
     */
    public boolean update_is_where() default false;


    /**
     * 作为查询条件的时候，其运算符，默认为等号
     * <功能详细描述>
     *
     * @return String [返回类型说明]
     * @throws throws [违例类型] [违例说明]
     * @see [类、类#方法、类#成员]
     */
    public String where_oper() default "=";


    /**
     * 作为查询条件的时候，对应的字段
     * <功能详细描述>
     *
     * @return String [返回类型说明]
     * @throws throws [违例类型] [违例说明]
     * @see [类、类#方法、类#成员]
     */
    public String where_column() default "";

    /**
     * 是否为表中的字段，默认为是
     * <功能详细描述>
     *
     * @return boolean [返回类型说明]
     * @throws throws [违例类型] [违例说明]
     * @see [类、类#方法、类#成员]
     */
    public boolean is_column() default true;


    public boolean is_Ingore() default false;


}

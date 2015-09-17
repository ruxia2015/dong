package common.util;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;

/**
 * Created by rxia on 2015/9/8.
 */
public class AnnotationUtil {

    public static Annotation getFieldAnnotation(Class cls,String fieldName, Class annotation){
        Field f;
        try {
            f = cls.getDeclaredField(fieldName);
            if(f!=null){

                f.setAccessible(true);
                return  f.getAnnotation(annotation);
            }
        } catch (NoSuchFieldException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (SecurityException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return null;
    }
}

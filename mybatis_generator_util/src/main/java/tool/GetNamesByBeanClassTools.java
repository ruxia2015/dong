package tool;

import common.annotation.TableRule;
import common.util.PropertiesUtil;
import common.util.StringTools;

import java.lang.reflect.Method;

/**
 * Created by rxia on 2015/9/8.
 * <p/>
 * 根据bean的class获取各种名称
 * <p/>
 * 如表名、dao名，mapper名称
 */
public class GetNamesByBeanClassTools {
    public static int METHOD_SAVE = 1;
    public static int METHOD_UPDATE = 2;
    public static int METHOD_DELETE = 3;
    public static int METHOD_COUNT = 4;
    public static int METHOD_QUERY = 5;
    public static int METHOD_FIND = 6;

    public static String getTableName(Class cls) {
        TableRule tableRule = (TableRule) cls.getAnnotation(TableRule.class);
        String tableName = "";
        if (tableRule != null) {
            tableName = tableRule.table_name();
        }

        String table_name_split = PropertiesUtil.getVaue(PropertyConstant.TABLE_NAME_SPLIT);
        if (StringTools.isEmptyOrNone(tableName)) {
            String className = cls.getSimpleName();
            String tableNamePrefix = PropertiesUtil.getVaue(PropertyConstant.TABLE_NAME_PREFIX);
            String beanSuffix = PropertiesUtil.getVaue(PropertyConstant.BEAN_SUFFIX_NAME);

            // if(className.endsWith(beanSuffix)){
            className = className.replaceAll(beanSuffix + "$", "");
            //   }

            className = wordAddSplit(className, table_name_split);
            tableName = tableNamePrefix + className;
        }
        return tableName.toUpperCase();

    }

    public static String getMapperName(Class cls) {
        String mpperSuffix = PropertiesUtil.getVaue(PropertyConstant.MAPPER_SUFFIX_NAME);
        String beanSuffix = PropertiesUtil.getVaue(PropertyConstant.BEAN_SUFFIX_NAME);
        String beanName = cls.getSimpleName().replaceAll(beanSuffix + "$", "");

        String mapperName = beanName + mpperSuffix + ".xml";

        return mapperName;
    }

    public static String getDAOAllName(Class cls) {
        String packageName = getDAOPackage(cls);
        String daoClassName = getDAOName(cls);
        if (StringTools.isEmptyOrNone(packageName)) {
            return daoClassName;
        }
        return packageName + "." + getDAOName(cls);
    }

    public static String getDAOName(Class cls) {
        String className = cls.getSimpleName();
        String beanSuffix = PropertiesUtil.getVaue(PropertyConstant.BEAN_SUFFIX_NAME);
        return className.replaceAll(beanSuffix + "$", "") + "DAO";

    }

    public static String getDAOPackage(Class cls) {
        TableRule columnRuleAnnon = (TableRule) cls.getAnnotation(TableRule.class);

        String packageName = null;
        if (columnRuleAnnon != null) {
            packageName = columnRuleAnnon.dao_real_packageName();
        }

        if (StringTools.isEmptyOrNone(packageName)) {
            packageName = PropertiesUtil.getVaue(PropertyConstant.DAO_REAL_PACKAGE_NAME);
        }

        if (StringTools.isEmptyOrNone(packageName)) {
            return "";
        }
        return packageName;
    }

    public static String getBeanRealName(Class cls) {
        TableRule columnRuleAnnon = (TableRule) cls.getAnnotation(TableRule.class);

        String realName = null;
        if (columnRuleAnnon != null) {
            realName = columnRuleAnnon.bean_real_packageName();
        }

        if (StringTools.isEmptyOrNone(realName)) {
            return cls.getName();
        }

        if (realName.endsWith(cls.getSimpleName())) {
            return realName;
        } else {
            return (realName + "." + cls.getSimpleName()).replaceAll("\\.\\.", ".");
        }
    }

    public static String wordAddSplit(String words, String split) {
        if (!StringTools.isEmptyOrNone(words) && !StringTools.isEmptyOrNone(split)) {
            char[] chars = words.toCharArray();
            StringBuffer columns = new StringBuffer();

            for (int i = 0; i < chars.length; i++) {
                if (i >= 2 && chars[i] >= 65 && chars[i] <= 90 && chars[i - 1] >= 97 && chars[i - 1] <= 122) {
                    columns.append(split);
                }
                columns.append(chars[i]);
            }
            return columns.toString().toUpperCase();
        }
        return words;
    }

    public static String getColumnNameByfield(String key, TableRule cr) {
        String split = "";
        if (cr == null) {
            Class cls = TableRule.class;
            try {
                Method m = cls.getDeclaredMethod("column_split");
                split = (String) m.getDefaultValue();
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            }
        } else {
            cr.column_split();
        }


        return wordAddSplit(key, split);


    }

    public static String getDAOMethod(Class beanCls, int type) {

        if (METHOD_SAVE == type) {
            return getDAOMethod(beanCls, "save");
        } else if (METHOD_UPDATE == type) {
            return getDAOMethod(beanCls, "update");
        } else if (METHOD_DELETE == type) {
            return getDAOMethod(beanCls, "delete");
        } else if (METHOD_COUNT == type) {
            return getDAOMethod(beanCls, "count");
        } else if (METHOD_QUERY == type) {
            return getDAOMethod(beanCls, "query", "List");
        } else if (METHOD_FIND == type) {
            return getDAOMethod(beanCls, "find");
        } else {
            return getDAOMethod(beanCls, "");
        }

    }


    private static String getDAOMethod(Class beanCls, String methodPrefix) {
        return getDAOMethod(beanCls, methodPrefix, "");

    }

    private static String getDAOMethod(Class beanCls, String methodPrefix, String methodSuffix) {
        //获取类名
        String beanName = beanCls.getSimpleName();
        String beanSuffix = PropertiesUtil.getVaue(PropertyConstant.BEAN_SUFFIX_NAME);
        beanName = beanName.replaceAll(beanSuffix + "$", "");

        return (methodPrefix + beanName + methodSuffix);

    }

/*    public static void main(String[] ards) {
        char c = 'a';
        TableRule annotation = (TableRule) AnnotationUtil.getFieldAnnotation(CategoryBean.class, "nameField", TableRule.class);
        System.out.println(getColumnNameByfield("valueSet", null));
        System.out.println(getTableName(CategoryBean.class));

    }*/
}

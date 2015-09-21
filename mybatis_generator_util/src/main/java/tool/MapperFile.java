package tool;

import common.util.PropertiesUtil;


/**
 * Created by rxia on 2015/9/8.
 */
public class MapperFile {

    /**
     * 生成mapper文件的内容
     *
     * @param cls
     * @return
     */
    public static String mapperContent(Class cls) {

        StringBuffer mapperContent = new StringBuffer();

        //1、文件头
        mapperContent.append("<?xml version=\"1.0\" encoding=\"UTF-8\" ?>  \r\n")
                .append("<!DOCTYPE mapper PUBLIC \"-//mybatis.org//DTD Mapper 3.0//EN\"  \"http://mybatis.org/dtd/mybatis-3-mapper.dtd\"> \r\n");

        //2、命名空间

        mapperContent.append("<mapper namespace=\"" + GetNamesByBeanClassTools.getDAOAllName(cls) + "\" > \r\n\n");

        //获取类的全路径
        String bean_allName = GetNamesByBeanClassTools.getBeanRealName(cls);


        //获取类名
        String beanName = cls.getSimpleName();
        String beanSuffix = PropertiesUtil.getVaue(PropertyConstant.BEAN_SUFFIX_NAME);

        // if(className.endsWith(beanSuffix)){
        beanName = beanName.replaceAll(beanSuffix + "$", "");

        //3-1 生成插入语句
        mapperContent.append(" <insert id=\"save" + beanName + "\" parameterType=\"" + bean_allName + "\">  \r\n    ")
                .append(SimpleSQLXMLTemplate.generatorInsertSQL(cls))
                .append("\r\n")
                .append(" </insert> \r\n");

        //3-2 生成统计语句
        mapperContent.append(" <select id=\"count" + beanName + "\" parameterType=\"" + bean_allName + "\"  resultType=\"int\"> \r\n    ")
                .append(SimpleSQLXMLTemplate.generatorCountSQL(cls))
                .append("\r\n")
                .append(" </select> \r\n");

        //3-3 生成查询语句
        mapperContent.append(" <select id=\"query" + beanName + "List\" parameterType=\"" + bean_allName + "\"  resultType=\"" + bean_allName + "\"> \r\n    ")
                .append(SimpleSQLXMLTemplate.generatorQuerySQL(cls))
                .append("\r\n")
                .append(" </select> \r\n");

        //3-3-1 生成查找语句
        mapperContent.append(" <select id=\"find" + beanName + "\" parameterType=\"" + bean_allName + "\"  resultType=\""+ bean_allName +"\"> \r\n    ")
                .append(SimpleSQLXMLTemplate.generatorQuerySQL(cls))
                .append("\r\n")
                .append(" </select> \r\n");

        //3-4 生成删除语句
        mapperContent.append("<delete id=\"delete" + beanName + "\" parameterType=\"" + bean_allName + "\"  > \r\n    ")
                .append(SimpleSQLXMLTemplate.generatorDeleteSQL(cls))
                .append("\r\n")
                .append(" </delete> \r\n");

        //3-5 生成更新语句
        mapperContent.append(" <update id=\"update" + beanName + "\" parameterType=\"" + bean_allName + "\"  > \r\n    ")
                .append(SimpleSQLXMLTemplate.generatorUpdateSQL(cls))
                .append("\r\n")
                .append(" </update> \r\n");

        //end  2、命名空间
        mapperContent.append("</mapper>");


        return mapperContent.toString().replaceAll("\n", "\n    ");


    }



/*    public static void main(String[] args) {
        System.out.print(mapperContent(CategoryBean.class));
    }*/


}

package tool;




/**
 * Created by rxia on 2015/9/9.
 */
public class DAOFile {



    public static String DAOContent(Class cls) {
        StringBuffer content = new StringBuffer();

        content.append("package " + GetNamesByBeanClassTools.getDAOPackage(cls) + ";\n\n");
        content.append("import java.util.List;\r\n");
        content.append("import " + GetNamesByBeanClassTools.getBeanRealName(cls) + ";\r\n");

        content.append("\n");

        content.append("public interface " + GetNamesByBeanClassTools.getDAOName(cls) + "\n{\n");

        //获取类名
        String beanClassName = cls.getSimpleName();

        content.append("    public void " + GetNamesByBeanClassTools.getDAOMethod(cls, GetNamesByBeanClassTools.METHOD_SAVE) + "(" + beanClassName + " bean);\n");
        content.append("    public void " + GetNamesByBeanClassTools.getDAOMethod(cls, GetNamesByBeanClassTools.METHOD_UPDATE) + "(" + beanClassName + " bean);\n");
        content.append("    public void " + GetNamesByBeanClassTools.getDAOMethod(cls, GetNamesByBeanClassTools.METHOD_DELETE) + "(" + beanClassName + " bean);\n");
        content.append("    public int " + GetNamesByBeanClassTools.getDAOMethod(cls, GetNamesByBeanClassTools.METHOD_COUNT) + "(" + beanClassName + " bean);\n");
        content.append("    public List<" + beanClassName + "> " + GetNamesByBeanClassTools.getDAOMethod(cls, GetNamesByBeanClassTools.METHOD_QUERY) + "(" + beanClassName + " bean);\n");
        content.append("    public " + beanClassName + "  " + GetNamesByBeanClassTools.getDAOMethod(cls, GetNamesByBeanClassTools.METHOD_FIND) + "(" + beanClassName + " bean);\n");


        content.append("} ");


        return content.toString();
    }

}

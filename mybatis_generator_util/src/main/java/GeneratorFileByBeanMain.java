import common.util.FileUtil;
import common.util.PropertiesUtil;
import common.util.classUtil;
import tool.DAOFile;
import tool.GetNamesByBeanClassTools;
import tool.MapperFile;
import tool.PropertyConstant;

import java.util.List;

/**
 * Created by rxia on 2015/9/8.
 */

/**
 * 根据bean生成dao层，mapper文件
 */
public class GeneratorFileByBeanMain {

    public static void main(String[] args) {
        //1、获取class
        List<Class<?>> classes = classUtil.getClasses(PropertiesUtil.getVaue(PropertyConstant.BEAN_PACKAGE_KEY));

        for (Class<?> cls : classes) {
            //2.生成dao文件
            String daoPath = PropertiesUtil.getVaue(PropertyConstant.FILE_PATH_DAO);
            String daoPackage = GetNamesByBeanClassTools.getDAOPackage(cls);
            String daoName = GetNamesByBeanClassTools.getDAOName(cls);
            String daoContent = DAOFile.DAOContent(cls);

            FileUtil.writeToFile(daoPath + "/" + daoPackage.replaceAll("\\.", "\\\\"), daoName + ".java", daoContent);

            //3.生成mapper文件
            String mapperPath = PropertiesUtil.getVaue(PropertyConstant.FILE_PATH_MAPPER);
            String mapperName = GetNamesByBeanClassTools.getMapperName(cls);
            String mapperContent = MapperFile.mapperContent(cls);
            FileUtil.writeToFile(mapperPath, mapperName, mapperContent);

            //4.生成 script




        }


    }


}

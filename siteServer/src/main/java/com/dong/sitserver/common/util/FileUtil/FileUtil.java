package com.dong.sitserver.common.util.FileUtil;

import com.dong.sitserver.common.util.StringTools;

import java.io.*;
import java.util.Collection;

public class FileUtil {
    /**
     * <一句话功能简述>
     * <功能详细描述>
     *
     * @param path
     * @return File [返回类型说明]
     * @throws throws [违例类型] [违例说明]
     * @see [类、类#方法、类#成员]
     */
    public static File getFile(String path) {
        File file = new File(path);
        if (!file.getParentFile().exists()) {
            file.getParentFile().mkdirs();
        }
        return file;
    }

    public static boolean writeCollectionToFile(String path, Collection<String> lists, boolean isAppend) {
        if (lists == null || lists.size() == 0) {
            return true;
        }
        String content = "";
        for (String str : lists) {
            content = content + "\r\n";
        }
        return writeToFile(path, content, isAppend);
    }

    public static boolean writeToFile(String path,
                                      String content) {
        return writeToFile(path, content, false);
    }

    /**
     * 文件读取
     *
     * @param path     文件路径
     * @param content  内容
     * @param isAppend 是否追加
     * @return
     */
    public static boolean writeToFile(String path,
                                      String content, boolean isAppend) {
        if (StringTools.isEmptyOrNone(content)) {
            return true;
        }
        File file = getFile(path);

        FileOutputStream fos = null;

        try {
            fos = new FileOutputStream(file, isAppend);
            if (file.exists()) {
                file.createNewFile();
            }
            byte[] con = content.getBytes();
            fos.write(con);
        } catch (FileNotFoundException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        } catch (IOException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        } finally {
            try {
                fos.close();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        return false;
    }


    //文件读取
    public static String readFile(String filePath) {
        File f = new File(filePath);
        if (!f.exists() || !f.isFile()) {
            System.out.println("文件不存在");
            return null;
        }
        try {
            BufferedInputStream bis = new BufferedInputStream(new FileInputStream(f));
            byte[] contents = new byte[1024];
            int byteRead = 0;
            String strFileContents = "";
            try {
                while ((byteRead = bis.read(contents)) != -1) {
                    strFileContents = strFileContents + new String(contents, 0, byteRead);
                }
            } catch (IOException e) {
                // TODO Auto-generated catch block  
                e.printStackTrace();
            } finally {
                bis.close();
            }
            return strFileContents;
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
    }


}

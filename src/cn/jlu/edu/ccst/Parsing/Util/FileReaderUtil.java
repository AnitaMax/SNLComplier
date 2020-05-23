package cn.jlu.edu.ccst.Parsing.Util;

import cn.jlu.edu.ccst.View.Windows.MainWindow;

import java.io.*;
import java.util.ArrayList;

public  class FileReaderUtil {
    private static File getResource(String file) {
        return new File(MainWindow.class.getClassLoader().getResource(file).getPath());
    }
    public static ArrayList<String> readFile(String filepath){
        ArrayList<String> lines=new ArrayList<>();
        //var path= ProductionElementController.class.getResource(filepath);
        //var path=getResource(filepath);
        /*
        MainWindow.class.getClassLoader()就回到了classpath
        Resource文件夹需要手动指定
         */
        InputStream is = MainWindow.class.getClassLoader().getResourceAsStream(filepath);//jar包中的资源文件不能用path，只能用流来读取
        try(var productions= new BufferedReader(new InputStreamReader(is))){
            String line;
            while ((line=productions.readLine())!=null){
                lines.add(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return lines;
    }
}

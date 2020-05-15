package cn.jlu.edu.ccst.Parsing.Util;

import cn.jlu.edu.ccst.Parsing.Controller.ProductionElementController;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public  class FileReaderUtil {
    public static ArrayList<String> readFile(String filepath){
        ArrayList<String> lines=new ArrayList<>();
        var path= ProductionElementController.class.getResource(filepath);
        try(var productions= new BufferedReader(new FileReader(path.getPath())) ){
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

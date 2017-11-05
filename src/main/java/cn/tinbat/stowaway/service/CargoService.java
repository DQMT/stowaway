package cn.tinbat.stowaway.service;

import cn.tinbat.stowaway.domain.Cargo;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.*;

@Service(value = "cargoService")
public class CargoService {
    @Value("${filepath}")
    private String filepath;

    public void saveFile(Cargo cargo){
        File file = new File(filepath);
        if(!file.isDirectory()){
            file.mkdir();
        }
        try {
            FileWriter fileWriter = new FileWriter(filepath+cargo.getFilename()+".txt");
            fileWriter.write(cargo.getGoods().toString());
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getGoods(String filename){
        StringBuilder stringBuilder = new StringBuilder();
        int tmp;
        try {
            FileReader fileReader = new FileReader(filepath+filename+".txt");
            while ((tmp=fileReader.read())!=-1){
                stringBuilder.append((char)tmp);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return "";
        } catch (IOException e) {
            e.printStackTrace();
            return "";
        }
        return stringBuilder.toString();
    }


}

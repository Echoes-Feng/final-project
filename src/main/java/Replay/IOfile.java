package Replay;


import Creature.Creature;

import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class IOfile{
    public static SL save;
    public void resultOut(List<List<String>> Action){//将结果输出到文件
        File file = new File( "Record" + File.separator + "record.xml");
        if(!file.getParentFile().exists()){
            file.getParentFile().mkdirs();
        }
        BufferedWriter writer=null;
        try{
            writer=new BufferedWriter(new FileWriter(file));
            Iterator<List<String>> it = Action.iterator();
            while(it.hasNext()){
                for(String s : it.next()){
                    writer.write(s);
                }
                writer.write("\r\n");
                writer.flush();
            }
        }catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                writer.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    public void resultIn(File file,Creature creature){
        BufferedReader Reader=null;
        try{
            Reader = new BufferedReader(new FileReader(file));
            String temp = null;
            while ((temp = Reader.readLine()) != null) {
                if(temp.charAt(4)==creature.getName()){
                    creature.getActionSave().add(temp);
                }

            }
            Reader.close();
         } catch (IOException e) {
            e.printStackTrace();
         } finally {
            if (Reader != null) {
                try {
                    Reader.close();
                } catch (IOException e1) { }
        }
    }
    }
}





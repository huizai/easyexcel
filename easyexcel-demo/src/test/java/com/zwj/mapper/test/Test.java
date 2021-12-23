package com.zwj.mapper.test;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.write.metadata.WriteSheet;
import com.zwj.easyexcel.controller.StatisticsData;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Test {
    public static void main(String[] args) throws IOException {
        {


            List<StatisticsData> targetDatas = new ArrayList<StatisticsData>();
            File dirFile = new File("d:/log");

            File[] listFiles = dirFile.listFiles();
            StringBuilder result = new StringBuilder();
            for(File file:listFiles) {
                result.append(file.getName()+"\r\n");
                StatisticsData sd = new StatisticsData();
                sd.setJCode(file.getName().replace(".js", ""));
                System.out.println(file.getName());
                StringBuilder sBuilder = new StringBuilder();

                try{
                    BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file), "UTF-8"));//构造一个BufferedReader类来读取文件

                    String s = null;
                    while((s = br.readLine())!=null){//使用readLine方法，一次读一行
                        if(s.startsWith(" take")) {
                            System.out.println(s);
                            sBuilder.append(s+"\r\n");
                        }
//                        result.append( System.lineSeparator() + s);
                    }
                    br.close();


                }catch(Exception e){
                    e.printStackTrace();
                }
                String[] split = sBuilder.toString().split("\r\n",-1);
                if(split.length==1) {
                    sd.setTake1(split[0]);
                }
                if(split.length ==2) {
                    sd.setTake1(split[0]);
                    sd.setTake2(split[1]);

                }
                if(split.length >= 3) {
                    sd.setTake1(split[0]);
                    sd.setTake2(split[1]);
                    sd.setTake3(split[2]);
                }
                System.out.println( StartApplicationTest.readLastLine(file,"utf8") );
                String lastLine =  StartApplicationTest.readLastLine(file,"utf8");
                String[] laststr = lastLine.split(" ");
                if(laststr.length  <11) {
                    continue;
                }
                sd.setPerDAy(laststr[1]);

                sd.setAccount(laststr[8]);
                sd.setReMain(laststr[6]);
                sd.setTotalIncomePercent(Double.valueOf(laststr[10].replace("=","")));
                //    	       result.append(readLastLine(file,"utf8") +"\r\n");
                targetDatas.add(sd);
//                break;
            }
            String fileName =  "D:\\\\s\\\\s.xlsx";
            ExcelWriter excelWriter = EasyExcel.write(fileName, StatisticsData.class).build();
            WriteSheet writeSheet = EasyExcel.writerSheet("data").build();
            excelWriter.write(targetDatas, writeSheet);
            /// 千万别忘记finish 会帮忙关闭流
            excelWriter.finish();

            //			BufferedWriter out = new BufferedWriter(new FileWriter("d:/11111.txt"));
            //			out.write(result.toString());
            //		    out.close();
            //		    System.out.println( "文件创建成功！");

        }
    }
}

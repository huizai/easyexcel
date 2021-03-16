package com.zwj.easyexcel.controller;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.write.metadata.WriteSheet;
import org.apache.commons.io.FileUtils;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FindFundStocks {


    public static   Map<String,List<String>> JCode = new HashMap<>();
    static   String fileName =  "D:\\\\s\\\\test.xlsx";
    public static void main(String[] args) throws Exception{
        //输入想要查询的内容 返回时持有改内容的鸡
        System.out.println("輸入...");
        Scanner scanner = new Scanner(System.in);
        String scan = scanner.next();

//        prepareExcelData();
        EasyExcel.read(new File(fileName), Data.class, new ConfigFilterListener(scan)).sheet().doRead();


    }


    public static void prepareExcelData() throws  Exception{

        System.out.println("开始生成 excel");
        //读取鸡的详情.解析持仓.写入到excel
        File dir = new File("D:\\s\\jj");
        File[] files = dir.listFiles();
        List<Data> excelData = new ArrayList<>();

        for (File file : files) {
            String jJStockCode = getJJStockCode(file);
            String regex="stockCodes.*[0-9]{7}\"];";
            Pattern r = Pattern.compile(regex);
            // 现在创建 matcher 对象
            Matcher m = r.matcher(jJStockCode);

            if (m.find( )) {
                //stockCodes = ["6018881", "6005191", "015795", "036905", "0008582", "007005", "009685", "3007602", "0005682", "022695"];
                String[] str = m.group(0).replaceAll("stockCodes=\\[","").
                    replaceAll("\\[","").replaceAll("\"", "")
                    .replaceAll("];","").split(",",-1);

                String key = file.getName().replace(".js", "");
                for (String s : str) {

                    Data d = new Data();
                    d.setJCode(key);
                    d.setStockCode(s);
                    excelData.add(d);
                }
                System.out.println(key+"解析成功 ");

            }
        }

        // 这里 需要指定写用哪个class去读
        ExcelWriter excelWriter = EasyExcel.write(fileName, Data.class).build();
        WriteSheet writeSheet = EasyExcel.writerSheet("data").build();
        excelWriter.write(excelData, writeSheet);
        /// 千万别忘记finish 会帮忙关闭流
        excelWriter.finish();
    }

    public static void downloadHttpUrl(String url, String dir, String fileName) {
        try {
            URL httpurl = new URL(url);
            File dirfile = new File(dir);
            if (!dirfile.exists()) {
                dirfile.mkdirs();
            }
            FileUtils.copyURLToFile(httpurl, new File(dir+fileName));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static String getJJStockCode(String fileName) throws IOException {
        FileInputStream fis=new FileInputStream(fileName);
        BufferedInputStream bis=new BufferedInputStream(fis);
        String content=null;
        //自定义缓冲区
        byte[] buffer=new byte[10240];
        int flag=0;
        while((flag=bis.read(buffer))!=-1){
            content+=new String(buffer, 0, flag);
        }
        //        System.out.println(content);
        //关闭的时候只需要关闭最外层的流就行了
        bis.close();

        return content;
    }


    public static String getJJStockCode(File fileName) throws IOException {
        FileInputStream fis=new FileInputStream(fileName);
        BufferedInputStream bis=new BufferedInputStream(fis);
        String content=null;
        //自定义缓冲区
        byte[] buffer=new byte[10240];
        int flag=0;
        while((flag=bis.read(buffer))!=-1){
            content+=new String(buffer, 0, flag);
        }
        //        System.out.println(content);
        //关闭的时候只需要关闭最外层的流就行了
        bis.close();

        return content;
    }


    //解析基金,从网上下载对应的基金file详情
    public static void GetFuncDataFile() throws Exception{
        String jJCode = getJJStockCode("D:\\s\\fundcode_search.js");
        String[] split = jJCode.split("\\]\\,\\[", -1);
        for (String s : split) {
            //"000002","HXCZHH","华夏成长混合(后端)","混合型","HUAXIACHENGZHANGHUNHE"
            String[] temp = s.replaceAll("null", "").replaceAll("\\[", "").split(",", -1);
            JCode.put(temp[0],new ArrayList<>());
        }
//        System.out.println(JCode);
        System.out.println(new Date().getTime());
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddhhmmss");
        String format = sdf.format(new Date());
        for (Map.Entry<String, List<String>> entry : JCode.entrySet()) {
            String url = "http://fund.eastmoney.com/pingzhongdata/"+entry.getKey().replaceAll("\"", "")+".js?v="+format;
            downloadHttpUrl(url,"d://s//jj//",entry.getKey().replaceAll("\"", "")+".js");
            Thread.currentThread().sleep(200);
        }
    }
}

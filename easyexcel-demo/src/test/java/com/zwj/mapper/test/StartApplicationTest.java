package com.zwj.mapper.test;
import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.RandomAccessFile;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.naming.java.javaURLContextFactory;
import org.apache.poi.hssf.record.common.FtrHeader;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.write.metadata.WriteSheet;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.parser.deserializer.BooleanFieldDeserializer;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zwj.StartApplication;
import com.zwj.easyexcel.controller.Data;
import com.zwj.easyexcel.controller.FindFundStocks;
import com.zwj.easyexcel.controller.StatisticsData;
import com.zwj.entity.Fund;
import com.zwj.mapper.FundMapper;
import com.zwj.util.HttpUtil;
@RunWith(SpringRunner.class)
@SpringBootTest(classes = StartApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class StartApplicationTest{
	@Autowired
    private FundMapper fundMapper;
	
	

    @Test
    public void contextLoads() {
    	
//    	
//    	 try {
//			File dir = new File("D:\\s\\jj");
//			 File[] files = dir.listFiles();
//			 List<Data> excelData = new ArrayList<>();
//
//			 for (File file : files) {
//			     String jJStockCode = FindFundStocks.getJJStockCode(file);
//			     String regex="Data_netWorthTrend =.*累计净值走势\\*\\/";
//			     Pattern r = Pattern.compile(regex);
//			     // 现在创建 matcher 对象
//			     Matcher m = r.matcher(jJStockCode);
//
//			     if (m.find( )) {
//			         String str = m.group(0);
////			         System.out.println(str);
//			         String str1 = str.replaceAll("Data_netWorthTrend = ", "").replaceAll(";\\/\\*累计净值走势\\*\\/", "");
////			         System.out.println(str1);
//
//			         JSONArray jsonArray = JSONObject.parseArray(str1);
////			        System.out.println(jsonArray.get(0));
//			         List<Fund> list = new ArrayList<Fund>();
//			         for(int i=0;i<jsonArray.size();i++) {
//			        	 JSONObject parseObject = JSONObject.parseObject(jsonArray.get(i).toString());
////				         System.out.println(parseObject.get("x")+"解析成功 ");
//				         Fund fund = new Fund();
//				         
//				         SimpleDateFormat format =  new SimpleDateFormat( "yyyy-MM-dd" );
//				         Long time=new Long(parseObject.get("x").toString());
//				         String d = format.format(time);
//				         Date date=format.parse(d);
//				         System.out.println("Format :"+d);
//				         fund.setFundcode(file.getName().replace(".js", ""));
//				         fund.setTimedate(d);
//				         //{"x":1604505600000,"y":0.931,"equityReturn":1.2,"unitMoney":""}
//				         fund.setCurrentday(Double.valueOf(parseObject.get("y").toString()));
//				         fund.setWave(Double.valueOf(parseObject.get("equityReturn").toString()));
////				         System.out.println(fund.toString());
////				         list.add(fund);
//				         fundMapper.insert(fund);
//			         }
//			         
//			        
//			        
//			     }
//			    
////			     break;
//			     System.out.println("处理完了"+file.getName());
//			 }
//		} catch (IOException | ParseException e) {
//			e.printStackTrace();
//		}

    	
//        System.out.println(("----- selectAll method test ------"));
//        List<Fund> userList = fundMapper.selectList(null);
//        //Assert.assertEquals(5, userList.size());
//        userList.forEach(System.out::println);
//    	
//    	try {
//    	
//    		
//    		
////			String url ="https://stock.finance.sina.com.cn/fundInfo/api/openapi.php/CaihuiFundInfoService.getNav?callback=jQuery111208456380533606518_1615362540442&symbol=260108&datefrom=2010-03-01&dateto=2021-03-10&page=2&_=1615362540465";
////			String result = HttpUtil.doGet(url);
////			System.out.println(result);
////			 String regex="\\[.*\\]";
////			 Pattern r = Pattern.compile(regex);
////			 // 现在创建 matcher 对象
////			 Matcher m = r.matcher(result);
////
////			 if (m.find( )) {
////				 String str = m.group(0);
////				 System.out.println(str);
//////				 JSONObject parseObject = JSONObject.parseObject( str);
////			 	//jQuery111208456380533606518_1615362540442({"result":{"status":{"code":0},"data":{"data":[{"fbrq":"2021-02-03 00:00:00","jjjz":"3.354","ljjz":"5.161"},{"fbrq":"2021-02-02 00:00:00","jjjz":"3.355","ljjz":"5.162"},{"fbrq":"2021-02-01 00:00:00","jjjz":"3.259","ljjz":"5.066"},{"fbrq":"2021-01-29 00:00:00","jjjz":"3.234","ljjz":"5.041"},{"fbrq":"2021-01-28 00:00:00","jjjz":"3.209","ljjz":"5.016"},{"fbrq":"2021-01-27 00:00:00","jjjz":"3.288","ljjz":"5.095"},{"fbrq":"2021-01-26 00:00:00","jjjz":"3.311","ljjz":"5.118"},{"fbrq":"2021-01-25 00:00:00","jjjz":"3.41","ljjz":"5.217"},{"fbrq":"2021-01-22 00:00:00","jjjz":"3.279","ljjz":"5.086"},{"fbrq":"2021-01-21 00:00:00","jjjz":"3.221","ljjz":"5.028"},{"fbrq":"2021-01-20 00:00:00","jjjz":"3.151","ljjz":"4.958"},{"fbrq":"2021-01-19 00:00:00","jjjz":"3.1","ljjz":"4.907"},{"fbrq":"2021-01-18 00:00:00","jjjz":"3.172","ljjz":"4.979"},{"fbrq":"2021-01-15 00:00:00","jjjz":"3.185","ljjz":"4.992"},{"fbrq":"2021-01-14 00:00:00","jjjz":"3.199","ljjz":"5.006"},{"fbrq":"2021-01-13 00:00:00","jjjz":"3.287","ljjz":"5.094"},{"fbrq":"2021-01-12 00:00:00","jjjz":"3.529","ljjz":"5.166"},{"fbrq":"2021-01-11 00:00:00","jjjz":"3.43","ljjz":"5.067"},{"fbrq":"2021-01-08 00:00:00","jjjz":"3.483","ljjz":"5.12"},{"fbrq":"2021-01-07 00:00:00","jjjz":"3.57","ljjz":"5.207"},{"fbrq":"2021-01-06 00:00:00","jjjz":"3.492","ljjz":"5.129"}],"total_num":"2692"}}})
//////			 	System.out.println(parseObject.get("result"));
//////			 	JSONObject dataObject = JSONObject.parseObject( parseObject.get("result").toString());
//////			 	JSONObject subObj = JSONObject.parseObject(dataObject.get("data").toString());
////			 	 
////			 	JSONArray jsonArray = JSONObject.parseArray(str);
////			 	jsonArray.stream().forEach(System.out::println);
////			 }
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
    	
    
    }
    
    /**
     * 开始1000,每天10,收益超10%重新来,亏损从不补
     * @throws Exception
     */
    @Test
    public void monitor() throws Exception{
    	String code = "260108";
    	String record = "";
    	Double account =-1000d;
    	//初始化
    	Double start = 1000d;
    	//步长
    	Double step = 10d;
    	
    	QueryWrapper<Fund> queryWrapper = new QueryWrapper<>();
    	queryWrapper.eq("fundcode", code);
    	List<Fund> list = fundMapper.selectList(queryWrapper);
    	System.out.println("monitor begin=====");
		System.out.println("begin "+start);
		
    	for(int i =0 ;i < list.size() ; i++) {
//    		System.out.println(list.get(i).getWave());
    		if(list.get(i).getWave()!=0) {
    			account=account-step;
    			start=start+step;
    			Double temp = start*list.get(i).getWave()/100;
    			System.out.println("temp "+temp);
    			//整体
    			
        		BigDecimal precnet = new BigDecimal((temp)).divide(new BigDecimal(start),3,BigDecimal.ROUND_HALF_UP);
        		start=start+temp;
        		double totalIncomePercent = (start-Math.abs(account))/Math.abs(account);
        		
        		System.out.println(list.get(i).getTimedate()+" 第"+i+"天 ,当天 "+ temp+", 当天百分比"+String.format("%.2f", precnet.doubleValue()*100)+", 剩余 "+(start)+" account "+account +" totalIncomePercent ="+String.format("%.2f", totalIncomePercent*100));
        		System.out.println(totalIncomePercent+","+(totalIncomePercent>10));
        		if(totalIncomePercent*100>10) {
        			System.out.println("一共投入"+Math.abs(account)+" 收益"+((Math.abs(start)-Math.abs(account))));
        			record+="一共投入"+Math.abs(account)+" 收益"+((Math.abs(start)-Math.abs(account)))+"..."+i+"\r\n";
        			account = -1000d;
        			start=1000d;
        			System.out.println(record + "truncate repate ...");
        			
//        			Thread.sleep(1000);
        		}
//        		Thread.sleep(800);
    		}
    		
    		
    		
    	}
    	System.out.println("monitor end====");
    }
    
    
    /**
     * 开始1000,每周四,收益超10%重新来,亏损从不补
     * @throws Exception
     */
    @Test
    public void monitorFourth() throws Exception{
    	String code = "260108";
    	String record = "";
    	Double account =-1000d;
    	//初始化
    	Double start = 1000d;
    	//步长
    	Double step = 50d;
    	
    	QueryWrapper<Fund> queryWrapper = new QueryWrapper<>();
    	queryWrapper.eq("fundcode", code);
    	List<Fund> list = fundMapper.selectList(queryWrapper);
    	System.out.println("monitor begin=====");
		System.out.println("begin "+start);
		
    	for(int i =0 ;i < list.size() ; i++) {
//    		System.out.println(list.get(i).getWave());
    		if(list.get(i).getWave()!=0) {
    			if( dayForWeek(list.get(i).getTimedate()) == "4") {
    				account=account-step;
        			start=start+step;
    			}
    			
    			Double temp = start*list.get(i).getWave()/100;
    			
    			System.out.println("temp "+temp);
    			
        		BigDecimal precnet = new BigDecimal((temp)).divide(new BigDecimal(start),3,BigDecimal.ROUND_HALF_UP);
        		start = start+temp;
        		double totalIncomePercent = (start-Math.abs(account))/Math.abs(account);
        		
        		System.out.println(list.get(i).getTimedate()+" 第"+i+"天 ,当天 "+ temp+", 当天百分比"+String.format("%.2f", precnet.doubleValue()*100)+", 剩余 "+(start)+" account "+account +" totalIncomePercent ="+String.format("%.2f", totalIncomePercent*100));
        		System.out.println(totalIncomePercent+","+(totalIncomePercent>10));
        		
        		if(totalIncomePercent * 100 > 10) {
        			System.out.println("一共投入"+Math.abs(account)+" 收益"+((Math.abs(start)-Math.abs(account))));
        			record+="一共投入"+Math.abs(account)+" 收益"+((Math.abs(start)-Math.abs(account)))+"..."+i+"\r\n";
        			account = -1000d;
        			start=1000d;
        			
        			System.out.println(record + "truncate repate ...");
        			
//        			Thread.sleep(1000);
        		}
//        		Thread.sleep(800);
    		}
    		
    		
    		
    	}
    	System.out.println("monitor end====");
    }
    
    /**
     * 模拟开始一千,每周四50 从不卖出
     * @throws Exception
     */
    @Test
    public void monitorNeverSale() throws Exception{
    	String code = "162102";
    	String record = "";
    	Double account =-1000d;
    	//初始化
    	Double start = 1000d;
    	//步长
    	Double step = 50d;
    	
    	QueryWrapper<Fund> queryWrapper = new QueryWrapper<>();
    	queryWrapper.eq("fundcode", code);
    	List<Fund> list = fundMapper.selectList(queryWrapper);
    	System.out.println("monitor begin=====");
		System.out.println("begin "+start);
		
    	for(int i =0 ;i < list.size() ; i++) {
//    		System.out.println(list.get(i).getWave());
    		if(list.get(i).getWave()!=0) {
//    			if( dayForWeek(list.get(i).getTimedate()) == "4") {
    				account=account-step;
        			start=start+step;
//    			}
    			
    			Double temp = start*list.get(i).getWave()/100;
    			
    			System.out.println("temp "+temp);
    			
        		BigDecimal precnet = new BigDecimal((temp)).divide(new BigDecimal(start),3,BigDecimal.ROUND_HALF_UP);
        		start = start+temp;
        		double totalIncomePercent = (start-Math.abs(account))/Math.abs(account);
        		
        		System.out.println(list.get(i).getTimedate()+" 第"+i+"天 ,当天 "+ temp+", 当天百分比"+String.format("%.2f", precnet.doubleValue()*100)+", 剩余 "+(start)+" account "+account +" totalIncomePercent ="+String.format("%.2f", totalIncomePercent*100));
        		System.out.println(totalIncomePercent+","+(totalIncomePercent>10));
        		
//        		if(totalIncomePercent * 100 > 10) {
//        			System.out.println("一共投入"+Math.abs(account)+" 收益"+((Math.abs(start)-Math.abs(account))));
//        			record+="一共投入"+Math.abs(account)+" 收益"+((Math.abs(start)-Math.abs(account)))+"..."+i+"\r\n";
//        			account = -1000d;
//        			start=1000d;
//        			
//        			System.out.println(record + "truncate repate ...");
//        			
////        			Thread.sleep(1000);
//        		}
//        		Thread.sleep(800);
    		}
    		
    		
    		
    	}
    	System.out.println("monitor end====");
    }
    
    
    /**
     * 模拟开始一千,每周四50 从不卖出,每五年拿出4分之一
     * @throws Exception
     */
    @Test
    public void monitorNeverSalePertenYearsTakeSome() throws Exception{
    	String code = "162605";
    	String record = "";
    	Double account =-10000d;
    	//初始化
    	Double start = 10000d;
    	//步长
    	Double step = 50d;
    	
    	QueryWrapper<Fund> queryWrapper = new QueryWrapper<>();
    	queryWrapper.eq("fundcode", code);
    	List<Fund> list = fundMapper.selectList(queryWrapper);
    	
    	System.out.println("monitor begin=====");
		System.out.println("begin "+start);
		
		Random random = new Random(4);
    	for(int i =0 ;i < list.size() ; i++) {
//    		System.out.println(list.get(i).getWave());
    		if(list.get(i).getWave()!=0) {
//    			if( dayForWeek(list.get(i).getTimedate()) == "4") {
    				account=account-step;
        			start=start+step;
//    			}
        		if(random.nextInt(100)%6 ==0) {
        			account=account-100;
        			start=start+100;
        			System.out.println("####加仓100");
        		}
        		
    			
    			Double temp = start*list.get(i).getWave()/100;
    			
    			System.out.println("temp "+temp);
    			
        		BigDecimal precnet = new BigDecimal((temp)).divide(new BigDecimal(start),3,BigDecimal.ROUND_HALF_UP);
        		start = start+temp;
        		double totalIncomePercent = (start-Math.abs(account))/Math.abs(account);
        		
        		
        		if(i%1200==0) {
        			Double tempDouble = start /Double.valueOf(4) ;
        			System.out.println("take====="+tempDouble);
        			start =start-tempDouble;
        		}
        		
        		System.out.println(list.get(i).getTimedate()+" 第"+i+"天 ,当天 "+ temp+", 当天百分比"+String.format("%.2f", precnet.doubleValue()*100)+", 剩余 "+(start)+" account "+account +" totalIncomePercent ="+String.format("%.2f", totalIncomePercent*100));
        		System.out.println(totalIncomePercent+","+(totalIncomePercent>10));
        		
//        		if(totalIncomePercent * 100 > 10) {
//        			System.out.println("一共投入"+Math.abs(account)+" 收益"+((Math.abs(start)-Math.abs(account))));
//        			record+="一共投入"+Math.abs(account)+" 收益"+((Math.abs(start)-Math.abs(account)))+"..."+i+"\r\n";
//        			account = -1000d;
//        			start=1000d;
//        			
//        			System.out.println(record + "truncate repate ...");
//        			
////        			Thread.sleep(1000);
//        		}
//        		Thread.sleep(800);
    		}
    		
    		
    		
    	}
    	System.out.println("monitor end====");
    }
    
    /**
     * 在下降20%买,上涨20卖
     * @throws Exception
     */
    @Test
    public void monitorNeverSalePertenYearsLowpuchaseHighsaleTakeSome() throws Exception{
    	String code = "004205";
    	String record = "";
    	Double account =-1000d;
    	//初始化
    	Double start = 1000d;
    	//步长
    	Double step = 50d;
    	
    	QueryWrapper<Fund> queryWrapper = new QueryWrapper<>();
    	queryWrapper.eq("fundcode", code);
    	List<Fund> list = fundMapper.selectList(queryWrapper);
    	System.out.println("monitor begin=====");
		System.out.println("begin "+start);
		
    	for(int i =0 ;i < list.size() ; i++) {
//    		System.out.println(list.get(i).getWave());
    		if(list.get(i).getWave()!=0) {
//    			if( dayForWeek(list.get(i).getTimedate()) == "4") {
    				account=account-step;
        			start=start+step;
//    			}
    			
    			Double temp = start*list.get(i).getWave()/100;
    			
    			System.out.println("temp "+temp);
    			
        		BigDecimal precnet = new BigDecimal((temp)).divide(new BigDecimal(start),3,BigDecimal.ROUND_HALF_UP);
        		start = start+temp;
        		double totalIncomePercent = (start-Math.abs(account))/Math.abs(account);
        		
        		
        		if(i%1200==0) {
        			Double tempDouble = start /Double.valueOf(4) ;
        			System.out.println("take====="+tempDouble);
        			start =start-tempDouble;
        		}
        		
        		System.out.println(list.get(i).getTimedate()+" 第"+i+"天 ,当天 "+ temp+", 当天百分比"+String.format("%.2f", precnet.doubleValue()*100)+", 剩余 "+(start)+" account "+account +" totalIncomePercent ="+String.format("%.2f", totalIncomePercent*100));
        		System.out.println(totalIncomePercent+","+(totalIncomePercent>10));
        		
//        		if(totalIncomePercent * 100 > 10) {
//        			System.out.println("一共投入"+Math.abs(account)+" 收益"+((Math.abs(start)-Math.abs(account))));
//        			record+="一共投入"+Math.abs(account)+" 收益"+((Math.abs(start)-Math.abs(account)))+"..."+i+"\r\n";
//        			account = -1000d;
//        			start=1000d;
//        			
//        			System.out.println(record + "truncate repate ...");
//        			
////        			Thread.sleep(1000);
//        		}
//        		Thread.sleep(800);
    		}
    		
    		
    		
    	}
    	System.out.println("monitor end====");
    }
    
    
    class call implements Callable<String>{
    	
    	
    	public call() {
    		
    	}
    	
    	 List<Fund> list;
    	 String code;
    	public call(List<Fund> v,String code) {
    		this.list = v;
    		this.code = code;
    	}

		@Override
		public String call() throws Exception {
//			String code = code;
        	Double account =-1000d;
        	//初始化
        	Double start = 1000d;
        	//步长
        	Double step = 50d;
        	
//        	QueryWrapper<Fund> queryWrapper = new QueryWrapper<>();
//        	queryWrapper.eq("fundcode", code);
//        	List<Fund> list = fundMapper.selectList(queryWrapper);
        	System.out.println("monitor begin=====");
    		System.out.println("begin "+start);
    		boolean flag = false;
    		boolean myflag = false;
    		
    		String fileName = "";
    		StringBuilder builder = new StringBuilder();
    		int reset =0;
    		
        	for(int i =0 ;i < list.size() ; i ++) {
        		if(i == 0) {
        			fileName = list.get(i).getFundcode();
        		}
//        		System.out.println(list.get(i).getWave());
        		if(list.get(i).getWave()!=0) {
//        			if( dayForWeek(list.get(i).getTimedate()) == "4") {
        				account = account - step;
            			start = start + step;
//        			}
        			
        			Double temp = start * list.get(i).getWave()/100;
        			
        			builder.append("temp "+temp+"\r\n");
        			
            		BigDecimal precnet = new BigDecimal((temp)).divide(new BigDecimal(start),3,BigDecimal.ROUND_HALF_UP);
            		start = start+temp;
            		double totalIncomePercent = (start-Math.abs(account))/Math.abs(account);
            		
            		
            		if(i%1200 == 0) {
            			Double tempDouble = start / Double.valueOf(4) ;
            			builder.append("take====="+tempDouble +" \r\n");
            			start =start-tempDouble;
            			flag = true;
            			reset = i;
            		}
            		
            		builder.append(list.get(i).getTimedate()+" 第"+i+"天 ,当天 "+ temp+", 当天百分比"+String.format("%.2f", precnet.doubleValue()*100)+", 剩余 "+(start)+" account "+account +" totalIncomePercent ="+String.format("%.2f", totalIncomePercent*100) +"\r\n");
//            		System.out.println(totalIncomePercent+","+(totalIncomePercent>10));
            		
            		
            		if(totalIncomePercent*100 < -20 && flag && i-reset >10) {
            			account = account - 10000;
            			start = start + 10000;
            			flag = false;
            			myflag =  true;
            		}
            		
            		if(totalIncomePercent*100>20 && myflag) {
            			start =start-14000;
            			builder.append("take===== "+14000+" \r\n");
            			myflag = false;
            		}
            		
            		

        		}
//			return builder.toString();
		}
			return builder.toString();
    	
      }
    }
    
    @Test
    public void monitorAll() throws Exception{
    	
//    	ArrayBlockingQueue<Map<String, Future<String>>> queue = new ArrayBlockingQueue<Map<String, Future<String>>>(20000);
//    	
//    	List<String> codeList = fundMapper.selectDistinctFundCodeList();
//    	
//    	
//    	Map<String, List<Fund>> fundlistMap = new HashMap<String, List<Fund>>();
//    	codeList.forEach(e->{
//    		QueryWrapper<Fund> queryWrapper = new QueryWrapper<>();
//        	queryWrapper.eq("fundcode", e);
//        	List<Fund> list = fundMapper.selectList(queryWrapper);
//        	fundlistMap.put(e, list);
//        	System.out.println("sql query end "+e);
//    	});
//     	
//    	
//    	
//    	
//new Thread(()->{
//    		System.out.println("启动消费...");
//    		while(true) {
//	       		 try {
//	       			
//	       		 
//	       		    Map<String, Future<String>> map = queue.poll();
//	       		    if(map!=null) {
//	       		    	Set<String> keySet = map.keySet();
//		       			for(String s : keySet) {
//		       				
//		       				
//		       				if(map.get(s).isDone()) {
//		       					BufferedWriter out = new BufferedWriter(new FileWriter("d:/log/"+s+".txt"));
//		    	  	            try {
//									out.write(map.get(s).get(1,TimeUnit.SECONDS));
//								} catch (TimeoutException e1) {
//								
//									e1.printStackTrace();
//								}
//		    	  	            out.close();
//		    	  	            System.out.println(s + "文件创建成功！");
//		       				}else {
//		       				
//		       					System.out.println(s+"被重新放回队列");
//		       					queue.put(map);
//		       				}
//		       				
//		       			}
//	       		    }
//	       			
//	       			
//	       			if(queue.size()>0) {
//	       				System.out.println("queue.szie = "+queue.size());
//	       			}
//	       		
//	 	           
//	 	        } catch (IOException | InterruptedException | ExecutionException e1) {
//	 	        	e1.printStackTrace();
//	 	        }
//    		}
//    	}).start();
//
//    	codeList.stream().forEach(e->{
//        	
//    		 ExecutorService threadPool = Executors.newFixedThreadPool(10);
//    	        Future<String> future = threadPool.submit(new call(fundlistMap.get(e), e));
//        		
//        		
//
//    		try {
//    			System.out.println("放入一个 "+ e);
//    			Map<String, Future<String>> map = new HashMap<String, Future<String>>();
//    			map.put(e, future);
//				queue.put(map);
//			} catch (InterruptedException e1) {
//				e1.printStackTrace();
//			}
////    		 try {
//// 	            BufferedWriter out = new BufferedWriter(new FileWriter("d:/log/"+fileName+".txt"));
//// 	            out.write(builder.toString());
//// 	            out.close();
//// 	            System.out.println("文件创建成功！");
//// 	        } catch (IOException e1) {
//// 	        	e1.printStackTrace();
//// 	        }
//        	System.out.println("monitor end====");
//    	});
//    	
    	
    	

    }
    
    
    @Test
    public void monitorAllFingleThread() throws Exception{
    	
    	ArrayBlockingQueue<Map<String, Future<String>>> queue = new ArrayBlockingQueue<Map<String, Future<String>>>(20000);
    	
//    	List<String> codeList = fundMapper.selectDistinctFundCodeList();
    	List<String> codeList = new ArrayList<String>();
    	
    	codeList.add("020003");
    	
    	Map<String, List<Fund>> fundlistMap = new HashMap<String, List<Fund>>();
    	codeList.forEach(e->{
    		QueryWrapper<Fund> queryWrapper = new QueryWrapper<>();
        	queryWrapper.eq("fundcode", e);
        	List<Fund> list = fundMapper.selectList(queryWrapper);

//			String code = code;
        	Double account = -1000d;
        	//初始化
        	Double start = 1000d;
        	//步长
        	Double step = 50d;
        	
//        	QueryWrapper<Fund> queryWrapper = new QueryWrapper<>();
//        	queryWrapper.eq("fundcode", code);
//        	List<Fund> list = fundMapper.selectList(queryWrapper);
        	System.out.println("monitor begin=====");
    		System.out.println("begin "+start);
    		boolean flag = false;
    		boolean myflag = false;
    		
    		String fileName = "";
    		StringBuilder builder = new StringBuilder();
    		int reset =0;
    		
        	for(int i =0 ;i < list.size() ; i ++) {
        		if(i == 0) {
        			fileName = list.get(i).getFundcode();
        		}
//        		System.out.println(list.get(i).getWave());
        		if(list.get(i).getWave()!=0) {
//        			if( dayForWeek(list.get(i).getTimedate()) == "4") {
        				account = account - step;
            			start = start + step;
//        			}
        			
        			Double temp = start * list.get(i).getWave()/100;
        			
        			builder.append("temp "+temp+"\r\n");
        			
            		BigDecimal precnet = new BigDecimal((temp)).divide(new BigDecimal(start),3,BigDecimal.ROUND_HALF_UP);
            		start = start+temp;
            		double totalIncomePercent = (start-Math.abs(account))/Math.abs(account);
            		
            		
            		if(i%1200 == 0) {
            			Double tempDouble = start / Double.valueOf(4) ;
            			builder.append("take====="+tempDouble +" \r\n");
            			start =start-tempDouble;
            			flag = true;
            			reset = i;
            		}
            		
            		builder.append(list.get(i).getTimedate()+" 第"+i+"天 ,当天 "+ temp+", 当天百分比"+String.format("%.2f", precnet.doubleValue()*100)+", 剩余 "+(start)+" account "+account +" totalIncomePercent ="+String.format("%.2f", totalIncomePercent*100) +"\r\n");
//            		System.out.println(totalIncomePercent+","+(totalIncomePercent>10));
            		
            		
            		if(totalIncomePercent*100 < -20 && flag && i-reset >10) {
            			account = account - 10000;
            			start = start + 10000;
            			flag = false;
            			myflag =  true;
            		}
            		
            		if(totalIncomePercent*100>20 && myflag) {
            			start =start-14000;
            			builder.append("take===== "+14000+" \r\n");
            			myflag = false;
            		}
            		
            		

        		}
//			return builder.toString();
		}
//			return builder.toString();
        	System.out.println(builder.toString());
//        	try {
//				BufferedWriter out = new BufferedWriter(new FileWriter("d:/log/"+e+".txt"));
//				out.write(builder.toString());
//			
//			    out.close();
//				    System.out.println(e + "文件创建成功！");
//			} catch (IOException e1) {
//				// TODO Auto-generated catch block
//				e1.printStackTrace();
//			}
        	
    	});
     	
    	
    	
        	System.out.println("monitor end====");
    	
    	
    	

    }
    
    
    
    
    public static void main(String[] args)throws Exception {
//    	double d = 10.78d;
//    	System.out.println(d>10);
////		BigDecimal a = new BigDecimal(-7);
////		BigDecimal b = new BigDecimal(1010);
////		 System.out.println(a.divide(b,3,BigDecimal.ROUND_HALF_UP));
//    	  String a = dayForWeek("2017-09-23");
//
//          System.out.println(a);
//          
//          Random random = new Random(100);
//          int count =0;
//          for(int i =0 ; i< 100;i++) {
//        	  int nextInt = random.nextInt(100);
//        	  System.out.println("!!!!"+nextInt);
//        	  if(nextInt%9==0) {
//        		  count++;
//        	  }
//                  
//
//          }
//          System.out.println(count);
    	
    	
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
    	            	if(s.startsWith("take")) {
    	            		System.out.println(s);
    	            		sBuilder.append(s+"\r\n");
    	            	}
//    	                result.append( System.lineSeparator() + s);
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
    	       System.out.println( readLastLine(file,"utf8") );
    	       String lastLine =  readLastLine(file,"utf8");
    	       String[] laststr = lastLine.split(" ");
    	       if(laststr.length != 11) {
    	    	   continue;
    	       }
    	       sd.setPerDAy(laststr[1]);
    	       
    	       sd.setAccount(laststr[8]);
    	       sd.setReMain(laststr[6]);
    	       sd.setTotalIncomePercent(laststr[10]);
//    	       result.append(readLastLine(file,"utf8") +"\r\n");
    	       targetDatas.add(sd);
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
    
    
    public static String readLastLine(File file, String charset) throws IOException {  
      if (!file.exists() || file.isDirectory() || !file.canRead()) {  
        return null;  
      }  
      RandomAccessFile raf = null;  
      try {  
        raf = new RandomAccessFile(file, "r");  
        long len = raf.length();  
        if (len == 0L) {  
          return "";  
        } else {  
          long pos = len - 1;  
          while (pos > 0) {  
            pos--;  
            raf.seek(pos);  
            if (raf.readByte() == '\n') {  
              break;  
            }  
          }  
          if (pos == 0) {  
            raf.seek(0);  
          }  
          byte[] bytes = new byte[(int) (len - pos)];  
          raf.read(bytes);  
          if (charset == null) {  
            return new String(bytes);  
          } else {  
            return new String(bytes, charset);  
          }  
        }  
      } catch (FileNotFoundException e) {  
      } finally {  
        if (raf != null) {  
          try {  
            raf.close();  
          } catch (Exception e2) {  
          }  
        }  
      }  
      return null;  
    } 

    
    
    public static String dayForWeek(String pTime) throws Exception {  

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");  
        Date tmpDate = format.parse(pTime);  
        Calendar cal = Calendar.getInstance(); 
        String[] weekDays = { "7", "1", "2", "3", "4", "5", "6" };

        try {
            cal.setTime(tmpDate);
        } catch (Exception e) {
            e.printStackTrace();
        }

        int w = cal.get(Calendar.DAY_OF_WEEK) - 1; // 指示一个星期中的某天。
        if (w < 0)
            w = 0;
        return weekDays[w];

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
}

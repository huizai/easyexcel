package com.zwj.easyexcel.controller;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.alibaba.excel.write.metadata.WriteSheet;
import com.zwj.mapper.FundMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @Author: zhengwj
 * @Description:    特殊号码导入监听类
 * @Date: 2020/4/1 16:30
 * @Version: 1.0
 */
// 不能被spring管理
public class StatisticListener extends AnalysisEventListener<StatisticsData> {

    private static final Logger logger = LoggerFactory.getLogger(StatisticListener.class);

    private static final int BATCH_COUNT = 10000;

    private String scan;

    private  List<Map<String,String>> mapList;
    List<StatisticsData> list = new ArrayList<>();

//    private ConfigFilterDao configFilterDao;
    private FundMapper fundMapper;
    public StatisticListener(List<Map<String,String>> list,FundMapper fundMapper){
        this.mapList = list;
        this.fundMapper = fundMapper;
    }


    @Override
    public void invoke(StatisticsData d, AnalysisContext analysisContext) {
//        System.out.println(configFilter.getJCode().substring(1));
        list.add(d);

    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {
        // 这里也要保存数据，确保最后遗留的数据也存储到数据库
        saveData();
//        logger.info("所有数据解析完成！");
    }

    /**
     * 加上存储数据库
     */
    private void saveData() {
        logger.info("{}条数据，", list.size());
        Set<String> target = new HashSet<>();
//        for (Data data : list) {
//            if(data.getStockCode().contains(scan)){
//                target.add(data.getJCode());
//            }
//        }
//        configFilterDao.save(list);
        Map<String,String> map = new HashMap<>();
        for (int i = 0; i < mapList.size(); i++) {
            Map<String, String> sub = mapList.get(i);
            map.put(sub.get("fundcode"),sub.get("fundname"));
        }
        List<StatisticsDataNew> collect = list.stream().map(e -> {
            StatisticsDataNew dataNew = new StatisticsDataNew();
            dataNew.setAccount(e.getAccount());

            dataNew.setFundname(map.get(e.getJCode().replaceAll(".txt","")));
            dataNew.setJCode(e.getJCode());
            dataNew.setTake1(e.getTake1());
            dataNew.setTake2(e.getTake2());
            dataNew.setTake3(e.getTake3());
            dataNew.setPerDAy(e.getPerDAy());
            dataNew.setReMain(e.getReMain());
            dataNew.setTotalIncomePercent(e.getTotalIncomePercent());
            return dataNew;
        }).collect(Collectors.toList());

        ExcelWriter excelWriter = EasyExcel.write("d:/s/s11.xlsx", StatisticsDataNew.class).build();
        WriteSheet writeSheet = EasyExcel.writerSheet("data").build();
        excelWriter.write(collect, writeSheet);
        /// 千万别忘记finish 会帮忙关闭流
        excelWriter.finish();
        //        EasyExcel.read(file, Fund.class, new StatisticListener(list,fundMapper)).sheet().doRead();
        logger.info("分析完成 持有scan {} 共有 {}",scan,target.toArray());
    }
}

package com.zwj.easyexcel.controller;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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

    List<StatisticsData> list = new ArrayList<>();

//    private ConfigFilterDao configFilterDao;
    public StatisticListener(String scan){
        this.scan = scan;
    }


    @Override
    public void invoke(StatisticsData configFilter, AnalysisContext analysisContext) {
//        System.out.println(configFilter.getJCode().substring(1));
        list.add(configFilter);

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
        logger.info("分析完成 持有scan {} 共有 {}",scan,target.toArray());
    }
}

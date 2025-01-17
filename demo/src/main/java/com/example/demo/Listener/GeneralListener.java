package com.example.demo.Listener;
import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.example.demo.pojo.Score;
import org.apache.commons.compress.utils.Lists;

import java.util.ArrayList;
import java.util.List;

public class GeneralListener<T> extends AnalysisEventListener<T> {
    private List<T> list = Lists.newArrayList();
    private List<String> errorMessages = new ArrayList<>();
    @Override
    public void invoke(T t, AnalysisContext analysisContext) {
        if (t instanceof Score) {
            Score score = (Score) t;
            if (score.getScore() < 0 || score.getScore() > 100) {
                errorMessages.add("第" + (analysisContext.readRowHolder().getRowIndex()+1) + " 行");
                return; // 跳过非法数据
            }
        }
        list.add(t);
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {
        System.out.println("read data complete!");
    }
    public List<T> getList(){
        return list;
    }

    public List<String> getErrorMessages() {
        return errorMessages;
    }
}

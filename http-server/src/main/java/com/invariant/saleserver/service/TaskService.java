package com.invariant.saleserver.service;

import com.invariant.devices.posiflex.printer.Printer;
import com.invariant.saleserver.service.task.CheckData;
import com.invariant.saleserver.service.task.PrintCheckResult;
import com.invariant.saleserver.service.task.PrintCheckTask;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 *
 */
@Service()
@Scope("singleton")
@Slf4j
public class TaskService {

    /**
     * Количество потоков выполнения.
     */
    public static final int THREAD_COUNT = 1;

    private ExecutorService executorService;

    private Map<String, CheckData> printedChecks = new HashMap<>();

    public Future<PrintCheckResult> printCheck(CheckData checkData, Printer printer){
        PrintCheckTask task = new PrintCheckTask(checkData, printer);
        String taskId = addCheck(checkData);
        task.setTaskId(taskId);
        return executorService.submit(task);
    }

    public Map<String, CheckData> getPrintedChecks(){
        return printedChecks;
    }

    private String addCheck(CheckData checkData){
        String timestamp = String.valueOf(new Date().getTime());
        printedChecks.put(timestamp, checkData);
        return timestamp;
    }

    @PostConstruct
    private void init(){
        executorService = Executors.newFixedThreadPool(THREAD_COUNT);
    }

}

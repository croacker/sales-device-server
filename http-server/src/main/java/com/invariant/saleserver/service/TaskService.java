package com.invariant.saleserver.service;

import com.invariant.devices.posiflex.printer.Printer;
import com.invariant.saleserver.service.task.CheckData;
import com.invariant.saleserver.service.task.PrintCheckResult;
import com.invariant.saleserver.service.task.PrintCheckTask;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
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

    public Future<PrintCheckResult> printCheck(CheckData checkData, Printer printer){
        PrintCheckTask task = new PrintCheckTask(checkData, printer);
        return executorService.submit(task);
    }

    @PostConstruct
    private void init(){
        executorService = Executors.newFixedThreadPool(THREAD_COUNT);
    }

}

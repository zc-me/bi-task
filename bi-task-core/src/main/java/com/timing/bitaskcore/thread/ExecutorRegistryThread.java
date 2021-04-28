package com.timing.bitaskcore.thread;

import com.timing.bitaskcore.biz.AdminBiz;
import com.timing.bitaskcore.biz.model.RegistryParam;
import com.timing.bitaskcore.biz.model.ReturnT;
import com.timing.bitaskcore.enums.RegistryConfig;
import com.timing.bitaskcore.executor.XxlJobExecutor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.TimeUnit;

public class ExecutorRegistryThread {
    private static Logger logger = LoggerFactory.getLogger(ExecutorRegistryThread.class);

    private static ExecutorRegistryThread instance = new ExecutorRegistryThread();
    public static ExecutorRegistryThread getInstance(){
        return instance;
    }

    private Thread registryThread;
    private volatile boolean toStop = false;
    public void start(final String appname, final String address){

        // valid
        if (appname==null || appname.trim().length()==0) {
            logger.warn(">>>>>>>>>>> bi-task, executor registry config fail, appname is null.");
            return;
        }
        if (XxlJobExecutor.getAdminBizList() == null) {
            logger.warn(">>>>>>>>>>> bi-task, executor registry config fail, adminAddresses is null.");
            return;
        }

        registryThread = new Thread(new Runnable() {
            @Override
            public void run() {

                // registry
                while (!toStop) {
                    try {
                        RegistryParam registryParam = new RegistryParam(RegistryConfig.RegistType.EXECUTOR.name(), appname, address);
                        for (AdminBiz adminBiz: XxlJobExecutor.getAdminBizList()) {
                            try {
                                ReturnT<String> registryResult = adminBiz.registry(registryParam);
                                if (registryResult!=null && ReturnT.SUCCESS_CODE == registryResult.getCode()) {
                                    registryResult = ReturnT.SUCCESS;
                                    logger.debug(">>>>>>>>>>> bi-task registry success, registryParam:{}, registryResult:{}", new Object[]{registryParam, registryResult});
                                    break;
                                } else {
                                    logger.info(">>>>>>>>>>> bi-task registry fail, registryParam:{}, registryResult:{}", new Object[]{registryParam, registryResult});
                                }
                            } catch (Exception e) {
                                logger.info(">>>>>>>>>>> bi-task registry error, registryParam:{}", registryParam, e);
                            }

                        }
                    } catch (Exception e) {
                        if (!toStop) {
                            logger.error(e.getMessage(), e);
                        }

                    }

                    try {
                        if (!toStop) {
                            TimeUnit.SECONDS.sleep(RegistryConfig.BEAT_TIMEOUT);
                        }
                    } catch (InterruptedException e) {
                        if (!toStop) {
                            logger.warn(">>>>>>>>>>> bi-task, executor registry thread interrupted, error msg:{}", e.getMessage());
                        }
                    }
                }

                // registry remove
                try {
                    RegistryParam registryParam = new RegistryParam(RegistryConfig.RegistType.EXECUTOR.name(), appname, address);
                    for (AdminBiz adminBiz: XxlJobExecutor.getAdminBizList()) {
                        try {
                            ReturnT<String> registryResult = adminBiz.registryRemove(registryParam);
                            if (registryResult!=null && ReturnT.SUCCESS_CODE == registryResult.getCode()) {
                                registryResult = ReturnT.SUCCESS;
                                logger.info(">>>>>>>>>>> bi-task registry-remove success, registryParam:{}, registryResult:{}", new Object[]{registryParam, registryResult});
                                break;
                            } else {
                                logger.info(">>>>>>>>>>> bi-task registry-remove fail, registryParam:{}, registryResult:{}", new Object[]{registryParam, registryResult});
                            }
                        } catch (Exception e) {
                            if (!toStop) {
                                logger.info(">>>>>>>>>>> bi-task registry-remove error, registryParam:{}", registryParam, e);
                            }

                        }

                    }
                } catch (Exception e) {
                    if (!toStop) {
                        logger.error(e.getMessage(), e);
                    }
                }
                logger.info(">>>>>>>>>>> bi-task, executor registry thread destory.");

            }
        });
        registryThread.setDaemon(true);
        registryThread.setName("xxl-job, executor ExecutorRegistryThread");
        registryThread.start();
    }

    public void toStop() {
        toStop = true;

        // interrupt and wait
        if (registryThread != null) {
            registryThread.interrupt();
            try {
                registryThread.join();
            } catch (InterruptedException e) {
                logger.error(e.getMessage(), e);
            }
        }

    }

}

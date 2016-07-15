
package com.sean.study.log4j;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @PURPOSE:
 * @AUTHOR:zhenbin.guo
 * @DATE:2014年8月4日
 */

public class Log4jTest {
    
    @Test
    public void testAndroidMonitor(){
        Logger logger = LoggerFactory.getLogger("AndroidMonitor");
        logger.info("测试");
    }
    @Test
    public void testLogger(){
        Logger logger = LoggerFactory.getLogger(Log4jTest.class);
        logger.info("rule [{}] tet [{}]", 1,"test");
    }

}

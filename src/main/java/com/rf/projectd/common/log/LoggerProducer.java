/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rf.projectd.common.log;

import java.lang.reflect.Member;
import javax.enterprise.inject.Produces;
import javax.enterprise.inject.spi.Bean;
import javax.enterprise.inject.spi.InjectionPoint;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Producer for the logger.
 *
 * @author raz
 */
public class LoggerProducer {

    @Produces
    @Log
    public Logger produceLogger(InjectionPoint injectionPoint) {
        final Bean<?> bean = injectionPoint.getBean();
        if (bean != null) {
            return LogManager.getLogger(bean.getBeanClass());
        }
        final Member member = injectionPoint.getMember();
        if (member != null) {
            return LogManager.getLogger(member.getDeclaringClass());
        }
        return LogManager.getLogger(this.getClass());
    }
}

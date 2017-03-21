/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rf.projectd.common.config;

import java.lang.annotation.Documented;
import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.ElementType.PARAMETER;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import javax.enterprise.util.Nonbinding;
import javax.inject.Qualifier;

/**
 * Annotation for injecting properties from the properties files.
 * @author raz
 */
@Qualifier
@Documented
@Retention(value = RetentionPolicy.RUNTIME)
@Target({FIELD, PARAMETER, METHOD})
public @interface Prop {
    @Nonbinding public String value() default "";
}

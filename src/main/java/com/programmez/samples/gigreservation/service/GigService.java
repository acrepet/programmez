/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.programmez.samples.gigreservation.service;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

/**
 *
 * @author agnes007
 */
@Service
@Scope(BeanDefinition.SCOPE_SINGLETON)
@Retention(RetentionPolicy.RUNTIME)
public @interface GigService {
}

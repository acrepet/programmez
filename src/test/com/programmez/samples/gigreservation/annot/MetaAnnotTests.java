/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.programmez.samples.gigreservation.annot;

import com.programmez.samples.gigreservation.service.internal.simple.SimpleGigService;
import com.programmez.samples.gigreservation.service.GigService;
import org.springframework.context.ApplicationContext;
import java.lang.annotation.Annotation;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *article-service-config.xml
 * @author agnes007
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations="classpath:com/programmez/samples/gigreservation/config/xml/gig-simple-service-config.xml")
public class MetaAnnotTests {

	@Autowired
	private ApplicationContext context;



	@Test
	public void testOnlyPrototypeServiceAnnotation() {
		SimpleGigService service1 = context.getBean(SimpleGigService.class);
		Annotation[] annots = service1.getClass().getDeclaredAnnotations();
		assertEquals(1, annots.length);
		assertEquals(GigService.class, annots[0].annotationType());
	}

	@Test
	public void testScopePrototype() {
		SimpleGigService service1 = context.getBean(SimpleGigService.class);
		SimpleGigService service2 = context.getBean(SimpleGigService.class);
		
		assertSame(service1, service2);
	}
}
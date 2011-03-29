/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.programmez.samples.article;

import org.springframework.context.ApplicationContext;
import com.programmez.samples.article.service.PrototypeService;
import com.programmez.samples.article.service.internal.ArticleService;
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
@ContextConfiguration(locations="classpath:com/programmez/samples/article/article-service-config.xml")
public class MetaAnnotTest {

	@Autowired
	private ApplicationContext context;



	@Test
	public void testOnlyPrototypeServiceAnnotation() {
		ArticleService service1 = context.getBean(ArticleService.class);
		Annotation[] annots = service1.getClass().getDeclaredAnnotations();
		assertEquals(1, annots.length);
		assertEquals(PrototypeService.class, annots[0].annotationType());
	}

	@Test
	public void testScopePrototype() {
		ArticleService service1 = context.getBean(ArticleService.class);
		ArticleService service2 = context.getBean(ArticleService.class);
		
		assertNotSame(service1, service2);
	}
}
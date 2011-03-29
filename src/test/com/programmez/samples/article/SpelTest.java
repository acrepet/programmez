/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.programmez.samples.article;

import org.junit.Ignore;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import com.programmez.samples.article.service.internal.ArticleService;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.expression.BeanFactoryResolver;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.SpelEvaluationException;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author agnes007
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations="classpath:com/programmez/samples/article/article-service-config.xml")
public class SpelTest {

	@Value("#{systemProperties['user.country'].toLowerCase()}")
	private String locale;

	@Autowired
	private ApplicationContext applicationContext;

	@Test @Ignore
	public void testLocaleResolutionInjection() {
		assertEquals(Locale.ENGLISH.getLanguage(), locale);
	}

	@Test
	public void testMap() {
		Map<String, String> info = new HashMap<String, String>();
		info.put("article1", "nouveautés-spring-3.0");
		info.put("article2", "nouveautés-spring-3.1");
		InfoArticle infoArticle = new InfoArticle(info);

		ExpressionParser parser = new SpelExpressionParser();
		StandardEvaluationContext context = new StandardEvaluationContext(infoArticle);
		String description = parser.parseExpression("info['article1']").getValue(context, String.class);
		assertEquals("nouveautés-spring-3.0", description);
	}

	@Test
	public void testReferenceAUnBean() {
		ExpressionParser parser = new SpelExpressionParser();
		StandardEvaluationContext context = new StandardEvaluationContext();
		context.setBeanResolver(new BeanFactoryResolver(applicationContext));

		ArticleService service = (ArticleService) parser.parseExpression("@articleService").getValue(context);
		assertNotNull(service);
		//assertEquals("Spring-3.0-news", service.getArticleTitle());
	}

	@Test(expected = SpelEvaluationException.class)
	public void testNavigationNullPointerException() {
		InfoArticle infoArticle = new InfoArticle(); // le titre est null

		ExpressionParser parser = new SpelExpressionParser();
		StandardEvaluationContext context = new StandardEvaluationContext(infoArticle);
		String titre = parser.parseExpression("info.titre").getValue(context, String.class);
		assertNull(titre);
	}

	@Test()
	public void testNavigationSure() {
		InfoArticle infoArticle = new InfoArticle(); // le titre est null

		ExpressionParser parser = new SpelExpressionParser();
		StandardEvaluationContext context = new StandardEvaluationContext(infoArticle);
		String titre = parser.parseExpression("info?.titre").getValue(context, String.class);
		assertNull(titre);
	}

	class InfoArticle {
		private Map<String, String> info;
		private String titre = null;
		public InfoArticle(){};
		public InfoArticle(Map<String, String> info) {
			this.info = info;
		}
		public Map<String, String> getInfo() {
			return info;
		}
	}
}
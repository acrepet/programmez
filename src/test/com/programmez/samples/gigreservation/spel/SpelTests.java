/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.programmez.samples.gigreservation.spel;

import org.springframework.beans.factory.annotation.Autowired;
import com.programmez.samples.gigreservation.service.internal.simple.SimpleGigService;
import org.springframework.context.ApplicationContext;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import java.util.Locale;

import java.util.HashMap;
import java.util.Map;
import org.springframework.beans.factory.annotation.Value;
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
@ContextConfiguration(locations="classpath:com/programmez/samples/gigreservation/config/xml/gig-simple-service-config.xml")
public class SpelTests {

    @Value("#{systemProperties['user.country'].toLowerCase()}")
    private String locale;

    @Autowired
    private ApplicationContext applicationContext;


    @Test
    public void testLocaleResolutionInjection() {
        assertEquals(Locale.US.getCountry().toLowerCase(), locale);
    }

    @Test
    public void testMap() {
        Map<String, String> info = new HashMap<String, String>();
        info.put("concert1", "arcade fire");
        info.put("concert2", "fugazi");
        InfoConcert infoArticle = new InfoConcert(info);

        ExpressionParser parser = new SpelExpressionParser();
        StandardEvaluationContext context = new StandardEvaluationContext(infoArticle);
        String description = parser.parseExpression("info['concert1']").getValue(context, String.class);
        assertEquals("arcade fire", description);
    }

    @Test
    public void testReferenceAUnBean() {
        ExpressionParser parser = new SpelExpressionParser();
        StandardEvaluationContext context = new StandardEvaluationContext();
        context.setBeanResolver(new BeanFactoryResolver(applicationContext));

        SimpleGigService service = (SimpleGigService) parser.parseExpression("@simpleGigService").getValue(context);
        assertNotNull(service);
        //assertEquals("Spring-3.0-news", service.getArticleTitle());
    }

    @Test(expected = SpelEvaluationException.class)
    public void testNavigationNullPointerException() {
        InfoConcert infoConcert = new InfoConcert(); // le groupe est null
        ExpressionParser parser = new SpelExpressionParser();
        StandardEvaluationContext context = new StandardEvaluationContext(infoConcert);
        String groupe = parser.parseExpression("info.groupe").getValue(context, String.class);
        assertNull(groupe);
    }

    @Test()
    public void testNavigationSure() {
        InfoConcert infoConcert = new InfoConcert(); // le groupe est null

        ExpressionParser parser = new SpelExpressionParser();
        StandardEvaluationContext context = new StandardEvaluationContext(infoConcert);
        String titre = parser.parseExpression("info?.groupe").getValue(context, String.class);
        assertNull(titre);
    }

    class InfoConcert {

        private Map<String, String> info;
        private String groupe = null;

        public InfoConcert() {
        }

        public InfoConcert(Map<String, String> info) {
            this.info = info;
        }

        public Map<String, String> getInfo() {
            return info;
        }
    }
}

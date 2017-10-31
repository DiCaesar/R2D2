package com.r2d2.repository.test;

import junit.framework.TestCase;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;

/**
 * Created by DiCaesar on 2017/8/24
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:/spring/applicationContext.xml")
@TransactionConfiguration(transactionManager = "transactionManager" , defaultRollback = true)
public abstract class BaseTest extends TestCase {

}

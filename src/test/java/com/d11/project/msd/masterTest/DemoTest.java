package com.d11.project.msd.masterTest;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import com.d11.project.msd.commonUtils.DriverManagerUtil;
import com.d11.project.msd.commonUtils.WebInteractUtil;
import com.d11.project.msd.config.BaseTest;
import com.d11.project.msd.config.Config;

public class DemoTest extends BaseTest{
	
	@Test
	public void test() throws InterruptedException {
		System.out.println(DriverManagerUtil.getWebdriver());
		DriverManagerUtil.getWebdriver().get(Config.AUT_URL);
		Thread.sleep(2000);
		WebElement webelement=DriverManagerUtil.getWebdriver().findElement(By.xpath("(//a[.='Home'])[last()]"));
		System.out.println(WebInteractUtil.scrollIntoView(webelement));
		Thread.sleep(3000);
	}
	
	@Test(enabled=false)
	public void test2() throws InterruptedException {
		System.out.println(DriverManagerUtil.getWebdriver());
		DriverManagerUtil.getWebdriver().get(Config.AUT_URL);
		Assert.assertTrue(false);
		
	}

}

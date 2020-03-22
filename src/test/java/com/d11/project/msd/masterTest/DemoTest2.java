package com.d11.project.msd.masterTest;

import org.testng.annotations.Test;
import com.d11.project.msd.commonUtils.DriverManagerUtil;
import com.d11.project.msd.config.Config;

public class DemoTest2 {
	@Test
	public void test() throws InterruptedException {
		System.out.println(DriverManagerUtil.getWebdriver());
		DriverManagerUtil.getWebdriver().get(Config.AUT_URL);
		Thread.sleep(3000);
	}
	
	@Test
	public void test2() throws InterruptedException {
		System.out.println(DriverManagerUtil.getWebdriver());
		DriverManagerUtil.getWebdriver().get(Config.AUT_URL);
		Thread.sleep(3000);
	}
}

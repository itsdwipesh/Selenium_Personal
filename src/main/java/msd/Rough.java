package msd;

import java.io.IOException;
import java.net.InetAddress;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.UnknownHostException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;

import org.openqa.selenium.Platform;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import com.d11.project.msd.config.Config;
import com.d11.project.msd.config.TestConfig;

public class Rough {

	public static void main(String[] args) throws MalformedURLException {
		
		
		Calendar cal = Calendar.getInstance();
	     SimpleDateFormat simpleformat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
	     String timeStamp=simpleformat.format(cal.getTime()).replaceAll("/|:| ","_");
	     System.out.println(timeStamp);
		

	}

}

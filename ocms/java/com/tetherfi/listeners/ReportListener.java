package com.tetherfi.listeners;

import org.testng.TestListenerAdapter;
import org.testng.ITestResult;
import org.testng.Reporter;
public class ReportListener extends TestListenerAdapter{

    private int m_count = 0;

    @Override
    public void onTestFailure(ITestResult tr) {
        log(tr.getName()+ "--Test method failed\n");
        Reporter.log("Test Case <b style=\"color:red;\" \"font-size:25px;\">FAILED</b>\n");
        Reporter.log("<img src=\"Screenshot\\"+tr.getMethod().getMethodName()+".jpg" + "\" alt=\"Image Not Found\"/><br />");
    }

    @Override
    public void onTestSkipped(ITestResult tr) {
        log(tr.getName()+ "--Test method skipped\n");
    }

    @Override
    public void onTestSuccess(ITestResult tr) {
        log(tr.getName()+ "--Test method success\n");
        Reporter.log("Test Case <b style=\"color:green;\" \"font-size:25px;\">Passed</b>\n");
        Reporter.log("<img src=\"Screenshot\\"+tr.getMethod().getMethodName()+".jpg" + "\" alt=\"Image Not Found\"/><br />");
    }

    private void log(String string) {
        System.out.print(string);
        if (++m_count % 40 == 0) {
            System.out.println("");
        }
    }

}

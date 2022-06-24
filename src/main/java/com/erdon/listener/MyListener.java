package com.erdon.listener;

import com.erdon.utils.JdbcUtils;
import com.mysql.cj.jdbc.AbandonedConnectionCleanupThread;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MyListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        System.out.println("webServlet start");

    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        System.out.println("webServlet stop");
        try {
        while (DriverManager.getDrivers().hasMoreElements()) {
            DriverManager.deregisterDriver(DriverManager.getDrivers().nextElement());
        }
            System.out.println("jdbc Driver close");
//            AbandonedConnectionCleanupThread.checkedShutdown();
            System.out.println("close thread success");
//            JdbcUtils.getConnection().close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
}

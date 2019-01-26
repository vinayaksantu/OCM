package com.tetherfi.utility;

import org.openqa.selenium.WebDriver;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class PageFactory {

    // To allow concurrent modifications to page store
    private static Map<Class, Object> pageStore = new ConcurrentHashMap<Class, Object>();

    private static Object lockObject = new Object();

    public static <T> T createPageInstance(WebDriver driver, Class<T> className) {

        if (!pageStore.containsKey(className)) {
            synchronized (lockObject) { // to avoid same page instance being created again
                if (!pageStore.containsKey(className)) {
                    T page = org.openqa.selenium.support.PageFactory.initElements(driver, className);
                    pageStore.put(className, page);
                }
            }
        }
        return (T) pageStore.get(className);

    }

    public static void reset(){
        synchronized (lockObject) {
            pageStore.clear();
        }
    }
}

package com.zhwb.learn.groovy;

/**
 * @author zhangwenbin
 * @since 2016/2/5.
 */
public class Domain {
    public void doIt() {
        String text = "fuck";
        if (Utils.isOk(text)) {
            System.out.println("what a "+text+" living day!");
        }
    }
}

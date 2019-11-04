package org.github.omnbmh.cobra.commons;

import org.github.omnbmh.cobra.commons.tools.LoggerTools;
import org.slf4j.Logger;

public class Main {

    static Logger logger = LoggerTools.getLogger(Main.class);

    public static void main(String[] args) {
        System.out.printf("Welcome to CobraCommons!");
        logger.info("logger out!");
    }
}

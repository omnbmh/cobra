package org.github.omnbmh.cobra.commons.tools;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created With IntelliJ IDEA CE
 * <p>
 * Desc: 日志输出类
 * <p>more info!</p>
 *
 * @version 2017/4/20 下午4:05
 * @since 1.7
 */
public final class LoggerTools {

    public static Logger getLogger(Class clazz) {
        return LoggerFactory.getLogger(clazz);
    }

}

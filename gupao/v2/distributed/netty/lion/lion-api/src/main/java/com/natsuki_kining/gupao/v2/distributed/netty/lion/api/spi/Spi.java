
package com.natsuki_kining.gupao.v2.distributed.netty.lion.api.spi;

import java.lang.annotation.*;

/**
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface Spi {

    /**
     * SPI name
     *
     * @return name
     */
    String value() default "";

    /**
     * 排序顺序
     *
     * @return sortNo
     */
    int order() default 0;

}

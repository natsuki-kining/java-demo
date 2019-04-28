
package com.natsuki_kining.gupao.v2.distributed.netty.lion.api.spi;

import java.util.function.Supplier;

/**
 */
@FunctionalInterface
public interface Factory<T> extends Supplier<T> {
}

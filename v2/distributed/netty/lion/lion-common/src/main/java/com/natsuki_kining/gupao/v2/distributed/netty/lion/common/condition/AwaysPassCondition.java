
package com.natsuki_kining.gupao.v2.distributed.netty.lion.common.condition;

import com.natsuki_kining.gupao.v2.distributed.netty.lion.api.common.Condition;

import java.util.Map;

/**
 *
 *
 *
 */
public final class AwaysPassCondition implements Condition {
    public static final Condition I = new AwaysPassCondition();

    @Override
    public boolean test(Map<String, Object> env) {
        return true;
    }
}

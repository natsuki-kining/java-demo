
package com.natsuki_kining.gupao.v2.distributed.netty.lion.common.condition;

import com.natsuki_kining.gupao.v2.distributed.netty.lion.api.common.Condition;

import javax.script.*;
import java.util.Map;

/**
 *
 *
 *
 */
public final class ScriptCondition implements Condition {
    private static final ScriptEngineManager scriptEngineManager = new ScriptEngineManager();
    private static final ScriptEngine jsEngine = scriptEngineManager.getEngineByName("js");

    private final String script;

    public ScriptCondition(String script) {
        this.script = script;
    }

    @Override
    public boolean test(Map<String, Object> env) {
        try {
            return (Boolean) jsEngine.eval(script, new SimpleBindings(env));
        } catch (Exception e) {
            return false;
        }
    }
}

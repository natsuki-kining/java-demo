
package com.natsuki_kining.gupao.v2.distributed.netty.lion.tester.util;

import com.natsuki_kining.gupao.v2.distributed.netty.lion.tools.Utils;
import org.junit.Test;

/**
 */
public class IPTest {
    @Test
    public void getLocalIP() throws Exception {
        System.out.println(Utils.lookupLocalIp());
        System.out.println(Utils.lookupExtranetIp());

    }
}


package com.natsuki_kining.gupao.v2.source_code.design_pattern.proxy.mycglib;

import com.natsuki_kining.gupao.v2.source_code.design_pattern.proxy.Work;

public class CglibWorkTest {

    public static void main(String[] args) {
        Work work = (Work)new MyCglibWorkProxy().getInstance(Work.class);
        work.findWork();
    }

}

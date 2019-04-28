package com.natsuki_kining.gupao.v2.source_code.design_pattern.proxy.cglib;

import com.natsuki_kining.gupao.v2.source_code.design_pattern.proxy.Work;

public class CglibWorkTest {

    public static void main(String[] args) {
        Work work = (Work)new CglibWorkProxy().getInstance(Work.class);
        work.findWork();
    }

}

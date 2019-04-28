

package com.natsuki_kining.gupao.v2.distributed.netty.lion.api.connection;


public interface Cipher {

    byte[] decrypt(byte[] data);

    byte[] encrypt(byte[] data);

}

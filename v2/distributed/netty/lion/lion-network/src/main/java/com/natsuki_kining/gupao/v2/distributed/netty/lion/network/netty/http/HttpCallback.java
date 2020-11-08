
package com.natsuki_kining.gupao.v2.distributed.netty.lion.network.netty.http;


import io.netty.handler.codec.http.HttpResponse;

public interface HttpCallback {

    void onResponse(HttpResponse response);

    void onFailure(int statusCode, String reasonPhrase);

    void onException(Throwable throwable);

    void onTimeout();

    boolean onRedirect(HttpResponse response);
}

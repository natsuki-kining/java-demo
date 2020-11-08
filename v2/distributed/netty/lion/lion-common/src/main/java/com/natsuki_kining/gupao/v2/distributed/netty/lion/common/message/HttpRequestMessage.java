
package com.natsuki_kining.gupao.v2.distributed.netty.lion.common.message;

import com.natsuki_kining.gupao.v2.distributed.netty.lion.api.connection.Connection;
import com.natsuki_kining.gupao.v2.distributed.netty.lion.api.protocol.Command;
import com.natsuki_kining.gupao.v2.distributed.netty.lion.api.protocol.Packet;
import com.natsuki_kining.gupao.v2.distributed.netty.lion.tools.Utils;
import io.netty.buffer.ByteBuf;

import java.util.Map;

/**
 */
public final class HttpRequestMessage extends ByteBufMessage {
    public byte method;
    public String uri;
    public Map<String, String> headers;
    public byte[] body;

    public HttpRequestMessage(Connection connection) {
        super(new Packet(Command.HTTP_PROXY, genSessionId()), connection);
    }

    public HttpRequestMessage(Packet message, Connection connection) {
        super(message, connection);
    }

    @Override
    public void decode(ByteBuf body) {
        method = decodeByte(body);
        uri = decodeString(body);
        headers = Utils.headerFromString(decodeString(body));
        this.body = decodeBytes(body);
    }

    @Override
    public void encode(ByteBuf body) {
        encodeByte(body, method);
        encodeString(body, uri);
        encodeString(body, Utils.headerToString(headers));
        encodeBytes(body, this.body);
    }


    public String getMethod() {
        switch (method) {
            case 0:
                return "GET";
            case 1:
                return "POST";
            case 2:
                return "PUT";
            case 3:
                return "DELETE";
        }
        return "GET";
    }

    @Override
    public String toString() {
        return "HttpRequestMessage{" +
                "method=" + method +
                ", uri='" + uri + '\'' +
                ", headers=" + headers +
                ", body=" + (body == null ? "" : body.length) +
                '}';
    }
}

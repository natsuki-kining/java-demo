
package com.natsuki_kining.gupao.v2.distributed.netty.lion.registion.zk;

import com.google.common.base.Strings;
import com.natsuki_kining.gupao.v2.distributed.netty.lion.api.srd.CommonServiceNode;
import com.natsuki_kining.gupao.v2.distributed.netty.lion.api.srd.ServiceListener;
import com.natsuki_kining.gupao.v2.distributed.netty.lion.tools.Jsons;
import com.natsuki_kining.gupao.v2.distributed.netty.lion.tools.log.Logs;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.recipes.cache.ChildData;
import org.apache.curator.framework.recipes.cache.TreeCacheEvent;
import org.apache.curator.framework.recipes.cache.TreeCacheListener;

/**
 *
 *
 */
public final class ZKCacheListener implements TreeCacheListener {

    private final String watchPath;

    private final ServiceListener listener;

    public ZKCacheListener(String watchPath, ServiceListener listener) {
        this.watchPath = watchPath;
        this.listener = listener;
    }

    @Override
    public void childEvent(CuratorFramework curator, TreeCacheEvent event) throws Exception {
        ChildData data = event.getData();
        if (data == null) return;
        String dataPath = data.getPath();
        if (Strings.isNullOrEmpty(dataPath)) return;
        if (dataPath.startsWith(watchPath)) {
            switch (event.getType()) {
                case NODE_ADDED:
                    listener.onServiceAdded(dataPath, Jsons.fromJson(data.getData(), CommonServiceNode.class));
                    break;
                case NODE_REMOVED:
                    listener.onServiceRemoved(dataPath, Jsons.fromJson(data.getData(), CommonServiceNode.class));
                    break;
                case NODE_UPDATED:
                    listener.onServiceUpdated(dataPath, Jsons.fromJson(data.getData(), CommonServiceNode.class));
                    break;
            }
            Logs.RSD.info("ZK node data change={}, nodePath={}, watchPath={}, ns={}");
        }
    }
}

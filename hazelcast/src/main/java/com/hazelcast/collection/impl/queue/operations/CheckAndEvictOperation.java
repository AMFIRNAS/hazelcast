/*
 * Copyright (c) 2008-2016, Hazelcast, Inc. All Rights Reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.hazelcast.collection.impl.queue.operations;

import com.hazelcast.collection.impl.queue.QueueContainer;
import com.hazelcast.collection.impl.queue.QueueDataSerializerHook;
import com.hazelcast.spi.ProxyService;

/**
 * Provides eviction functionality for Operations of Queue.
 */
public class CheckAndEvictOperation extends QueueOperation {

    public CheckAndEvictOperation() {
    }

    public CheckAndEvictOperation(String name) {
        super(name);
    }

    @Override
    public void run() throws Exception {
        final QueueContainer queueContainer = getContainer();
        if (queueContainer.isEvictable()) {
            ProxyService proxyService = getNodeEngine().getProxyService();
            proxyService.destroyDistributedObject(getServiceName(), name);
        }
    }

    @Override
    public int getId() {
        return QueueDataSerializerHook.CHECK_EVICT;
    }
}

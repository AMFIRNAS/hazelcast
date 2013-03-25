/*
 * Copyright (c) 2008-2013, Hazelcast, Inc. All Rights Reserved.
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

package com.hazelcast.transaction;

import com.hazelcast.nio.ObjectDataInput;
import com.hazelcast.nio.ObjectDataOutput;
import com.hazelcast.nio.serialization.DataSerializable;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

public final class TransactionOptions implements DataSerializable {

    private long timeoutMillis;

    private int durability;

    public TransactionOptions() {
        setTimeout(2, TimeUnit.MINUTES).setDurability(1);
    }

    public long getTimeoutMillis() {
        return timeoutMillis;
    }

    public TransactionOptions setTimeout(long timeout, TimeUnit timeUnit) {
        if (timeout <= 0) {
            throw new IllegalArgumentException("Timeout must be positive!");
        }
        this.timeoutMillis = timeUnit.toMillis(timeout);
        return this;
    }

    public int getDurability() {
        return durability;
    }

    public TransactionOptions setDurability(int durability) {
        if (durability < 0) {
            throw new IllegalArgumentException("Durability cannot be negative!");
        }
        this.durability = durability;
        return this;
    }

    public static TransactionOptions getDefault() {
        return new TransactionOptions();
    }

    public void writeData(ObjectDataOutput out) throws IOException {
        out.writeLong(timeoutMillis);
        out.writeInt(durability);
    }

    public void readData(ObjectDataInput in) throws IOException {
        timeoutMillis = in.readLong();
        durability = in.readInt();
    }


    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append("TransactionOptions");
        sb.append("{timeoutMillis=").append(timeoutMillis);
        sb.append(", durability=").append(durability);
        sb.append('}');
        return sb.toString();
    }
}

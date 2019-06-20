package com.hazelcast.internal.query.exec;

import com.hazelcast.internal.query.QueryContext;
import com.hazelcast.internal.query.io.RowBatch;
import com.hazelcast.internal.query.io.SendBatch;
import com.hazelcast.internal.query.worker.data.DataWorker;

/**
 * Basic execution stage.
 */
public interface Exec {
    /**
     * One-time setup of the executor.
     *
     * @param ctx Context.
     */
    void setup(QueryContext ctx, DataWorker worker);

    /**
     * Try advancing executor. Content of the current batch will be changed as a result of this call.
     *
     * @return Result of iteration.
     */
    IterationResult advance();

    /**
     * @return Current batch available in response to {@link #advance()} call.
     */
    RowBatch currentBatch();
}

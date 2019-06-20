package com.hazelcast.internal.query.exec;

import com.hazelcast.internal.query.io.Row;
import com.hazelcast.internal.query.io.RowBatch;

import java.util.Iterator;

/**
 * Consumer of results.
 */
public interface RootConsumer {
    /**
     * Perform one-time setup.
     *
     * @param root Root.
     */
    void setup(RootExec root);

    /**
     * Consume rows from the batch.
     *
     * @param batch Batch.
     * @param startPos Start position in the batch.
     * @return Number of rows consumed.
     */
    int consume(RowBatch batch, int startPos);

    /**
     * Mark results as finished.
     */
    void done();

    /**
     * Open an iterator over results.
     *
     * @return Iterator.
     */
    Iterator<Row> iterator();
}

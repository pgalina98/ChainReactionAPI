package hr.pgalina.chain_reaction.config;

import org.springframework.transaction.support.TransactionSynchronization;
import org.springframework.transaction.support.TransactionSynchronizationManager;

public class AsyncExecutor {
    private AsyncExecutor() {
        throw new IllegalStateException();
    }

    public static void executeAfterTransactionCommits(final Runnable task) {
        TransactionSynchronizationManager.registerSynchronization(new TransactionSynchronization() {
            public void afterCommit() {
                task.run();
            }
        });
    }
}
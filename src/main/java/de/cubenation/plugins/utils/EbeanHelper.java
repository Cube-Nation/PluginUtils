package de.cubenation.plugins.utils;

import java.util.logging.Level;
import java.util.logging.Logger;

import javax.persistence.PersistenceException;

import com.avaje.ebean.Transaction;

/**
 * Useful methodes for Ebean Server.
 * 
 * @since 0.1.4
 */
public class EbeanHelper {
    private final static Logger log = Logger.getLogger(EbeanHelper.class.getName());

    /**
     * Close a transaction and catch all exceptions. Open changes will be
     * rollbacked.
     * 
     * @param transaction
     * @since 0.1.4
     */
    public static void endQuiet(Transaction transaction) {
        if (transaction == null) {
            return;
        }

        try {
            transaction.end();
        } catch (PersistenceException e) {
            log.log(Level.WARNING, "error on end transaction", e);
        }
    }

    /**
     * Rollback a transaction and catch all exceptions.
     * 
     * @param transaction
     * @since 0.1.4
     */
    public static void rollbackQuiet(Transaction transaction) {
        if (transaction == null) {
            return;
        }

        try {
            transaction.rollback();
        } catch (PersistenceException e) {
            log.log(Level.WARNING, "error on rollback transaction", e);
        }
    }
}

package de.cubenation.plugins.utils;

public abstract class Callback<T> {

    /**
     * The time the callback object was created, in milliseconds.
     */
    protected long createTime;

    /**
     * The default constructor.
     */
    public Callback() {
        createTime = System.currentTimeMillis();

    }

    /**
     * Returns the time when this callback was created.
     * 
     * @return the time, in milliseconds
     */
    public long getCreateTime() {
        return createTime;
    }

    /**
     * Returns the current age of this callback.
     * 
     * @return the number of milliseconds since this callback was created
     */
    public long getAge() {
        return System.currentTimeMillis() - createTime;
    }

    /**
     * Called after successful completion of an asynchronous call.
     * <p>
     * This method is called on the main server thread.
     * 
     * @param t
     *            the return value of the asynchronous call
     */
    public void onSuccess(T t) {
    }

    /**
     * Called when an exception occurs during the asynchronous call.
     * <p>
     * Usually, the exception comes from the remote side of the call, but it can
     * also come from the local side, for example, when the call times out.
     * <p>
     * This method is called on the main server thread.
     * 
     * @param e
     *            the exception
     */
    public void onFailure(Exception e) {
    }

}

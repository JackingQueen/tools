package cn.xkyln.thread;

/**
 * 自定义一个 UncaughtExceptionHandler，用于处理线程异常，防止线程中断而导致资源没有释放
 */
public class ErrHandler implements Thread.UncaughtExceptionHandler {

    /**
     * 异常处理
     *
     * @param t
     * @param e
     */
    @Override
    public void uncaughtException(Thread t, Throwable e) {
        System.err.printf("%s: %s at line %d of %s%n",
                t.getName(),
                e.toString(),
                e.getStackTrace()[0].getLineNumber(),
                e.getStackTrace()[0].getFileName());
    }
}

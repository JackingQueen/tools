package cn.xkyln.thread;

public class ErrThread extends Thread {
    @Override
    public void run() {
        // 抛异常的地方
        int i = 1 / 0;
    }
}

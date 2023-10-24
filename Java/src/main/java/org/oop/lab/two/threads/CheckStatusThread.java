package org.oop.lab.two.threads;

public class CheckStatusThread extends Thread {

    public CheckStatusThread() throws InterruptedException {
        while (true) {
            this.run();
            Thread.sleep(5000);
        }
    }
    @Override
    public void run() {

    }
}

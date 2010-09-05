package net.binarymuse.embjettest;

public class ShutdownThread implements Runnable {

    @Override
    public void run() {
        try {
            EmbJetTest.server.setGracefulShutdown(1000);
            EmbJetTest.server.stop();
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

}

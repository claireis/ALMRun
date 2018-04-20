package tk.carlyle2k.handler;

import tk.carlyle2k.bean.AlmBean;

public class DefaultHandler extends Handler {
    public DefaultHandler() {
        super("");
    }

    @Override
    public AlmBean handleFilePath(String path, int index) {

        System.err.println("[" + path + "] can't find match!");

        return null;
    }
}

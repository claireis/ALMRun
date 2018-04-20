package tk.carlyle2k.handler;

import tk.carlyle2k.bean.AlmBean;

import java.util.regex.Matcher;

public class ExeHandler extends Handler {
    public ExeHandler() {
        super("^([\\w-]+) ((.+/)(.+)\\.(exe|ahk))$");
    }

    @Override
    public AlmBean handleFilePath(String path, int index) {
        Matcher exe = pattern.matcher(path);

        return exe.matches() ?
                new AlmBean(exe.group(2), exe.group(1), exe.group(1), index) :
                successor.handleFilePath(path, index);
    }
}

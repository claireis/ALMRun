package tk.carlyle2k.handler;

import tk.carlyle2k.bean.AlmBean;

import java.util.regex.Matcher;

public class SysHandler extends Handler {
    public SysHandler() {
        super("^(([-\\w]+)[\\.:\\w]*)$");
    }

    @Override
    public AlmBean handleFilePath(String path, int index) {
        Matcher sys = pattern.matcher(path);

        return sys.matches() ?
                new AlmBean(sys.group(1), sys.group(2), sys.group(2), index) :
                successor.handleFilePath(path, index);
    }
}

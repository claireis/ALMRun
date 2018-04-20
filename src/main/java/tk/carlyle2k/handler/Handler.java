package tk.carlyle2k.handler;

import tk.carlyle2k.bean.AlmBean;

import java.util.regex.Pattern;

/**
 * handle difference executable file path
 */
public abstract class Handler {
    /**
     * successor handler
     */
    protected Handler successor;

    /**
     * need regex to handle file path
     */
    protected Pattern pattern;

    public Handler(String regex) {
        pattern = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
    }

    public void setSuccessor(Handler successor) {
        this.successor = successor;
    }

    /**
     * handle file path
     * @param path file path
     * @param index index of command
     */
    public abstract AlmBean handleFilePath(String path, int index);
}

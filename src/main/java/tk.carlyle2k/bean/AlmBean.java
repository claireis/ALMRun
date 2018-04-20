package tk.carlyle2k.bean;

public class AlmBean {
    private String cmd;
    private String name;
    private String desc;
    private int index;

    public AlmBean(String cmd, String name, String desc, int index) {
        this.cmd = cmd;
        this.name = name;
        this.desc = desc;
        this.index = index;
    }

    @Override
    public String toString() {
        return "[cmds/" + index + "]\n" +
                "cmd=" + cmd + "\n" +
                "name=" + name + "\n" +
                "key=" + "\n" +
                "desc=" + desc + "\n";
    }
}

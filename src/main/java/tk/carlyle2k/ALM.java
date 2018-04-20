package tk.carlyle2k;

import tk.carlyle2k.bean.AlmBean;
import tk.carlyle2k.handler.DefaultHandler;
import tk.carlyle2k.handler.ExeHandler;
import tk.carlyle2k.handler.Handler;
import tk.carlyle2k.handler.SysHandler;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

import static java.util.stream.Collectors.toList;

public class ALM {

    /**
     * composite handlers
     */
    private static Handler getHandlers() {
        Handler h1 = new ExeHandler();
        Handler h2 = new SysHandler();
        Handler h3 = new DefaultHandler();

        h1.setSuccessor(h2);
        h2.setSuccessor(h3);

        return h1;
    }

    public static void main(String[] args) throws IOException {
        //read program paths
        ConfigAlm config = ConfigAlm.getMe();
        Collection<String> lines = config.getProgramPaths();
        Handler handler = getHandlers();

        //alm already has two commands
        int index = 3;

        List<AlmBean> almBeans = new ArrayList<>(128);
        for (String line : lines) {
            //convert line to alm command
            almBeans.add(handler.handleFilePath(line, index++));
        }

        List<String> saveList = almBeans.stream()
                .filter(Objects::nonNull)
                .map(AlmBean::toString)
                .collect(toList());

        //save result
        config.writeToAlmConfigFile(saveList);
    }

}
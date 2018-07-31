import org.apache.commons.exec.CommandLine;
import org.apache.tools.ant.helper.DefaultExecutor;

import java.io.IOException;

public class ShellRunner {

    private final String TAG = "SHELL RUNNER ";

    private String path = "/Users/tkallinich/DashboardProjectResources/Rest/ShellScripte/";

    public ShellRunner() {
    }

    public void run(String c) throws IOException {
        String[]cmd;

        switch (c){
            case "open":

                System.out.println(TAG + "run shell: openFile.sh");

                path = path + "openFile.sh";

                cmd = new String[]{"/bin/bash", path};
                Runtime.getRuntime().exec(cmd);

                break;

            case "git push":

                System.out.println(TAG + "run shell: gitPush.sh");

                path = path + "openFile.sh";

                cmd = new String[]{"/bin/bash", path};
                Runtime.getRuntime().exec(cmd);

                break;

            default:
                System.out.println(TAG + "no script to run found");

        }

    }
}

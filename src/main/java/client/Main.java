package client;

import constant.CONFIG;
import service.UI;

public class Main {
    public static void main(String[] args) {
        if (args.length > 1 && args[0].equals("-access")){
            CONFIG.AUTH_SERVER = args[1];
        }
        if (args.length > 2 && args[2].equals("-resource")){
            CONFIG.API_SERVER = args[3] + "/v1/browse/";
        }
        UI ui = new CommandLineUI();
        ui.start();
    }
}

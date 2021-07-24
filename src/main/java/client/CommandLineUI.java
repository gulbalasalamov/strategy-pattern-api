package client;

import context.Context;
import request.Authorization;
import service.UI;
import strategy.CategoryStrategy;
import strategy.FeaturedStrategy;
import strategy.PlaylistsStrategy;

import java.util.List;
import java.util.Scanner;

public class CommandLineUI implements UI {
    Scanner scanner = new Scanner(System.in);
    boolean authorized = false;
    Context context = new Context();

    @Override
    public void start() {
        String input = getInput();
        String option = getOption(input);

        while (!"exit".equals(option)) {
            if ("auth".equals(option)) {
                Authorization auth = new Authorization();
                authorized = auth.authorize();
            } else if (!authorized) {
                System.out.println("Please, provide access for application.");
            } else {
                switch (option) {
                    case "featured":
                        context.setStrategy(new FeaturedStrategy());
                        break;
                    case "new":
                        //context.setStrategy(new NewReleasesStrategy());
                        break;
                    case "categories":
                        context.setStrategy(new CategoryStrategy());
                        break;
                    case "playlists":
                        String category = input.substring(10);
                        context.setStrategy(new PlaylistsStrategy(category));
                        break;
                }
                List<String> list = context.execute();
                list.forEach(System.out::println);
            }
            input = getInput();
            option = input.split(" ")[0];
        }
        System.out.println("---GOODBYE!---");
    }

    String getInput() {
        return scanner.nextLine();
    }

    String getOption(String input) {
        return input.split(" ")[0];
    }
}

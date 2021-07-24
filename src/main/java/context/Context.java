package context;

import service.AdviceStrategy;

import java.util.List;

/**
 * A special class for storing reference to a strategy
 * The context delegates execution to an instance of concrete strategy through its interface, instead of implementing behaviour itself
 */
public class Context {
    AdviceStrategy strategy;

    public void setStrategy(AdviceStrategy strategy){
        this.strategy = strategy;
    }

    public List<String> execute(){
        return this.strategy.execute();
    }

//    SelectionBehaviour selectionBehaviour;
//    public boolean isAuth;
//
//    public void setSelectionBehaviour(SelectionBehaviour sb) {
//        this.selectionBehaviour = sb;
//    }
//
//    public void performSelection(String genre) {
//        selectionBehaviour.select(genre);
//    }
//
//    public void login() {
//        //Set Auth to true
//        this.isAuth = true;
//        System.out.println("---SUCCESS---");
//    }
//
//    public void logout() {
//        this.isAuth = false;
//    }
}

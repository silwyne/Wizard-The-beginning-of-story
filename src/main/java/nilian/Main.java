package nilian;

import nilian.graphics.window.MainWindow;
import nilian.loader.MainLoader;

public class Main {
    public static void main(String[] args) {
        MainLoader.loadAll();
        MainWindow.show();
    }
}
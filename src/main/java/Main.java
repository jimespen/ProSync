import com.github.prosync.gui.MainMenu;
import com.github.prosync.logic.CameraController;

/**
 * Created by jim-espen on 10/3/14.
 */
public class Main {
    public static void main(String[] args) {
        MainMenu mainMenu = new MainMenu();
        CameraController cameraController = new CameraController();

        MainMenu main = new MainMenu();
        main.createAndShowGUI();
    }
}

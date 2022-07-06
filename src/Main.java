import controller.MainForm;

public class Main {
    public static void main(String[] args) {

        try {
            new MainForm();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

public class InteractiveShell {
    public static InteractiveShell instance = null;

    public InteractiveShell() {
        // singleton
        if (instance == null) {
            instance = this;
        }
    }
}

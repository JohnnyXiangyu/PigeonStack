public class InteractiveShell {
    public static InteractiveShell instance = null;

    public InteractiveShell() {
        // singleton
        if (instance == null) {
            instance = this;
        }
    }

    public void Start() { // pass control to shell
        String newCommand = "";
        Boolean interrupted = false;
        System.out.println("PigeonStack: Entering interactive shell");

        // main routine
        while (!interrupted) {
            System.out.printf(">> ");
            newCommand = System.console().readLine();
            switch (newCommand) {
                case "reset":
                    this.Reset();
                    break;
                default:
                    System.out.println("Error: invalid command");
                    break;
            }
        }
    }

    public void Reset() { // reset stored arguments

    }

    public static void main(String args[]) { // debug code
        new InteractiveShell();
        InteractiveShell.instance.Start();
    }
}

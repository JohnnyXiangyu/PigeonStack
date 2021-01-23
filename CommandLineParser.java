public class CommandLineParser {
    public static CommandLineParser instance = null;

    public enum UIMode {
        INTERACTIVE, NEWITEM
    }

    public class NewItemInfo {
        String summary, content, comment;
        int priorityLevel;
        String destination;
    }

    private UIMode mode;
    private NewItemInfo niInfo;

    public CommandLineParser() {
        // singleton 
        if (instance == null) {
            instance = this;
        }
    }

    public void ProcessArgs(String[] rs) {
        // Parse options
        if (rs.length == 0 || rs[0] == "--interactive" || rs[0] == "-i") { // interactive
            // Should be the only option
            if (rs.length <= 1) {
                this.mode = UIMode.INTERACTIVE;
            } else {
                // Terminate with error (this one I don't want to return to main, too messy)
                System.out.println("Error: too many arguments after \"" + rs[0] + "\"");
                System.exit(120);
            }
        } else if (rs[0] == "--new" || rs[0] == "-n") { // new item
            this.mode = UIMode.NEWITEM;
            if (rs.length == 5 || rs.length == 7) {
                // Summary, content, comment, priority
                this.niInfo = new NewItemInfo();
                niInfo.summary = rs[1];
                niInfo.content = rs[2];
                niInfo.comment = rs[3];
                niInfo.priorityLevel = Integer.parseInt(rs[4]);
            }
            if (rs.length == 7) {
                // All above and --to destination
                if (rs[6] == "--to" || rs[6] == "-t") {
                    niInfo.destination = rs[7];
                } else {
                    // error out
                    System.out.println("Error: bad argument \"" + rs[6] + "\"");
                    System.exit(160);
                }
            }
        } else {
            // It's time to give the error: bad argument syntax
            System.out.println("Error: bad arguments");
            System.exit(140);
        }
    }
}

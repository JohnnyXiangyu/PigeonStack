public class UserAction {
    public enum Action {
        INTERACTIVE, NEWITEM
    }

    public class NewItemInfo { // only associated with Action.NEWITEM
        String summary, content, comment;
        int priorityLevel;
        String destination;
    }

    public Action mode;
    public NewItemInfo niInfo;
}

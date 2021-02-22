import java.util.*;

public class List {
    String name;
    String alias;
    PriorityQueue<Item> importantItems = new PriorityQueue<>();
    LinkedList<Item> negativeItems = new LinkedList<>();
    Set<Item> setOfImportant = new HashSet<>();

    public List(String nm, String al) {
        // the list itself can only be created as empty
        this.name = nm;
        this.alias = al;

    }

    private static class Item implements Comparable<Item> {
        public String summary, content, comment;
        public int priorityLevel; // range 1~10, or negative number for "not important"

        public Item(String smr, String ctt, String cmt, int pl) {
            summary = smr;
            content = ctt;
            comment = cmt;
            priorityLevel = pl;
        }

        @Override
        public int compareTo(Item other) {
            return Integer.compare(this.priorityLevel, other.priorityLevel);
        }
    }

    public void AddItem(String summary, String content, String comment, int priority_level) {
        Item newItem = new Item(summary, content, comment, priority_level);

        // decide if the item is important
        if (newItem.priorityLevel > 0) {
            // important: need to order them
            importantItems.add(newItem);
            setOfImportant.add(newItem);
        } else {
            // Unimportant: append directly
            negativeItems.add(newItem);
        }
    }

    public void DeleteItem(int item_index) {
        if (item_index < importantItems.size()) {
            // important
            Item victim = null;

            // iterate index times to find the victim pointer
            Iterator<Item> iterator = importantItems.iterator();
            for (int i = 0; i <= item_index; i++) {
                victim = iterator.next();
            }

            // remove the found item
            importantItems.remove(victim);
            setOfImportant.remove(victim);
        } else {
            // remove item from list directly
            negativeItems.remove(item_index - importantItems.size());
        }
    }

    public void PrintImportantItems() {
        for (Item temp : importantItems) {
            String whole_item = temp.summary + " | " + temp.content + " | " + temp.comment;
            System.out.println(whole_item);
        }
    }

    public void PrintNonImportantItems() {
        for (Item temp : negativeItems) {
            String whole_item = temp.summary + " | " + temp.content + " | " + temp.comment;
            System.out.println(whole_item);
        }
    }
}

public interface StorageModule {
    public static StorageModule instance = null;

    public void CreateList(String name, String alias);
    public void RemoveList(String alias);
    public void AddItemToList(String summary, String content, String comment, int priority_level);
    public void RemoveItemFromList(int index);
    public void ReadWholeList(String alias);
    public void PeakTopOfList(String alias);
    
    // alternatives
    public void PrintWholeList(String alias);
    public void PrintTopOfList(String alias);
}

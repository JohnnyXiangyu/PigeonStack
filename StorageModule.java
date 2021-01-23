public interface StorageModule {
    public void CreateList(String name, String alias);
    public void RemoveList(String alias);
    public void AddItemToList(String summary, String content, String comment, int priority_level);
    public void RemoveItemFromList(int index);
    public void ReadWholeList();
    public void PeakTopOfList();
    
    // alternatives
    public void PrintWholeList(String alias);
    public void PrintTopOfList(String alias);
}

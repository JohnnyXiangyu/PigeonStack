public interface StorageModule {
    public void CreateList(String name, String alias);
    public void RemoveList(String alias);
    public void AddItemToList();
    public void RemoveItemFromList();
    public void ReadWholeList();
    public void PeakTopOfList();
}

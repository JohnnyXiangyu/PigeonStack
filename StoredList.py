# the abstracted representation of a list
# all other storage-related modules will use this class to store information
# to avoid naming confusion I'm calling this Stored List.


class ListItem:
    def __init__(self, summary='', content='', comment='', priority=0):
        self.summary = ''
        self.content = ''
        self.comment = ''
        self.priority = 0  # let's say the priority level is only 1~3 and negative numbers


class StoredList:
    def __init__(self, pName='', pAlias='', pSortedItems=[], pUnsortedItems=[]):
        self.name = ''
        self.alias = ''
        self.sortedItems = []
        self.unsortedItems = []

    def AddItem(self, summary='', content='', comment='', priority=0):
        newItem = ListItem(summary, content, comment, priority)
        if priority > 0:
            inserted = True
            for i in range(0, self.sortedItems.count):
                if self.sortedItems[i].priority > priority:
                    self.sortedItems.insert(i, newItem)
                    inserted = True
                    break
            if inserted == False:
                self.sortedItems.append(newItem)
        else:
            self.unsortedItems.append(newItem)

    def RemoveItem(self, index=0):
        if index < self.sortedItems.count:
            self.sortedItems.pop(index)
        elif index < self.sortedItems.count + self.unsortedItems.count:
            self.unsortedItems.pop(index - self.sortedItems.count)

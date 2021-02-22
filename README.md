# Pigeon Stack

It allows me to put a tentative TODO list to CLI.

Each list item allows: 1) Summary 2) Content 3) Comment 4) Priority Level

## Design/Architecture

### File Storage

Storage.py

There should be a utility storing information to a "somewhere".

I'm actually thinking making this feature generic so user can expand it later.

#### Storage Interfaces

Obviously I'll need more than just "write to file" in the generic interface.

To allow reasonable user experience, let's say there must be at least the following functions provided by the interface:

##### Write

The most basic one. Use this interface to serialize information.

##### Read

Not exposed to user, but the program will need to read from the storage from time to time.

##### Settings

The most neglected module in games. Put this interface in since every storage implementation will have stuff to configure.

For the Json module to start with, settings will include the Json file location.

### CLI interface

UI.py

There should be 2 modes: command-argument and shell mode.

The easiest part probably.

#### CLI Options

TODO

### Overall Class Relationships

Besides init.py, each module only talks to init.py. This file serves as a central manager that process and route information.

init.py will pass control to UI.py, which will decide either to parse arguments or to enter shell mode.

After UI.py gathers enough information, it notifies init.py what was the user command. This message should also include how the user interacts with the program.

init.py will then execute the command. In the process CRUD operations will be performed by calling Storage.py.

## Scratch Paper

By all means things here mean nothing. It's my scratch paper.

make list aliases unique but names don't have to be

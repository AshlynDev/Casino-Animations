# CasinoAnimations

CasinoAnimations is a mirror of JustAnimations

CasinoAnimations is a plugin that allows you to animate blocks in Minecraft. 
Not the textures but animate stuff with blocks. It can make for cool lobbies,
RPG servers, and more.

Basic usage:
- /anima create <name>
- /anima animation {name} addframe <ticks/optional, default is 20>
- /anima animation {name} start
- /anima animation {name} stop

To create a frame use worldedit and select an area, the selected area will be the frame

Requires WorldEdit

## Permissions

- `casinoanimations.admin` - All permissions
- `casinoanimations.command` - All commands

## What file storage should I use for my animation?
There are two type:
- Single file (Stores all frames in a single file)
- Multiple files (Stores each frame in a separate file)

You select which one to use on animation creation.  The default is multiplefile.
/anima create <name> <singlefile/multiplefile>

### Multiple files

Pros:

- Less memory usage
- Easier to find certain frames in the files

Cons:

- Uses more CPU to read the files each frame

This one is good for bigger animations or servers with less ram but decent CPU power

### Single file

Pros:

- Less CPU usage

Cons:

- Uses more ram as it keeps the whole file loaded in ram
- Takes longer to load the file

This one is good for smaller animations or servers with lots of ram but not so much CPU power

## Animation load types

There are two animation load types. ram and file.
File loads the animation frames from the file each time it is played. and ram
loads it from ram each time it is played.

Ram is good for smaller animations and file is good for bigger animations.


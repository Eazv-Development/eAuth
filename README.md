# eAuth
This open source add-on can help to have a better performance and not to use many plugins inside the Auth server.


### Configuration:
------------
```yml
SCOREBOARD:
    TICKS: 20
    TITLE: "&b&lAuth Core"
    LINES:
        - "&7&m---------------------------"
        - ""
        - "&b&lLogin"
        - "&7/login <pass>"
        - ""
        - "&b&lRegister"
        - "&7/register <pass> <pass>"
        - ""
        - "&bplay.server.net"
        - "&7&m---------------------------"

MESSAGES:
    ONLY_PLAYERS: "&cThis command is only for players"
    NO_PERMISSIONS: "&cYou don't have permissions"
    SPAWN_SETTED: "&aSpawn has been currently setted."
        
OPTIONS:
    HIDE_PLAYERS: true
    CANCEL_MOVEMENT: true
    CANCEL_CHAT: true

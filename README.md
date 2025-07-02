# 🛡️ eAuth

**eAuth** is a lightweight plugin for authentication servers. It helps improve performance by managing essential features like player restrictions and scoreboards, all without needing multiple plugins.

## ⚙️ Configuration (`config.yml`)

```yaml
SCOREBOARD:
  TICKS: 20
  TITLE: "&b&leAuth Core"
  LINES:
    - "&7&m---------------------------"
    - ""
    - "&b&lLogin"
    - "&7Use &f/login <password>"
    - ""
    - "&b&lRegister"
    - "&7Use &f/register <password> <password>"
    - ""
    - "&fplay.server.net"
    - "&7&m---------------------------"

MESSAGES:
  ONLY_PLAYERS: "&cThis command can only be used by players."
  NO_PERMISSIONS: "&cYou do not have permission to use this command."
  SPAWN_SET: "&aSpawn point has been successfully set."

OPTIONS:
  HIDE_PLAYERS: true
  CANCEL_MOVEMENT: true
  CANCEL_CHAT: true
```

---

### 📌 Additional Info
* ✅ Optional: [PlaceholderAPI](https://www.spigotmc.org/resources/placeholderapi.6245/) support
* 🧪 Command: `/setspawn` | sets the join spawn point
PluginUtils
==========
Contains useful classes for Bukkit plugins.

ChatApi
==========
Message resource files must have UTF-8 encoding too.

CommandApi
==========
CommandApi is a command parser and handler for Bukkit. It deals with
incoming console and player commands and dispatches them to the appropriate
Java classes.

PermissionApi
==========
Simple permission interface for easy use. Bukkit core permission methods is used, if PermissionEx plugin is not available.

PluginApi
==========
New base class `BasePlugin` for Bukkit plugins with standard services (PermissionService, ChatService, CommandsManager).
For easy extend, overwrite following hook methods:
- preEnableActions
- postEnableActions
- initialCustomServices
- startCustomServices
- stopCustomServices
- registerCommands
- initialCustomEventListeners
- registerCustomEventListeners
- registerDatabaseModel
- migrateOldData
- getCustomDatabaseServer
- registerScheduledTasks
- createCustomErrorHandler

WrapperApi
==========
Use WrapperApi for third party plugin integration. So it wouldn't need `depend` configuration in plugin.yml.
Before use, check WrapperManager.isPluginEnabled if plugin is available.
  
Available wrapper classes:
- dynmap
- LogBlock
- LWC
- PermissionsEx
- Shopkeepers
- VanishNoPacket
- Vault
- WorldEdit
- WorldGuard

---

License: The MIT License (MIT)

Copyright (c) 2013 Cube-Nation (Björn Teichmann)

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in
all copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
THE SOFTWARE.

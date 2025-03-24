# NoStackTotems

A simple Minecraft plugin that prevents Totems of Undying from stacking in a player's inventory.

## Description

This plugin enforces a "one totem per slot" rule for Totems of Undying. It automatically unstacks any existing totem stacks and prevents players from creating new ones. This can be useful for balancing gameplay or for server setups where stacked totems are undesirable.

## Features

*   **Automatic Unstacking:** Upon player login or inventory closure, the plugin automatically separates any stacks of Totems of Undying into individual items.
*   **Stacking Prevention:** Prevents players from manually stacking Totems of Undying during inventory interactions (e.g., moving items, crafting).
*   **Inventory Management:** Handles cases where the player's inventory is full by dropping any extra totems at the player's location, ensuring no totems are lost.

## Installation

1.  Download the latest `NoStackTotems.jar` file from the [Releases](https://github.com/Daniel-Farmer/NoStackTotems/tree/Releases]) section.
2.  Place the `NoStackTotems.jar` file into the `plugins` folder of your Spigot or Paper Minecraft server.
3.  Restart the server.

## Configuration

This plugin requires no configuration.  The default behavior is to always prevent totem stacking.

## Dependencies

*   Spigot or Paper Minecraft server (version 1.19 or higher)

## Usage

The plugin works automatically.  Players will no longer be able to stack Totems of Undying in their inventories.

## Permissions

This plugin does not use any permissions. All players are affected equally.

## Support

For bug reports or feature requests, please open an issue on this GitHub repository.

## License

[Choose a license, e.g., MIT License]

---

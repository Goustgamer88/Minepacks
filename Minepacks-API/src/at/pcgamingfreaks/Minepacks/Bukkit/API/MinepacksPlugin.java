/*
 *   Copyright (C) 2020 GeorgH93
 *
 *   This program is free software: you can redistribute it and/or modify
 *   it under the terms of the GNU General Public License as published by
 *   the Free Software Foundation, either version 3 of the License, or
 *   (at your option) any later version.
 *
 *   This program is distributed in the hope that it will be useful,
 *   but WITHOUT ANY WARRANTY; without even the implied warranty of
 *   MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 *   GNU General Public License for more details.
 *
 *   You should have received a copy of the GNU General Public License
 *   along with this program. If not, see <http://www.gnu.org/licenses/>.
 */

package at.pcgamingfreaks.Minepacks.Bukkit.API;

import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@SuppressWarnings("unused")
public interface MinepacksPlugin
{
	/**
	 * Gets the instance of the Minepacks plugin.
	 * WARNING use this function at your own risk! If the plugin is not installed the MinepacksPlugin class will be unknown!
	 *
	 * @return The instance of the Minepacks plugin.
	 */
	static @Nullable MinepacksPlugin getInstance()
	{
		Plugin plugin = Bukkit.getServer().getPluginManager().getPlugin("Minepacks");
		return (plugin instanceof MinepacksPlugin && plugin.isEnabled()) ? (MinepacksPlugin) plugin : null;
	}

	/**
	 * Checks if the plugin is running in standalone mode. Some features and API functions are not available in standalone mode!
	 *
	 * @return True if the plugin is running in standalone mode.
	 */
	boolean isRunningInStandaloneMode();

	/**
	 * Let a given player open the backpack of an other player.
	 *
	 * @param opener   The player who opens the backpack.
	 * @param owner    The owner of the backpack that should be opened.
	 * @param editable Defines if the player who has opened the backpack can change the items inside.
	 */
	@Deprecated
	default void openBackpack(@NotNull final Player opener, @NotNull final MinepacksPlayer owner, final boolean editable)
	{
		openBackpack(opener, owner, editable, null);
	}

	/**
	 * Let a given player open the backpack of an other player.
	 *
	 * @param opener   The player who opens the backpack.
	 * @param owner    The owner of the backpack that should be opened.
	 * @param editable Defines if the player who has opened the backpack can change the items inside.
	 * @deprecated     Use {@link #openBackpack(Player, MinepacksPlayer, boolean)} instead!
	 */
	@Deprecated
	default void openBackpack(@NotNull final Player opener, @NotNull final OfflinePlayer owner, final boolean editable)
	{
		openBackpack(opener, owner, editable, null);
	}

	/**
	 * Let a given player open a given {@link Backpack}.
	 *
	 * @param opener   The player who opens the backpack.
	 * @param backpack The backpack to be opened. null will result in an error message for the player.
	 * @param editable Defines if the player who has opened the backpack can change the items inside.
	 */
	default void openBackpack(@NotNull final Player opener, @NotNull final Backpack backpack, boolean editable)
	{
		openBackpack(opener, backpack, editable, null);
	}

	/**
	 * Let a given player open the backpack of an other player.
	 *
	 * @param opener   The player who opens the backpack.
	 * @param owner    The owner of the backpack that should be opened.
	 * @param editable Defines if the player who has opened the backpack can change the items inside.
	 * @param title    Custom title for the backpack (will be shown to the player who opened the backpack.
	 */
	void openBackpack(@NotNull final Player opener, @NotNull final MinepacksPlayer owner, final boolean editable, @Nullable String title);

	/**
	 * Let a given player open the backpack of an other player.
	 *
	 * @param opener   The player who opens the backpack.
	 * @param owner    The owner of the backpack that should be opened.
	 * @param editable Defines if the player who has opened the backpack can change the items inside.
	 * @param title    Custom title for the backpack (will be shown to the player who opened the backpack.
	 * @deprecated     Use {@link #openBackpack(Player, MinepacksPlayer, boolean, String)} instead!
	 */
	@Deprecated
	default void openBackpack(@NotNull final Player opener, @NotNull final OfflinePlayer owner, final boolean editable, @Nullable String title)
	{
		openBackpack(opener, getMinepacksPlayer(owner), editable, title);
	}

	/**
	 * Let a given player open a given {@link Backpack}.
	 *
	 * @param opener   The player who opens the backpack.
	 * @param backpack The backpack to be opened.
	 * @param editable Defines if the player who has opened the backpack can change the items inside.
	 * @param title    Custom title for the backpack (will be shown to the player who opened the backpack.
	 */
	void openBackpack(@NotNull final Player opener, @NotNull final Backpack backpack, boolean editable, @Nullable String title);

	/**
	 * Retrieves the backpack for a given player.
	 * This method only returns a backpack if it is in the cache.
	 *
	 * @param owner The player who's backpack should be retrieved.
	 * @return The backpack of the given player. null if the backpack is not loaded.
	 * @deprecated Replaced by {@link MinepacksPlugin#getBackpackLoadedOnly(OfflinePlayer)}!
	 */
	@Deprecated
	default @Nullable Backpack getBackpackCachedOnly(@NotNull final OfflinePlayer owner)
	{
		return getBackpackLoadedOnly(owner);
	}

	/**
	 * Retrieves the backpack for a given player.
	 * This method only returns a backpack if it is fully loaded.
	 *
	 * @param owner The player who's backpack should be retrieved.
	 * @return The backpack of the given player. null if the backpack is not loaded.
	 */
	@Nullable Backpack getBackpackLoadedOnly(final @NotNull OfflinePlayer owner);

	/**
	 * Retrieves the backpack for a given player.
	 * This method runs async! The result will be delivered with a callback.
	 * If no backpack exists a new one will be created.
	 *
	 * @param owner The player who's backpack should be retrieved.
	 * @param callback The callback delivering the result of the request.
	 */
	void getBackpack(@NotNull final OfflinePlayer owner, @NotNull final Callback<Backpack> callback);

	/**
	 * Retrieves the backpack for a given player.
	 * This method runs async! The result will be delivered with a callback.
	 *
	 * @param owner The player who's backpack should be retrieved.
	 * @param callback The callback delivering the result of the request.
	 * @param createNewIfNotExists If set to true, a new backpack will be created if there currently is no backpack for this player.
	 */
	@Deprecated
	default void getBackpack(@NotNull final OfflinePlayer owner, @NotNull final Callback<Backpack> callback, boolean createNewIfNotExists)
	{
		getBackpack(owner, callback);
	}

	/**
	 * Retrieves the {@link MinepacksPlayer} representing a given player.
	 * The player might not have been loaded by now. Make sure to check the {@link MinepacksPlayer#isLoaded()} state.
	 *
	 * @param player The player for which the data should be retrieved.
	 * @return The players data.
	 */
	@NotNull MinepacksPlayer getMinepacksPlayer(final @NotNull OfflinePlayer player);

	/**
	 * Retrieves the {@link MinepacksPlayer} representing a given player.
	 * The callback will trigger one the player is fully loaded.
	 *
	 * @param player The player for which the data should be retrieved.
	 * @param callback The callback to be called once the player data is fully loaded.
	 */
	void getMinepacksPlayer(final @NotNull OfflinePlayer player, final @NotNull Callback<MinepacksPlayer> callback);

	/**
	 * Retrieves the {@link MinepacksPlayer} representing a given player.
	 * If the player is not loaded it will return null.
	 *
	 * @param player The player for which the data should be retrieved.
	 * @return The players data. Null if the player is not loaded.
	 */
	@Nullable MinepacksPlayer getMinepacksPlayerLoadedOnly(final @NotNull OfflinePlayer player);

	/**
	 * Checks if the player is allowed to open a backpack based on is permissions and current game-mode.
	 *
	 * @param player The player to be checked.
	 * @return True if the player can use a backpack. False if not.
	 */
	boolean isPlayerGameModeAllowed(final @NotNull Player player);

	/**
	 * Gets the item filter.
	 *
	 * @return The item filter. Null if item filter is disabled
	 */
	@Nullable ItemFilter getItemFilter();

	/**
	 * Checks if an item is a backpack shortcut item.
	 *
	 * @param itemStack item to check.
	 * @return true if the item is a backpack shortcut item, false if not.
	 */
	boolean isBackpackItem(final @Nullable ItemStack itemStack);

	/**
	 * Checks if a player can use the backpack at its current location (or if the world is disabled in the config).
	 * <b>Does not check if the player has the permission to use the backpack!!!</b>
	 *
	 * @param player The player that should be checked.
	 * @return The configured reason why the player can not access at its current location. {@link WorldBlacklistMode#None} if the player can use the backpack.
	 */
	@NotNull WorldBlacklistMode isDisabled(Player player);
}
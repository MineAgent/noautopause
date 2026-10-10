// SPDX-License-Identifier: LGPL-3.0-only
// Copyright (C) 2026 MineAgent

package com.mineagent.noautopause;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.loader.api.FabricLoader;

import java.util.logging.Logger;

/**
 * Client entrypoint of NoAutoPause.
 *
 * <p>There is nothing to set up here, and deliberately no Minecraft class is touched at this point:
 * the pause flag is cleared from a mixin on {@code Minecraft#tick} rather than from a
 * {@code ClientTickEvents} listener (this mod does not depend on the Fabric API). The initializer
 * only reports the loaded build; {@link NoAutoPause#tick} then logs the actual flag change on the
 * first tick.</p>
 */
public final class NoAutoPauseMod implements ClientModInitializer {
	private static final Logger LOG = Logger.getLogger("noautopause");
	private static final String MOD_ID = "noautopause";

	@Override
	public void onInitializeClient() {
		LOG.info("NoAutoPause " + version() + " loaded");
	}

	/**
	 * The version this jar was built as.
	 *
	 * <p>{@code Package#getImplementationVersion()} returns null here: the manifest attribute only
	 * reaches a package that the class loader defines with it, which is not what Fabric's loader
	 * does. The loader's own metadata has it.</p>
	 */
	static String version() {
		return FabricLoader.getInstance()
				.getModContainer(MOD_ID)
				.map(container -> container.getMetadata().getVersion().getFriendlyString())
				.orElse("dev");
	}
}

// SPDX-License-Identifier: LGPL-3.0-only
// Copyright (C) 2026 MineAgent

package com.mineagent.noautopause.mixin;

import com.mineagent.noautopause.NoAutoPause;
import net.minecraft.client.Minecraft;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

/**
 * Clears {@code Options#pauseOnLostFocus} once, at the end of {@code Minecraft#tick}.
 *
 * <p>The mod does not use the Fabric API, so instead of a lifecycle event the tick is injected
 * directly. The first tick happens before the player can unfocus the window, and after that first
 * call {@link NoAutoPause#tick} is a no-op.</p>
 */
@Mixin(Minecraft.class)
public abstract class MinecraftMixin {
	@Inject(method = "tick()V", at = @At("TAIL"))
	private void noautopause$tick(CallbackInfo ci) {
		NoAutoPause.tick((Minecraft) (Object) this);
	}
}

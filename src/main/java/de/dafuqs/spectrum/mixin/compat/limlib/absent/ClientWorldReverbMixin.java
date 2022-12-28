package de.dafuqs.spectrum.mixin.compat.limlib.absent;

import de.dafuqs.spectrum.*;
import de.dafuqs.spectrum.compat.liminal_library.*;
import net.fabricmc.api.*;
import net.fabricmc.loader.api.*;
import net.minecraft.client.network.*;
import net.minecraft.client.render.*;
import net.minecraft.client.world.*;
import net.minecraft.util.registry.*;
import org.spongepowered.asm.mixin.*;
import org.spongepowered.asm.mixin.injection.*;
import org.spongepowered.asm.mixin.injection.callback.*;

import java.util.function.*;

@Environment(EnvType.CLIENT)
@Mixin(ClientWorld.class)
public abstract class ClientWorldReverbMixin {
	
	@Inject(method = "<init>", at = @At("TAIL"))
	private void spectrum$init(ClientPlayNetworkHandler netHandler, ClientWorld.Properties properties, RegistryKey registryRef, RegistryEntry registryEntry, int loadDistance, int simulationDistance, Supplier profiler, WorldRenderer worldRenderer, boolean debugWorld, long seed, CallbackInfo ci) {
		ClientWorld clientWorld = (ClientWorld) (Object) this;
		if(clientWorld.getDimension().effects().equals(SpectrumCommon.locate("deeper_down")) && FabricLoader.getInstance().isModLoaded("limlib")) {
			LiminalDimensionReverb.setReverbForClientDimension(clientWorld);
		}
	}
	
}

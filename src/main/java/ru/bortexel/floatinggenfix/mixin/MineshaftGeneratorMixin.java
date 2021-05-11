package ru.bortexel.floatinggenfix.mixin;

import net.minecraft.structure.MineshaftGenerator;
import net.minecraft.structure.StructurePiece;
import net.minecraft.util.math.Direction;
import net.minecraft.world.gen.feature.MineshaftFeature;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.List;
import java.util.Random;

@Mixin(MineshaftGenerator.class)
public class MineshaftGeneratorMixin {
    @Inject(method = "pickPiece", at = @At("HEAD"), cancellable = true)
    private static void pickPiece(List<StructurePiece> pieces, Random random, int x, int y, int z, @Nullable Direction orientation, int chainLength, MineshaftFeature.Type type, CallbackInfoReturnable<?> info) {
        info.setReturnValue(null);
    }
}

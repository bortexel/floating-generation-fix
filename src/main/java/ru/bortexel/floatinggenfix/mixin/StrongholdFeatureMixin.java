package ru.bortexel.floatinggenfix.mixin;

import net.minecraft.structure.StructureManager;
import net.minecraft.structure.StructureStart;
import net.minecraft.util.math.BlockBox;
import net.minecraft.util.math.Direction;
import net.minecraft.util.registry.DynamicRegistryManager;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.chunk.ChunkGenerator;
import net.minecraft.world.gen.feature.DefaultFeatureConfig;
import net.minecraft.world.gen.feature.StrongholdFeature;
import net.minecraft.world.gen.feature.StructureFeature;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import ru.bortexel.floatinggenfix.StrongholdGenerator;

public class StrongholdFeatureMixin {
    @Mixin(StrongholdFeature.Start.class)
    public static abstract class Start extends StructureStart<DefaultFeatureConfig> {
        private final long seed;

        public Start(StructureFeature<DefaultFeatureConfig> structureFeature, int i, int j, BlockBox blockBox, int k, long l) {
            super(structureFeature, i, j, blockBox, k, l);
            this.seed = l;
        }

        @Inject(method = "init", at = @At("HEAD"), cancellable = true)
        public void init(DynamicRegistryManager dynamicRegistryManager, ChunkGenerator chunkGenerator, StructureManager structureManager, int i, int j, Biome biome, DefaultFeatureConfig defaultFeatureConfig, CallbackInfo info) {
            this.children.clear();
            int x = (i << 4) + 2;
            int z = (j << 4) + 2;

            Direction orientation = Direction.random(this.random);
            this.boundingBox = BlockBox.rotated(x, 36, z, -4, -1, 0, 11, 8, 16, orientation);
            this.random.setCarverSeed(this.seed, i, j);
            StrongholdGenerator.Start start = new StrongholdGenerator.Start(this.boundingBox, orientation);
            this.children.add(start);

            this.setBoundingBoxFromChildren();
            this.randomUpwardTranslation(this.random, 24, 36);

            info.cancel();
        }
    }
}

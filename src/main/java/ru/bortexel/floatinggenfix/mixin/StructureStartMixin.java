package ru.bortexel.floatinggenfix.mixin;

import net.minecraft.block.BlockState;
import net.minecraft.structure.StructureStart;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockBox;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.StructureWorldAccess;
import net.minecraft.world.gen.StructureAccessor;
import net.minecraft.world.gen.chunk.ChunkGenerator;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Random;

@Mixin(StructureStart.class)
public class StructureStartMixin {
    @Inject(method = "generateStructure", at = @At("HEAD"), cancellable = true)
    public void generateStructure(StructureWorldAccess world, StructureAccessor structureAccessor, ChunkGenerator chunkGenerator, Random random, BlockBox box, ChunkPos chunkPos, CallbackInfo info) {
        BlockPos pos1 = new BlockPos(chunkPos.getStartX(), 48, chunkPos.getStartZ());
        BlockPos pos2 = new BlockPos(chunkPos.getEndX(), 48, chunkPos.getEndZ());
        if (isUnsuitable(world, pos1) || isUnsuitable(world, pos2)) info.cancel();
    }

    /**
     * Checks if structure can not be generated at given position
     * @param world World instance
     * @param pos Position
     * @return true if structure should not be generated, false if can be generated
     */
    private boolean isUnsuitable(StructureWorldAccess world, BlockPos pos) {
        BlockState blockState = world.getBlockState(pos);
        Identifier id = Registry.BLOCK.getId(blockState.getBlock());
        return id.getPath().equals("air");
    }
}

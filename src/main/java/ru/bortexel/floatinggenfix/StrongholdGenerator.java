package ru.bortexel.floatinggenfix;

import net.minecraft.block.*;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.MobSpawnerBlockEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.structure.StructurePiece;
import net.minecraft.structure.StructurePieceType;
import net.minecraft.util.math.BlockBox;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.StructureWorldAccess;
import net.minecraft.world.gen.StructureAccessor;
import net.minecraft.world.gen.chunk.ChunkGenerator;

import java.util.Random;

public class StrongholdGenerator {
    private static final StoneBrickRandomizer STONE_BRICK_RANDOMIZER = new StoneBrickRandomizer();

    public static class Start extends PortalRoom {
        public Start(BlockBox boundingBox, Direction orientation) {
            super(boundingBox, orientation);
        }
    }

    public static class PortalRoom extends Piece {
        private boolean spawnerPlaced;

        public PortalRoom(BlockBox boundingBox, Direction orientation) {
            super(StructurePieceType.STRONGHOLD_PORTAL_ROOM, 0);
            this.setOrientation(orientation);
            this.boundingBox = boundingBox;
        }

        public boolean generate(StructureWorldAccess structureWorldAccess, StructureAccessor structureAccessor, ChunkGenerator chunkGenerator, Random random, BlockBox boundingBox, ChunkPos chunkPos, BlockPos blockPos) {
            this.fillWithOutline(structureWorldAccess, boundingBox, 0, 0, 0, 10, 7, 15, false, random, STONE_BRICK_RANDOMIZER);
            this.generateEntrance(structureWorldAccess, boundingBox);
            int i = 6;
            this.fillWithOutline(structureWorldAccess, boundingBox, 1, i, 1, 1, i, 14, false, random, STONE_BRICK_RANDOMIZER);
            this.fillWithOutline(structureWorldAccess, boundingBox, 9, i, 1, 9, i, 14, false, random, STONE_BRICK_RANDOMIZER);
            this.fillWithOutline(structureWorldAccess, boundingBox, 2, i, 1, 8, i, 2, false, random, STONE_BRICK_RANDOMIZER);
            this.fillWithOutline(structureWorldAccess, boundingBox, 2, i, 14, 8, i, 14, false, random, STONE_BRICK_RANDOMIZER);
            this.fillWithOutline(structureWorldAccess, boundingBox, 1, 1, 1, 2, 1, 4, false, random, STONE_BRICK_RANDOMIZER);
            this.fillWithOutline(structureWorldAccess, boundingBox, 8, 1, 1, 9, 1, 4, false, random, STONE_BRICK_RANDOMIZER);
            this.fillWithOutline(structureWorldAccess, boundingBox, 1, 1, 1, 1, 1, 3, Blocks.LAVA.getDefaultState(), Blocks.LAVA.getDefaultState(), false);
            this.fillWithOutline(structureWorldAccess, boundingBox, 9, 1, 1, 9, 1, 3, Blocks.LAVA.getDefaultState(), Blocks.LAVA.getDefaultState(), false);
            this.fillWithOutline(structureWorldAccess, boundingBox, 3, 1, 8, 7, 1, 12, false, random, STONE_BRICK_RANDOMIZER);
            this.fillWithOutline(structureWorldAccess, boundingBox, 4, 1, 9, 6, 1, 11, Blocks.LAVA.getDefaultState(), Blocks.LAVA.getDefaultState(), false);
            BlockState blockState = Blocks.IRON_BARS.getDefaultState().with(PaneBlock.NORTH, true).with(PaneBlock.SOUTH, true);
            BlockState blockState2 = Blocks.IRON_BARS.getDefaultState().with(PaneBlock.WEST, true).with(PaneBlock.EAST, true);

            int k;
            for (k = 3; k < 14; k += 2) {
                this.fillWithOutline(structureWorldAccess, boundingBox, 0, 3, k, 0, 4, k, blockState, blockState, false);
                this.fillWithOutline(structureWorldAccess, boundingBox, 10, 3, k, 10, 4, k, blockState, blockState, false);
            }

            for (k = 2; k < 9; k += 2) {
                this.fillWithOutline(structureWorldAccess, boundingBox, k, 3, 15, k, 4, 15, blockState2, blockState2, false);
            }

            BlockState blockState3 = Blocks.STONE_BRICK_STAIRS.getDefaultState().with(StairsBlock.FACING, Direction.NORTH);
            this.fillWithOutline(structureWorldAccess, boundingBox, 4, 1, 5, 6, 1, 7, false, random, STONE_BRICK_RANDOMIZER);
            this.fillWithOutline(structureWorldAccess, boundingBox, 4, 2, 6, 6, 2, 7, false, random, STONE_BRICK_RANDOMIZER);
            this.fillWithOutline(structureWorldAccess, boundingBox, 4, 3, 7, 6, 3, 7, false, random, STONE_BRICK_RANDOMIZER);

            for (int l = 4; l <= 6; ++l) {
                this.addBlock(structureWorldAccess, blockState3, l, 1, 4, boundingBox);
                this.addBlock(structureWorldAccess, blockState3, l, 2, 5, boundingBox);
                this.addBlock(structureWorldAccess, blockState3, l, 3, 6, boundingBox);
            }

            BlockState blockState4 = Blocks.END_PORTAL_FRAME.getDefaultState().with(EndPortalFrameBlock.FACING, Direction.NORTH);
            BlockState blockState5 = Blocks.END_PORTAL_FRAME.getDefaultState().with(EndPortalFrameBlock.FACING, Direction.SOUTH);
            BlockState blockState6 = Blocks.END_PORTAL_FRAME.getDefaultState().with(EndPortalFrameBlock.FACING, Direction.EAST);
            BlockState blockState7 = Blocks.END_PORTAL_FRAME.getDefaultState().with(EndPortalFrameBlock.FACING, Direction.WEST);

            boolean bl = true;
            boolean[] bls = new boolean[12];

            for (int m = 0; m < bls.length; ++m) {
                bls[m] = random.nextFloat() > 0.9F;
                bl &= bls[m];
            }

            this.addBlock(structureWorldAccess, blockState4.with(EndPortalFrameBlock.EYE, bls[0]), 4, 3, 8, boundingBox);
            this.addBlock(structureWorldAccess, blockState4.with(EndPortalFrameBlock.EYE, bls[1]), 5, 3, 8, boundingBox);
            this.addBlock(structureWorldAccess, blockState4.with(EndPortalFrameBlock.EYE, bls[2]), 6, 3, 8, boundingBox);
            this.addBlock(structureWorldAccess, blockState5.with(EndPortalFrameBlock.EYE, bls[3]), 4, 3, 12, boundingBox);
            this.addBlock(structureWorldAccess, blockState5.with(EndPortalFrameBlock.EYE, bls[4]), 5, 3, 12, boundingBox);
            this.addBlock(structureWorldAccess, blockState5.with(EndPortalFrameBlock.EYE, bls[5]), 6, 3, 12, boundingBox);
            this.addBlock(structureWorldAccess, blockState6.with(EndPortalFrameBlock.EYE, bls[6]), 3, 3, 9, boundingBox);
            this.addBlock(structureWorldAccess, blockState6.with(EndPortalFrameBlock.EYE, bls[7]), 3, 3, 10, boundingBox);
            this.addBlock(structureWorldAccess, blockState6.with(EndPortalFrameBlock.EYE, bls[8]), 3, 3, 11, boundingBox);
            this.addBlock(structureWorldAccess, blockState7.with(EndPortalFrameBlock.EYE, bls[9]), 7, 3, 9, boundingBox);
            this.addBlock(structureWorldAccess, blockState7.with(EndPortalFrameBlock.EYE, bls[10]), 7, 3, 10, boundingBox);
            this.addBlock(structureWorldAccess, blockState7.with(EndPortalFrameBlock.EYE, bls[11]), 7, 3, 11, boundingBox);

            if (bl) {
                BlockState blockState8 = Blocks.END_PORTAL.getDefaultState();
                this.addBlock(structureWorldAccess, blockState8, 4, 3, 9, boundingBox);
                this.addBlock(structureWorldAccess, blockState8, 5, 3, 9, boundingBox);
                this.addBlock(structureWorldAccess, blockState8, 6, 3, 9, boundingBox);
                this.addBlock(structureWorldAccess, blockState8, 4, 3, 10, boundingBox);
                this.addBlock(structureWorldAccess, blockState8, 5, 3, 10, boundingBox);
                this.addBlock(structureWorldAccess, blockState8, 6, 3, 10, boundingBox);
                this.addBlock(structureWorldAccess, blockState8, 4, 3, 11, boundingBox);
                this.addBlock(structureWorldAccess, blockState8, 5, 3, 11, boundingBox);
                this.addBlock(structureWorldAccess, blockState8, 6, 3, 11, boundingBox);
            }

            if (!this.spawnerPlaced) {
                int j = this.applyYTransform(3);
                BlockPos blockPos2 = new BlockPos(this.applyXTransform(5, 6), j, this.applyZTransform(5, 6));
                if (boundingBox.contains(blockPos2)) {
                    this.spawnerPlaced = true;
                    structureWorldAccess.setBlockState(blockPos2, Blocks.SPAWNER.getDefaultState(), 2);
                    BlockEntity blockEntity = structureWorldAccess.getBlockEntity(blockPos2);
                    if (blockEntity instanceof MobSpawnerBlockEntity) {
                        ((MobSpawnerBlockEntity) blockEntity).getLogic().setEntityId(EntityType.SILVERFISH);
                    }
                }
            }

            return true;
        }
    }

    abstract static class Piece extends StructurePiece {
        protected Piece.EntranceType entryDoor;

        protected Piece(StructurePieceType structurePieceType, int i) {
            super(structurePieceType, i);
            this.entryDoor = Piece.EntranceType.OPENING;
        }

        public Piece(StructurePieceType structurePieceType, CompoundTag compoundTag) {
            super(structurePieceType, compoundTag);
            this.entryDoor = Piece.EntranceType.OPENING;
            this.entryDoor = Piece.EntranceType.valueOf(compoundTag.getString("EntryDoor"));
        }

        protected void toNbt(CompoundTag tag) {
            tag.putString("EntryDoor", this.entryDoor.name());
        }

        protected void generateEntrance(StructureWorldAccess structureWorldAccess, BlockBox boundingBox) {
            this.addBlock(structureWorldAccess, Blocks.CAVE_AIR.getDefaultState(), 4 + 1, 1, 0, boundingBox);
            this.addBlock(structureWorldAccess, Blocks.CAVE_AIR.getDefaultState(), 4 + 1, 1 + 1, 0, boundingBox);
            this.addBlock(structureWorldAccess, Blocks.IRON_BARS.getDefaultState().with(PaneBlock.WEST, true), 4, 1, 0, boundingBox);
            this.addBlock(structureWorldAccess, Blocks.IRON_BARS.getDefaultState().with(PaneBlock.WEST, true), 4, 1 + 1, 0, boundingBox);
            this.addBlock(structureWorldAccess, Blocks.IRON_BARS.getDefaultState().with(PaneBlock.EAST, true).with(PaneBlock.WEST, true), 4, 1 + 2, 0, boundingBox);
            this.addBlock(structureWorldAccess, Blocks.IRON_BARS.getDefaultState().with(PaneBlock.EAST, true).with(PaneBlock.WEST, true), 4 + 1, 1 + 2, 0, boundingBox);
            this.addBlock(structureWorldAccess, Blocks.IRON_BARS.getDefaultState().with(PaneBlock.EAST, true).with(PaneBlock.WEST, true), 4 + 2, 1 + 2, 0, boundingBox);
            this.addBlock(structureWorldAccess, Blocks.IRON_BARS.getDefaultState().with(PaneBlock.EAST, true), 4 + 2, 1 + 1, 0, boundingBox);
            this.addBlock(structureWorldAccess, Blocks.IRON_BARS.getDefaultState().with(PaneBlock.EAST, true), 4 + 2, 1, 0, boundingBox);
        }

        public enum EntranceType {
            OPENING
        }
    }

    static class StoneBrickRandomizer extends StructurePiece.BlockRandomizer {
        private StoneBrickRandomizer() {
        }

        public void setBlock(Random random, int x, int y, int z, boolean placeBlock) {
            if (placeBlock) {
                float f = random.nextFloat();
                if (f < 0.2F) {
                    this.block = Blocks.CRACKED_STONE_BRICKS.getDefaultState();
                } else if (f < 0.5F) {
                    this.block = Blocks.MOSSY_STONE_BRICKS.getDefaultState();
                } else if (f < 0.55F) {
                    this.block = Blocks.INFESTED_STONE_BRICKS.getDefaultState();
                } else {
                    this.block = Blocks.STONE_BRICKS.getDefaultState();
                }
            } else {
                this.block = Blocks.CAVE_AIR.getDefaultState();
            }
        }
    }
}

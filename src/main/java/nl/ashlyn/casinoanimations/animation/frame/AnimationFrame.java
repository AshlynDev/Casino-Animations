package nl.ashlyn.casinoanimations.animation.frame;

import nl.ashlyn.casinoanimations.api.util.BlockVector;
import lombok.Getter;
import lombok.Setter;
import org.bukkit.block.data.BlockData;

import java.util.Map;

@Getter
@Setter
public class AnimationFrame {

    private Map<BlockVector, BlockData> blockVectors;
    private int delay;

    public AnimationFrame(Map<BlockVector, BlockData> blockVectors) {
        this.blockVectors = blockVectors;
    }

    public AnimationFrame(Map<BlockVector, BlockData> blockVectors, int delay) {
        this.blockVectors = blockVectors;
        this.delay = delay;
    }
}
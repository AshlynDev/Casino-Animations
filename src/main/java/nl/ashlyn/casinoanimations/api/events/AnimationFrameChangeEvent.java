package nl.ashlyn.casinoanimations.api.events;

import nl.ashlyn.casinoanimations.animation.IAnimation;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public class AnimationFrameChangeEvent extends Event {

    private static final HandlerList handlers = new HandlerList();

    private final IAnimation animation;

    public AnimationFrameChangeEvent(IAnimation animation) {
        this.animation = animation;
    }

    public IAnimation getAnimation() {
        return this.animation;
    }

    public HandlerList getHandlers() {
        return handlers;
    }

    public static HandlerList getHandlerList() {
        return handlers;
    }
}
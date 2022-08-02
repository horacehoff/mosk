package com.mango.anosk.mosk.gui;


import com.mojang.blaze3d.systems.RenderSystem;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.gui.screen.ingame.HandledScreen;
import net.minecraft.client.render.GameRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

@Environment(EnvType.CLIENT)
public class HupperScreen extends HandledScreen<HupperScreenHandler> {
    private static final Identifier TEXTURE = new Identifier("textures/gui/container/hupper.png");

    public HupperScreen(HupperScreenHandler handler, PlayerInventory inventory, Text title) {
        super(handler, inventory, title);
        this.passEvents = false;
        this.backgroundHeight = 133;
        this.playerInventoryTitleY = this.backgroundHeight - 94;
    }

    /**
     * This function renders the background, then calls the superclass's render function, then draws the mouseover tooltip.
     *
     * @param matrices The matrix stack.
     * @param mouseX The X position of the mouse.
     * @param mouseY The y coordinate of the mouse.
     * @param delta The time in seconds since the last frame.
     */
    public void render(MatrixStack matrices, int mouseX, int mouseY, float delta) {
        this.renderBackground(matrices);
        super.render(matrices, mouseX, mouseY, delta);
        this.drawMouseoverTooltip(matrices, mouseX, mouseY);
    }

    /**
     * Draws the background texture of the screen.
     *
     * @param matrices The matrix stack used to draw the background.
     * @param delta The time in seconds since the last frame was rendered.
     * @param mouseX The X position of the mouse.
     * @param mouseY The y position of the mouse
     */
    protected void drawBackground(MatrixStack matrices, float delta, int mouseX, int mouseY) {
        RenderSystem.setShader(GameRenderer::getPositionTexShader);
        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
        RenderSystem.setShaderTexture(0, TEXTURE);
        int i = (this.width - this.backgroundWidth) / 2;
        int j = (this.height - this.backgroundHeight) / 2;
        this.drawTexture(matrices, i, j, 0, 0, this.backgroundWidth, this.backgroundHeight);
    }
}

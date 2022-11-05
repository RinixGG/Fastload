package io.github.bumblesoftware.fastload.util.screen;

import io.github.bumblesoftware.fastload.FastLoad;
import io.github.bumblesoftware.fastload.config.FLMath;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawableHelper;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.text.Text;

public class BuildingTerrainScreen extends Screen {
    private final Text text;
    private final MinecraftClient client = MinecraftClient.getInstance();
    private int getLoadedChunkCount() {
        return client.world != null ? client.world.getChunkManager().getLoadedChunkCount() : 0;
    }
    public BuildingTerrainScreen() {
        super(Text.literal("Building Terrain"));
        text = Text.literal("Building Terrain");
    }
    @Override
    public void render(MatrixStack matrices, int mouseX, int mouseY, float delta) {
        this.renderBackgroundTexture(0);
        int white = 0xFFFFFF;
        int heightUpFromCentre = 50;
        String percentage = getLoadedChunkCount()/FLMath.getPreRenderRadius() + "%";
        FastLoad.LOGGER.info("Terrain Building Progress; " + percentage);
        DrawableHelper.drawCenteredText(matrices, this.textRenderer, text, this.width / 2, this.height / 2 - heightUpFromCentre, white);
        DrawableHelper.drawCenteredText(
                matrices,
                this.textRenderer,
                percentage,
                width / 2,
                height / 2 - heightUpFromCentre + 15,
                white);
        super.render(matrices, mouseX, mouseY, delta);
    }
    @Override
    public boolean shouldCloseOnEsc() {
        return getLoadedChunkCount() <= FLMath.getPreRenderRadius() / 4;
    }
}
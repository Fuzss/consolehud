package com.fuzs.consoleexperience.handler;

import com.fuzs.consoleexperience.util.PositionPreset;
import com.mojang.blaze3d.platform.GlStateManager;
import net.minecraft.client.MainWindow;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.AbstractGui;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class CoordinateDisplayHandler {

    private final Minecraft mc = Minecraft.getInstance();

    @SuppressWarnings("unused")
    @SubscribeEvent
    public void renderGameOverlayPre(RenderGameOverlayEvent.Text evt) {

        if (!ConfigBuildHandler.GENERAL_CONFIG.coordinateDisplay.get() || this.mc.gameSettings.showDebugInfo) {
            return;
        }

        GlStateManager.pushMatrix();
        GlStateManager.enableBlend();
        GlStateManager.blendFuncSeparate(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA,
                GlStateManager.SourceFactor.ONE, GlStateManager.DestFactor.ZERO);

        ITextComponent component;
        double d = Math.pow(10, ConfigBuildHandler.COORDINATE_DISPLAY_CONFIG.decimalPlaces.get());
        double posX = Math.round(this.mc.player.posX * d) / d;
        double posY = Math.round(this.mc.player.getBoundingBox().minY * d) / d;
        double posZ = Math.round(this.mc.player.posZ * d) / d;

        if (ConfigBuildHandler.COORDINATE_DISPLAY_CONFIG.decimalPlaces.get() == 0) {
            // no empty decimal place added like this
            component = new TranslationTextComponent("screen.coordinates", (int) posX, (int) posY, (int) posZ);
        } else {
            component = new TranslationTextComponent("screen.coordinates", posX, posY, posZ);
        }

        MainWindow window = evt.getWindow();
        int f = (int) ((this.mc.gameSettings.chatOpacity * 0.9f + 0.1f) * 255.0f);
        int k = this.mc.fontRenderer.getStringWidth(component.getString()) + 3;
        int l = 7 + 4;

        PositionPreset position = ConfigBuildHandler.COORDINATE_DISPLAY_CONFIG.position.get();
        int x = position.getX(k, window.getScaledWidth(), ConfigBuildHandler.COORDINATE_DISPLAY_CONFIG.xOffset.get());
        int y = position.getY(l, window.getScaledHeight(), ConfigBuildHandler.COORDINATE_DISPLAY_CONFIG.yOffset.get());

        if (ConfigBuildHandler.COORDINATE_DISPLAY_CONFIG.background.get()) {
            AbstractGui.fill(x, y, x + k, y + l, f / 2 << 24);
        }

        this.mc.fontRenderer.drawStringWithShadow(component.getFormattedText(), x + 2, y + 2, 16777215 + (f << 24));

        GlStateManager.disableBlend();
        GlStateManager.popMatrix();
        GlStateManager.color4f(1.0F, 1.0F, 1.0F, 1.0F);

    }

}

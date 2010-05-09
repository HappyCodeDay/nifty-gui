package de.lessvoid.nifty.render;

import de.lessvoid.nifty.spi.render.RenderImage;
import de.lessvoid.nifty.tools.Color;

/**
 * NiftyImage.
 * @author void
 */
public class NiftyImage {
  private RenderImage image;
  private NiftyImageMode subImageMode;
  private NiftyRenderEngine niftyRenderEngine;

  /**
   * create new NiftyImage.
   * @param createImage RenderImage
   */
  public NiftyImage(final NiftyRenderEngine niftyRenderEngine, final RenderImage createImage) {
    this.niftyRenderEngine = niftyRenderEngine;
    this.image = createImage;
    this.subImageMode = NiftyImageMode.normal();
  }

  /**
   * Get the width of the image.
   * @return width of image in pixel
   */
  public int getWidth() {
    return image.getWidth();
  }

  /**
   * Get the height of the image.
   * @return height of image in pixel
   */
  public int getHeight() {
    return image.getHeight();
  }

  /**
   * Render the image using the given Box to specify the render attributes.
   * @param x x
   * @param y y
   * @param width width
   * @param height height
   * @param color color
   * @param scale scale
   */
  public void render(
      final int x, final int y, final int width, final int height, final Color color, final float scale) {
    subImageMode.render(niftyRenderEngine.getRenderDevice(), image, x, y, width, height, color, scale);
  }

  /**
   * Set a new sub image active state.
   * @param newSubImageMode new type
   */
  public void setImageMode(final NiftyImageMode newSubImageMode) {
    this.subImageMode = newSubImageMode;
  }

  public void dispose() {
    niftyRenderEngine.disposeImage(image);
  }
}

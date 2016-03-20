/*
 * Copyright (c) 2015, Nifty GUI Community 
 * All rights reserved. 
 * 
 * Redistribution and use in source and binary forms, with or without 
 * modification, are permitted provided that the following conditions are 
 * met: 
 * 
 *  * Redistributions of source code must retain the above copyright 
 *    notice, this list of conditions and the following disclaimer. 
 *  * Redistributions in binary form must reproduce the above copyright 
 *    notice, this list of conditions and the following disclaimer in the 
 *    documentation and/or other materials provided with the distribution. 
 * 
 * THIS SOFTWARE IS PROVIDED BY THE AUTHOR AND CONTRIBUTORS ``AS IS'' AND 
 * ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE 
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR 
 * PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE AUTHOR OR CONTRIBUTORS BE 
 * LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR 
 * CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF 
 * SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS 
 * INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN 
 * CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) 
 * ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF 
 * THE POSSIBILITY OF SUCH DAMAGE.
 */
package de.lessvoid.nifty;

import de.lessvoid.niftyinternal.InternalNiftyImage;
import de.lessvoid.niftyinternal.accessor.NiftyImageAccessor;

import java.nio.ByteBuffer;

/**
 * A image that can be rendered into a NiftyCanvas or that can be used as a background image for a NiftyNode.
 * @author void
 */
public final class NiftyImage {
  private final InternalNiftyImage impl;

  /**
   * Please use one of the {@link Nifty#createImage()} methods to create a new NiftyImage. You're not supposed to
   * create an instance of this class directly and you're not supposed to extend from this class.
   */
  private NiftyImage(final InternalNiftyImage impl) {
    this.impl = impl;
  }

  /**
   * Get the width of this texture.
   * @return the texture width
   */
  public int getWidth() {
    return impl.getWidth();
  }

  /**
   * Get the height of this texture.
   * @return the texture height
   */
  public int getHeight() {
    return impl.getHeight();
  }

  public void update(final ByteBuffer buffer) {
    impl.update(buffer);
  }

  // package private methods

  InternalNiftyImage getImpl() {
    return impl;
  }

  static NiftyImage newInstance(final InternalNiftyImage internalImage) {
    return new NiftyImage(internalImage);
  }

  static {
    NiftyImageAccessor.DEFAULT = new InternalNiftyImageAccessorImpl();
  }
}

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
package de.lessvoid.nifty.node;

import de.lessvoid.nifty.spi.node.NiftyNode;
import de.lessvoid.nifty.spi.node.NiftyNodeAccessor;
import de.lessvoid.nifty.spi.node.NiftyNodeImpl;
import de.lessvoid.niftyinternal.accessor.NiftyNodeAccessorRegistryAccessor;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by void on 22.08.15.
 */
public class NiftyNodeAccessorRegistry {
  private Map<Class<? extends NiftyNode>, NiftyNodeAccessor> nodeAccessors = new HashMap<>();

  public NiftyNodeAccessorRegistry() {
    registerStandardNodes();
  }

  public void registerNiftyNodeAccessor(final NiftyNodeAccessor<? extends NiftyNode> niftyNodeAccessor) {
    nodeAccessors.put(niftyNodeAccessor.getNodeClass(), niftyNodeAccessor);
  }

  private void registerStandardNodes() {
    registerNiftyNodeAccessor(new NiftyRootNodeAccessor());
    registerNiftyNodeAccessor(new NiftyContentNodeAccessor());
    registerNiftyNodeAccessor(new NiftyBackgroundColorNodeAccessor());
    registerNiftyNodeAccessor(new NiftyTransformationNodeAccessor());

    // layout nodes
    registerNiftyNodeAccessor(new FixedSizeLayoutNodeAccessor());
    registerNiftyNodeAccessor(new PaddingLayoutNodeAccessor());
    registerNiftyNodeAccessor(new StackLayoutNodeAccessor());
    registerNiftyNodeAccessor(new UniformStackLayoutNodeAccessor());
  }

  // friend access

  NiftyNodeImpl getImpl(final NiftyNode niftyNode) {
    NiftyNodeAccessor niftyNodeAccessor = nodeAccessors.get(niftyNode.getClass());
    return niftyNodeAccessor.getImplementation(niftyNode);
  }

  static {
    NiftyNodeAccessorRegistryAccessor.DEFAULT = new NiftyAccessorRegistryAccessorImpl();
  }
}

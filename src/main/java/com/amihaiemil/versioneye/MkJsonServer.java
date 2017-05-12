/**
 * Copyright (c) 2017, Mihai Emil Andronache
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 *  modification, are permitted provided that the following conditions are met:
 * Redistributions of source code must retain the above copyright notice, this
 *  list of conditions and the following disclaimer.
 *  Redistributions in binary form must reproduce the above copyright notice,
 *  this list of conditions and the following disclaimer in the documentation
 *  and/or other materials provided with the distribution.
 * Neither the name of the copyright holder nor the names of its
 *  contributors may be used to endorse or promote products derived from
 *  this software without specific prior written permission.
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
 * AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE
 * DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDER OR CONTRIBUTORS BE LIABLE
 * FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL
 * DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR
 * SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER
 * CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT
 * LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY
 * OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF
 * SUCH DAMAGE.
 */
package com.amihaiemil.versioneye;

import javax.json.Json;

import com.amihaiemil.json.NfJsonObjectBuilder;
import com.amihaiemil.json.NfJsonObjectBuilderImpl;

/**
 * Mock VersionEye server storage with Json. See {@link MkServer}
 * @author Mihai Andronache (amihaiemil@gmail.com)
 * @version $Id$
 * @since 1.0.0
 *
 */
public final class MkJsonServer implements MkServer {

    /**
     * Storage.
     */
    private NfJsonObjectBuilder storage;
    
    /**
     * Ctor.
     */
    public MkJsonServer() {
        this.storage = new NfJsonObjectBuilderImpl();
        MkJsonServer.initServer(this.storage);
    }
    
    @Override
    public NfJsonObjectBuilder storage() {
        return this.storage;
    }

    /**
     * Give the versioneye server a default, initial state.
     * @param storage Json storage.
     */
    private static void initServer(final NfJsonObjectBuilder storage) {
        storage
            .add(
                "ping",
                Json.createObjectBuilder()
                    .add("success", true)
                    .add("message", "pong")
                    .build()
            )
            .add("authenticated", Json.createArrayBuilder().build());
    }
}

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

import java.math.BigDecimal;
import java.math.BigInteger;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;
import javax.json.JsonValue;

import com.amihaiemil.json.Storage;

/**
 * Synchronized Storage for {@link MkServer}.
 * @author Mihai Andronache (amihaiemil@gmail.com)
 * @version $Id$
 * @since 1.0.0
 */
final class SyncStorage implements Storage {

    /**
     * Storage that needs to be synchronized.
     */
    private Storage storage;
    
    /**
     * Ctor.
     * @param storage The storage to be synchronized.
     */
    SyncStorage(final Storage storage) {
        this.storage = storage;
    }
    
    @Override
    public synchronized Storage add(final String name, final JsonValue value) {
        return this.storage.add(name, value);
    }

    @Override
    public synchronized Storage add(final String name, final String value) {
        return this.storage.add(name, value);
    }

    @Override
    public synchronized Storage add(
        final String name, final BigInteger value
    ) {
        return this.storage.add(name, value);
    }

    @Override
    public synchronized Storage add(
        final String name, final BigDecimal value
    ) {
        return this.storage.add(name, value);
    }

    @Override
    public synchronized Storage add(final String name, final int value) {
        return this.storage.add(name, value);
    }

    @Override
    public synchronized Storage add(final String name, final long value) {
        return this.storage.add(name, value);
    }

    @Override
    public synchronized Storage add(final String name, final double value) {
        return this.storage.add(name, value);
    }

    @Override
    public synchronized Storage add(final String name, final boolean value) {
        return this.storage.add(name, value);
    }

    @Override
    public synchronized Storage addNull(final String name) {
        return this.storage.addNull(name);
    }

    @Override
    public synchronized Storage add(
        final String name, final JsonObjectBuilder builder
    ) {
        return this.storage.add(name, builder);
    }

    @Override
    public synchronized Storage add(
        final String name, final JsonArrayBuilder builder
    ) {
        return this.storage.add(name, builder);
    }

    @Override
    public synchronized JsonObject build() {
        return this.storage.build();
    }

}

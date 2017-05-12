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
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;

import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;
import javax.json.JsonValue;

import com.amihaiemil.json.Storage;

/**
 * Implementation of {@link Storage}.
 * @author Mihai Andronache (amihaiemil@gmail.com)
 * @version $Id$
 * @since 1.0.0
 *
 */
final class MkStorage implements Storage {

    /**
     * Json key:value pairs added to this builder.
     */
    private Map<String, Object> values = new LinkedHashMap<>();

    @Override
    public Storage add(final String name, final JsonValue value) {
        this.values.put(name, value);
        return this;
    }

    @Override
    public Storage add(final String name, final String value) {
        this.values.put(name, value);
        return this;
    }

    @Override
    public Storage add(final String name, final BigInteger value) {
        this.values.put(name, value);
        return this;
    }

    @Override
    public Storage add(final String name, final BigDecimal value) {
        this.values.put(name, value);
        return this;
    }

    @Override
    public Storage add(final String name, final int value) {
        this.values.put(name, value);
        return this;
    }

    @Override
    public Storage add(final String name, final long value) {
        this.values.put(name, value);
        return this;
    }

    @Override
    public Storage add(final String name, final double value) {
        this.values.put(name, value);
        return this;
    }

    @Override
    public Storage add(final String name, final boolean value) {
        if(value) {
            this.values.put(name, JsonValue.TRUE);
        } else {
            this.values.put(name, JsonValue.FALSE);
        }
        return this;
    }

    @Override
    public Storage addNull(final String name) {
        this.values.put(name, JsonValue.NULL);
        return this;
    }

    @Override
    public Storage add(
        final String name, final JsonObjectBuilder builder
    ) {
        this.values.put(name, builder.build());
        return this;
    }

    @Override
    public Storage add(
        final String name, final JsonArrayBuilder builder
    ) {
        this.values.put(name, builder.build());
        return this;
    }

    @Override
    public JsonObject build() {
        final JsonObjectBuilder builder = Json.createObjectBuilder();
        for(final Entry<String, Object> pair : this.values.entrySet()) {
            this.addToBuilder(pair, builder);
        }
        return builder.build();
    }
    
    /**
     * Add an entry from the map to the given {@link JsonObjectBuilder}.
     * @param pair Map entry.
     * @param builder JsonObjectBuilder
     * @checkstyle NPathComplexity (50 lines)
     */
    private void addToBuilder(
        final Entry<String, Object> pair, final JsonObjectBuilder builder
    ) {
        if(pair.getValue() instanceof JsonValue) {
            builder.add(pair.getKey(), (JsonValue) pair.getValue());
        }
        if(pair.getValue() instanceof String) {
            builder.add(pair.getKey(), (String) pair.getValue());
        }
        if(pair.getValue() instanceof Boolean) {
            builder.add(pair.getKey(), (Boolean) pair.getValue());
        }
        if(pair.getValue() instanceof Integer) {
            builder.add(pair.getKey(), (Integer) pair.getValue());
        }
        if(pair.getValue() instanceof Long) {
            builder.add(pair.getKey(), (Long) pair.getValue());
        }
        if(pair.getValue() instanceof Double) {
            builder.add(pair.getKey(), (Double) pair.getValue());
        }
        if(pair.getValue() instanceof BigInteger) {
            builder.add(pair.getKey(), (BigInteger) pair.getValue());
        }
        if(pair.getValue() instanceof BigDecimal) {
            builder.add(pair.getKey(), (BigDecimal) pair.getValue());
        }
    }
}

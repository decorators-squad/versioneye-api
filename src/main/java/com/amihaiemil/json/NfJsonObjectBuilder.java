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
package com.amihaiemil.json;

import java.math.BigDecimal;
import java.math.BigInteger;
import javax.json.JsonArrayBuilder;
import javax.json.JsonNumber;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;
import javax.json.JsonValue;

/**
 * JsonObjectBuilder <b>with no flush</b>. It works the same as other
 * javax.json providers' implementation (glassfish, resteasy etc) except
 * it doesn't flush when the build() method is called (you can call build()
 * multiple times and it will return the same JsonObject).<br><br>
 * 
 * It has the same API as {@link JsonObjectBuilder}, but it's named differently
 * so the classloader isn't confused when choosing the provided
 * JsonObjectBuilder implementation (which is still the one from glassfish,
 * used behind this).
 * @author Mihai Andronache (amihaiemil@gmail.com)
 * @version $Id$
 * @since 1.0.0
 */
public interface NfJsonObjectBuilder {

    /**
     * Adds a name/{@code JsonValue} pair to the JSON object associated with
     * this object builder. If the object contains a mapping for the specified
     * name, this method replaces the old value with the specified value.
     *
     * @param name Name in the name/value pair
     * @param value Value in the name/value pair
     * @return This object builder
     * @throws NullPointerException if the specified name or value is null
     */
    NfJsonObjectBuilder add(final String name, final JsonValue value);

    /**
     * Adds a name/{@code JsonString} pair to the JSON object associated with
     * this object builder. If the object contains a mapping for the specified
     * name, this method replaces the old value with the specified value.
     *
     * @param name Name in the name/value pair
     * @param value Value in the name/value pair
     * @return This object builder
     * @throws NullPointerException if the specified name or value is null
     */
    NfJsonObjectBuilder add(final String name, final String value);

    /**
     * Adds a name/{@code JsonNumber} pair to the JSON object associated with
     * this object builder. If the object contains a mapping for the specified
     * name, this method replaces the old value with the specified value.
     *
     * @param name Name in the name/value pair
     * @param value Value in the name/value pair
     * @return This object builder
     * @throws NullPointerException if the specified name or value is null
     *
     * @see JsonNumber
     */
    NfJsonObjectBuilder add(final String name, final BigInteger value);

    /**
     * Adds a name/{@code JsonNumber} pair to the JSON object associated with
     * this object builder. If the object contains a mapping for the specified
     * name, this method replaces the old value with the specified value.
     *
     * @param name Name in the name/value pair
     * @param value Value in the name/value pair
     * @return This object builder
     * @throws NullPointerException if the specified name or value is null
     *
     * @see JsonNumber
     */
    NfJsonObjectBuilder add(final String name, final BigDecimal value);

    /**
     * Adds a name/{@code JsonNumber} pair to the JSON object associated with
     * this object builder. If the object contains a mapping for the specified
     * name, this method replaces the old value with the specified value.
     *
     * @param name Name in the name/value pair
     * @param value Value in the name/value pair
     * @return This object builder
     * @throws NullPointerException if the specified name is null
     *
     * @see JsonNumber
     */
    NfJsonObjectBuilder add(final String name, final int value);

    /**
     * Adds a name/{@code JsonNumber} pair to the JSON object associated with
     * this object builder. If the object contains a mapping for the specified
     * name, this method replaces the old value with the specified value.
     *
     * @param name Name in the name/value pair
     * @param value Value in the name/value pair
     * @return This object builder
     * @throws NullPointerException if the specified name is null
     *
     * @see JsonNumber
     */
    NfJsonObjectBuilder add(final String name, final long value);

    /**
     * Adds a name/{@code JsonNumber} pair to the JSON object associated with
     * this object builder. If the object contains a mapping for the specified
     * name, this method replaces the old value with the specified value.
     *
     * @param name Name in the name/value pair
     * @param value Value in the name/value pair
     * @return This object builder
     * @throws NumberFormatException if the value is Not-a-Number(NaN) or 
     * infinity
     * @throws NullPointerException if the specified name is null
     *
     * @see JsonNumber
     */
    NfJsonObjectBuilder add(final String name, final double value);

    /**
     * Adds a name/{@code JsonValue#TRUE} or name/{@code JsonValue#FALSE} pair
     * to the JSON object associated with this object builder. If the object
     * contains a mapping for the specified name, this method replaces the old
     * value with the specified value.
     *
     * @param name Name in the name/value pair
     * @param value Value in the name/value pair
     * @return This object builder
     * @throws NullPointerException if the specified name is null
     */
    NfJsonObjectBuilder add(final String name, final boolean value);

    /**
     * Adds a name/{@code JsonValue#NULL} pair to the JSON object associated
     * with this object builder where the value is {@code null}.
     * If the object contains a mapping for the specified name, this method
     * replaces the old value with {@code null}.
     *
     * @param name Name in the name/value pair
     * @return This object builder
     * @throws NullPointerException if the specified name is null
     */
    NfJsonObjectBuilder addNull(final String name);

    /**
     * Adds a name/{@code JsonObject} pair to the JSON object associated
     * with this object builder. The value {@code JsonObject} is built from the
     * specified object builder. If the object contains a mapping for the
     * specified name, this method replaces the old value with the
     * {@code JsonObject} from the specified object builder.
     *
     * @param name Name in the name/value pair
     * @param builder The value is the object associated with this builder
     * @return This object builder
     * @throws NullPointerException if the specified name or builder is null
     */
    NfJsonObjectBuilder add(final String name, final JsonObjectBuilder builder);

    /**
     * Adds a name/{@code JsonArray} pair to the JSON object associated with
     * this object builder. The value {@code JsonArray} is built from the
     * specified array builder. If the object contains a mapping for the
     * specified name, this method replaces the old value with the
     * {@code JsonArray} from the specified array builder.
     *
     * @param name The name in the name/value pair
     * @param builder The value is the object array with this builder
     * @return This object builder
     * @throws NullPointerException if the specified name or builder is null
     */
    NfJsonObjectBuilder add(final String name, final JsonArrayBuilder builder);

    /**
     * Returns the JSON object associated with this object builder. 
     * The iteration order for the {@code JsonObject} is based
     * on the order in which name/value pairs are added to the object using
     * this builder.
     *
     * @return JSON object that is being built
     */
    JsonObject build();
}

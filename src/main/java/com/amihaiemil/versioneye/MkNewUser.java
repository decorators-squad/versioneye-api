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
import javax.json.JsonArray;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import javax.json.JsonValue;

/**
 * Mock user sign-up form.
 * @author Mihai Andronache (amihaiemil@gmail.com)
 * @version $Id$
 * @since 1.0.0
 */
final class MkNewUser implements NewUser {

    /**
     * VersionEye server.
     */
    private MkServer server;
    
    /**
     * This user as JsonObject.
     */
    private JsonObject json;
    
    /**
     * Ctor.
     * @param server VersionEye server.
     */
    MkNewUser(final MkServer server) {
        this(server, Json.createObjectBuilder().build());
    }
    
    /**
     * Ctor.
     * @param server VersionEye server.
     * @param newUser The new user as json.
     */
    MkNewUser(final MkServer server, final JsonObject newUser) {
        this.server = server;
        this.json = newUser;
    }
    
    @Override
    public String fullName() {
        return this.json.getString("fullname");
    }

    @Override
    public String username() {
        return this.json.getString("username");
    }

    @Override
    public JsonObject json() {
        return this.json;
    }

    @Override
    public NewUser fullName(final String fullName) {
        final String username =  this.json.getString("username", "");
        final JsonObject edited;
        if(username.isEmpty()) {
            edited = Json
                .createObjectBuilder()
                .add("fullname", fullName)
                .build();
        } else {
            edited = Json
                .createObjectBuilder()
                .add("username", username)
                .add("fullname", fullName)
                .build();
        }
        return new MkNewUser(this.server, edited);
    }

    @Override
    public NewUser username(final String username) {
        final JsonObject edited = Json
            .createObjectBuilder()
            .add("username", username)
            .add("fullname", this.json.getString("fullname", ""))
            .build();
        return new MkNewUser(this.server, edited);
    }

    @Override
    public User signUp() {
        final JsonArray registered = this.server.storage().build()
            .getJsonArray("users");
        final JsonArrayBuilder users = Json.createArrayBuilder();
        for(final JsonValue user: registered) {
            users.add(user);
        }
        users.add(
            Json.createObjectBuilder()
                .add(
                    this.json.getString("username"),
                    this.json
                )
        );
        this.server.storage().add("users", users.build());
        return new MkUser(this.server, this.json.getString("username"));
    }

}

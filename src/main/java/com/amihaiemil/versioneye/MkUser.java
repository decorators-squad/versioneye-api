package com.amihaiemil.versioneye;

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
import java.io.IOException;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonObject;

/**
 * A mock VersionEye user.
 * @author Mihai Andronache (amihaiemil@gmail.com)
 * @version $Id$
 * @since 1.0.0
 *
 */
final class MkUser implements User {

    /**
     * VersionEye server.
     */
    private MkServer server;
    
    /**
     * This user's username.
     */
    private String username;
    
    /**
     * Ctor.
     * @param server VersionEye server storage.
     * @param username The user's username.
     */
    MkUser(final MkServer server, final String username) {
        this.server = server;
        this.username = username;
    }

    @Override
    public UserData about() throws IOException {
        UserData about = null;
        final JsonArray online = this.server.storage().build()
            .getJsonArray("authenticated");
        for(int idx = 0; idx < online.size(); idx++) {
            final JsonObject authenticated = online.getJsonObject(idx);
            if(authenticated.getJsonObject(this.username) != null) {
                about =  new JsonUserData(
                    Json.createObjectBuilder()
                        .add(
                            "fullname",
                            authenticated.getJsonObject(this.username)
                                .getString("fullname")
                        ).add(
                            "username",
                            authenticated.getJsonObject(this.username)
                                .getString("username")
                        )
                        .build()
                );
            }
        }
        final JsonArray users = this.server.storage().build()
            .getJsonArray("users");
        for(int idx = 0; idx < users.size(); idx++) {
            final JsonObject user = users.getJsonObject(idx);
            if(user.getJsonObject(this.username) != null) {
                about = new JsonUserData(
                    user.getJsonObject(this.username)
                );
            }
        }
        if(about == null) {
            throw new IllegalStateException(
                "User " + this.username + " not found."
                        + " You have to register it first, "
                        + "or it has to be the authenticated user."
            );
        }
        return about;
    }

    @Override
    public Comments comments() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Favorites favorites() {
        // TODO Auto-generated method stub
        return null;
    }

}

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

import javax.json.JsonObject;

/**
 * VersionEye authenticated user represented by a Json object.
 * @author Mihai Andronache (amihaiemil@gmail.com)
 * @version $Id$
 * @sinve 1.0.0
 *
 */
final class JsonAuthenticated implements Authenticated {

    /**
     * Authenticated user.
     */
    private JsonObject user;
    
    /**
     * Ctor.
     * @param user Given user.
     */
    JsonAuthenticated(final JsonObject user) {
        this.user = user;
    }
    
    @Override
    public String fullName() {
        return this.user.getString("fullname");
    }

    @Override
    public String username() {
        return this.user.getString("username");
    }

    @Override
    public String email() {
        return this.user.getString("email");
    }

    @Override
    public boolean admin() {
        return this.user.getBoolean("admin");
    }

    @Override
    public boolean deleted() {
        return this.user.getBoolean("deleted_user");
    }

    @Override
    public int enterpriseProjects() {
        return this.user.getInt("enterprise_projects");
    }

    @Override
    public int rateLimit() {
        return this.user.getInt("rate_limit");
    }

    @Override
    public int compLimit() {
        return this.user.getInt("comp_limit");
    }

    @Override
    public boolean active() {
        return this.user.getBoolean("active");
    }

    @Override
    public JsonObject json() {
        return this.user;
    }

}

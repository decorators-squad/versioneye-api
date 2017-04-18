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
import javax.json.JsonObject;

import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.Test;

/**
 * Unit tests for {@link JsonAuthenticated}.
 * @author Mihai Andronache (amihaiemil@gmail.com)
 * @version $Id$
 * @since 1.0.0
 *
 */
public final class JsonAuthenticatedTestCase {
    
    /**
     * JsonAuthenticated knows the user's full name.
     */
    @Test
    public void knowsFullName() {
        final Authenticated auth = new JsonAuthenticated(
            Json.createObjectBuilder().add("fullname", "Jon Doe").build()
        );
        MatcherAssert.assertThat(auth.fullName(), Matchers.equalTo("Jon Doe"));
    }
    
    /**
     * JsonAuthenticated knows the user's username.
     */
    @Test
    public void knowsUsername() {
        final Authenticated auth = new JsonAuthenticated(
            Json.createObjectBuilder().add("username", "jondoe").build()
        );
        MatcherAssert.assertThat(auth.username(), Matchers.equalTo("jondoe"));
    }
    
    /**
     * JsonAuthenticated knows the user's e-mail address.
     */
    @Test
    public void knowsEmail() {
        final Authenticated auth = new JsonAuthenticated(
            Json.createObjectBuilder().add("email", "jdoe@gmail.com").build()
        );
        MatcherAssert.assertThat(
            auth.email(), Matchers.equalTo("jdoe@gmail.com")
        );
    }
    
    /**
     * JsonAuthenticated knows if the user is an admin.
     */
    @Test
    public void knowsAdmin() {
        final Authenticated auth = new JsonAuthenticated(
            Json.createObjectBuilder().add("admin", true).build()
        );
        MatcherAssert.assertThat(auth.admin(), Matchers.is(true));
    }
    
    /**
     * JsonAuthenticated knows if the user is deleted.
     */
    @Test
    public void knowsDeleted() {
        final Authenticated auth = new JsonAuthenticated(
            Json.createObjectBuilder().add("deleted_user", true).build()
        );
        MatcherAssert.assertThat(auth.deleted(), Matchers.is(true));
    }
    
    /**
     * JsonAuthenticated knows how many enterprise projects the user has.
     */
    @Test
    public void knowsNrOfEnterpriseProjects() {
        final Authenticated auth = new JsonAuthenticated(
            Json.createObjectBuilder().add("enterprise_projects", 17).build()
        );
        MatcherAssert.assertThat(auth.enterpriseProjects(), Matchers.is(17));
    }
    
    /**
     * JsonAuthenticated knows the user's rate limit.
     */
    @Test
    public void knowsTheRateLimit() {
        final Authenticated auth = new JsonAuthenticated(
            Json.createObjectBuilder().add("rate_limit", 59).build()
        );
        MatcherAssert.assertThat(auth.rateLimit(), Matchers.is(59));
    }
    
    /**
     * JsonAuthenticated knows the user's comp limit.
     */
    @Test
    public void knowsTheCompLimit() {
        final Authenticated auth = new JsonAuthenticated(
            Json.createObjectBuilder().add("comp_limit", 60).build()
        );
        MatcherAssert.assertThat(auth.compLimit(), Matchers.is(60));
    }
    
    /**
     * JsonAuthenticated knows if the user is active.
     */
    @Test
    public void knowsIfActive() {
        final Authenticated auth = new JsonAuthenticated(
            Json.createObjectBuilder().add("active", true).build()
        );
        MatcherAssert.assertThat(auth.active(), Matchers.is(true));
    }
    
    /**
     * JsonAuthenticated can fetch the raw json.
     */
    @Test
    public void knowsJson() {
        final Authenticated auth = new JsonAuthenticated(
            Json.createObjectBuilder()
                .add("username", "amihaiemil")
                .add("active", true)
                .build()
        );
        JsonObject user = auth.json();
        MatcherAssert.assertThat(user, Matchers.notNullValue());
        MatcherAssert.assertThat(
            user.getString("username"), Matchers.equalTo("amihaiemil")
        );
        MatcherAssert.assertThat(
            user.getBoolean("active"), Matchers.is(true)
        );
        
    }
}

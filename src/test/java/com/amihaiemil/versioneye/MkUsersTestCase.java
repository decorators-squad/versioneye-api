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

import java.io.IOException;

import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.Test;

/**
 * Unit tests for {@link MkUsers}.
 * @author Mihai Andronache (amihaiemil@gmail.com)
 * @version $Id$
 * @since 1.0.0
 *
 */
public final class MkUsersTestCase {

    /**
     * MkUsers can fetch the authenticated user.
     * @throws IOException If something goes wrong.
     */
    @Test
    public void returnsAuthenticatedUser() throws IOException {
        MkAuthenticated authenticated = new MkJsonAuthenticated();
        authenticated = authenticated.fullName("Mihai Andronache")
            .username("amihaiemil")
            .email("amihaiemil@gmail.com")
            .admin(false)
            .deleted(false)
            .enterpriseProjects(1)
            .rateLimit(50)
            .compLimit(50)
            .active(true);
        User fetched = 
            new MkVersionEye(authenticated).users().user("amihaiemil");
        MatcherAssert.assertThat(
            fetched, Matchers.notNullValue()
        );
        MatcherAssert.assertThat(
            fetched.about().username(), Matchers.is("amihaiemil")
        );
        MatcherAssert.assertThat(
            fetched.about().fullName(), Matchers.is("Mihai Andronache")
        );
    }
    
    /**
     * MkUsers can return a registered user which is not authenticated.
     * @throws IOException If something goes wrong.
     */
    @Test
    public void returnsRegisteredUser() throws IOException {
        final VersionEye versioneye = new MkVersionEye();
        final User registered = versioneye.users().register()
            .fullName("Jeff Doe")
            .username("jeff")
            .signUp();
        final User fetched = versioneye.users().user("jeff");
        MatcherAssert.assertThat(
            fetched.about().json(),
            Matchers.equalTo(registered.about().json())
        );
        MatcherAssert.assertThat(
            fetched.about().username(), Matchers.is("jeff")
        );
        MatcherAssert.assertThat(
            fetched.about().fullName(), Matchers.is("Jeff Doe")
        );
    }
    
    /**
     * An exception is thrown if the user is not found.
     * @throws IOException If something goes wrong.
     */
    @Test (expected = IllegalStateException.class)
    public void throwsExceptionOnMissingUser() throws IOException {
        new MkVersionEye().users().user("jeff").about();
    }

}

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
import java.net.HttpURLConnection;

import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.Test;

import com.jcabi.http.mock.MkAnswer;
import com.jcabi.http.mock.MkContainer;
import com.jcabi.http.mock.MkGrizzlyContainer;
import com.jcabi.http.mock.MkQuery;
import com.jcabi.http.request.JdkRequest;
import com.jcabi.http.wire.TrustedWire;

/**
 * Unit tests for {@link RtVersionEye}.
 * @author Mihai Andronache (amihaiemil@gmail.com)
 * @version $Id$
 * @since 1.0.0
 *
 */
@SuppressWarnings("resource")
public final class RtVersionEyeTestCase {
    
    /**
     * RtVersionEye can return the services endpoint.
     */
    @Test
    public void fetchesServicesApi() {
        final Services services = new RtVersionEye().services();
        MatcherAssert.assertThat(services, Matchers.notNullValue());
        MatcherAssert.assertThat(
            services, Matchers.instanceOf(RtServices.class)
        );
    }
    
    /**
     * RtVersionEye can return the users endpoint.
     */
    @Test
    public void fetchesUsersApi() {
        final Users users = new RtVersionEye().users();
        MatcherAssert.assertThat(users, Matchers.notNullValue());
        MatcherAssert.assertThat(
            users, Matchers.instanceOf(RtUsers.class)
        );
    }
    
    /**
     * RtVersionEye works with {@link TrustedWire}.
     * @throws IOException If something goes wrong.
     */
    @Test
    public void rtVersionEyeTrustsServer() throws IOException {
        final MkContainer container = new MkGrizzlyContainer().next(
            new MkAnswer.Simple(
                HttpURLConnection.HTTP_OK,
                "{\"success\":true, \"message\":\"pong\"}"
            )
        ).next(
            new MkAnswer.Simple(
                HttpURLConnection.HTTP_OK,
               "{\"success\":true, \"message\":\"pong\"}"
            )
        ).start();
        final VersionEye versionEye = new RtVersionEye(
            new JdkRequest(container.home())
        );
        MatcherAssert.assertThat(
            versionEye.services().ping(),
            Matchers.equalTo(versionEye.trusted().services().ping())
        );
        final MkQuery notTrusted = container.take();
        final MkQuery trusted = container.take();
        MatcherAssert.assertThat(
            notTrusted.uri(), Matchers.equalTo(trusted.uri())
        );
    }
}

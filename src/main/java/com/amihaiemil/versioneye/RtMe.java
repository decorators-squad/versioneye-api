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

import javax.json.JsonObject;

import com.jcabi.http.Request;
import com.jcabi.http.response.JsonResponse;
import com.jcabi.http.response.RestResponse;

/**
 * Me API real implementation.
 * @author Mihai Andronache (amihaiemil@gmail.com)
 * @version $Id$
 * @since 1.0.0
 */
final class RtMe implements Me {

    /**
     * HTTP request.
     */
    private Request req;
    
    /**
     * Ctor.
     * @param req HTTP request.
     */
    RtMe(final Request req) {
        this.req = req.uri().path("/me").back();
    }

    @Override
    public Authenticated about() throws IOException {
        return new JsonAuthenticated(
            this.req.fetch()
                .as(RestResponse.class)
                .assertStatus(HttpURLConnection.HTTP_OK)
                .as(JsonResponse.class)
                .json()
                .readObject()
        );
    }

    /**
     * Comments.
     * @todo #11:30min/DEV Implement and unit test this method after we have
     *  a working implementation of Comments in place.
     * @return Comments.
     */
    @Override
    public Comments comments() {
        return new RtComments(this.req);
    }
    
    /**
     * Favorites.
     * @todo #11:30min/DEV Implement and unit test this method after we have
     *  a working implementation of Favorites in place.
     * @return Favorites.
     */
    @Override
    public Favorites favorites() {
        return null;
    }

    @Override
    public void post(final JsonObject authenticated) {
        throw new UnsupportedOperationException(
            "Invalid operation for runtime VersionEye"
        );
    }
}

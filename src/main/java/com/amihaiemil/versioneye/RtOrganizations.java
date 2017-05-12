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
import java.util.ArrayList;
import java.util.List;

import javax.json.JsonArray;

import com.jcabi.http.Request;
import com.jcabi.http.response.JsonResponse;
import com.jcabi.http.response.RestResponse;

/**
 * Organizations API implementation.
 * @author Sherif Waly (sherifwaly95@gmail.com)
 * @version $Id$
 * @since 1.0.0
 */
final class RtOrganizations implements Organizations {

    /**
     * HTTP request for organizations.
     */
    private Request req;
    
    /**
     * Initial HTTP request, the API's entry point.
     */
    private Request entry;

    /**
     * Ctor.
     * @param entry HTTP Request.
     */
    RtOrganizations(final Request entry) {
        this.entry = entry;
        this.req = entry.uri().path("/organisations").back();
    }
    
    @Override
    public List<Organization> fetch() throws IOException {
        return this.fetchOrgs();
    }

    @Override
    public Organization organization(final String organizationName)
        throws IOException {
        final List<Organization> organizations = this.fetchOrgs();
        for(final Organization organization: organizations) {
            if(organization.name().equals(organizationName)) {
                return organization;
            }
        }
        throw new IOException(
            "No Organization found for name: " + organizationName
        ); 
    }

    /**
     * Fetches the organizations that the authenticated user
     * has access to.
     * @return List of organizations.
     * @throws IOException If something goes wrong when
     *  making the HTTP call.
     */
    private List<Organization> fetchOrgs() throws IOException {
        final JsonArray orgs = this.req.fetch()
            .as(RestResponse.class)
            .assertStatus(HttpURLConnection.HTTP_OK)
            .as(JsonResponse.class)
            .json()
            .readArray();
        final List<Organization> organizations = new ArrayList<>();
        for(int idx=0; idx<orgs.size(); idx++) {
            organizations.add(
                new RtOrganization(
                    orgs.getJsonObject(idx), this.req, this.entry
                )
            );
        }
        return organizations;
    }

}

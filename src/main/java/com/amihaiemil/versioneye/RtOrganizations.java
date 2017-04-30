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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.json.JsonArray;
import javax.json.JsonObject;

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
     * HTTP request.
     */
    private Request req;
    
    /**
     * Mapping from organization name to an Organization.
     */
    private Map<String, Organization> organizationsMapping;
   
    /**
     * Ctor.
     * @param entry HTTP Request.
     */
    RtOrganizations(final Request entry) {
        this.req = entry.uri().path("/organisations").back();
    }
    
    @Override
    public List<Organization> fetch() throws IOException {
        final JsonArray array = this.req.fetch()
            .as(RestResponse.class)
            .assertStatus(HttpURLConnection.HTTP_OK)
            .as(JsonResponse.class)
            .json()
            .readArray();
        final List<Organization> organizations = new ArrayList<>();
        for(int idx=0; idx<array.size(); idx++) {
            organizations.add(
                new RtOrganization(array.getJsonObject(idx), this.req)
            );
        }
        return organizations;
    }

    @Override
    public Organization organization(final String organizationName)
        throws IOException {
        if(this.organizationsMapping == null) {
            this.organizationsMapping = this.fetchOrganizationsMapping();
        }
        return this.organizationsMapping.get(organizationName);
    }

    /**
     * Fetches the organizations that the authenticated user
     * has access to and construct a mapping from the organization name 
     * to an organization.
     * @return Mapping from organization name to an organization.
     * @throws IOException 
     */
    private Map<String, Organization> fetchOrganizationsMapping()
        throws IOException {
        JsonArray array = this.req.fetch()
            .as(RestResponse.class)
            .assertStatus(HttpURLConnection.HTTP_OK)
            .as(JsonResponse.class)
            .json()
            .readArray();
        final Map<String, Organization> organizations = new HashMap<>();
        for(int idx=0; idx<array.size(); idx++) {
            JsonObject organization = array.getJsonObject(idx);
            organizations.put(
                organization.getString("name"),
                new RtOrganization(organization, this.req)
            );
        }
        return organizations;
    }

}

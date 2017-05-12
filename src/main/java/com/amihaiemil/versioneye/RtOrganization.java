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

import com.jcabi.http.Request;

/**
 * Real implementation for {@link Oganization}.
 * @author Sherif Waly (sheifwaly95@gmail.com)
 * @version $Id$
 * @since 1.0.0
 * @todo #37:30min/DEV implement and test `projects()` method.
 */
final class RtOrganization implements Organization {

    /**
     * The organization as a JsonObject.
     */
    private JsonObject organization;    

    /**
     * HTTP request.
     */
    private Request req;
    
    /**
     * API entry point.
     */
    private Request entry;
    
    /**
     * Ctor.
     * @param organization Json organization as returned by API. 
     * @param req HTTP request for Organization.
     * @param entry Initial HTTP request, entry point of the API.
     */
    RtOrganization(
        final JsonObject organization, final Request req,
        final Request entry
    ) {
        this.organization = organization;
        this.req = req.uri().path(organization.getString("name")).back();
        this.entry = entry;
    }

    @Override
    public Teams teams() {
        return new RtTeams(this.entry, this.req, this);
    }

    @Override
    public String name() {
        return this.organization.getString("name");
    }

    @Override
    public String company() {
        return this.organization.getString("company", "");  
    }

    @Override
    public String createdAt() {
        return this.organization.getString("created_at");
    }
    
    @Override
    public String updatedAt() {
        return this.organization.getString("updated_at");    
    }

    @Override
    public String apiKey() {
        return this.organization.getString("api_key");    
    }

    @Override
    public JsonObject json() {
        return this.organization;
    }

}

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
import java.util.List;

import com.jcabi.http.Request;

/**
 * Real implementation of {@link Projects}.
 * @author Mihai Andronache (amihaiemil@gmail.com)
 * @version $Id$
 * @since 1.0.0
 * @todo #38:30min/DEV Implement and unit test method fetch().
 *
 */
final class RtProjects implements Projects {

    /**
     * HTTP request for /projects.
     */
    private Request req;
    
    /**
     * The team responsible for these projects.
     */
    private Team team;
    
    /**
     * Ctor.
     * @param req Request for /projects.
     * @param team Team responsible for these projects;
     */
    RtProjects(final Request req, final Team team) {
        this.req = req.uri()
            .path("/projects")
            .queryParam("orga_name", team.organization().name())
            .queryParam("team_name", team.name())
            .queryParam("api_key", team.organization().apiKey())
            .back();
        this.team = team;
    }
    
    @Override
    public List<Project> fetch() throws IOException {
        return null;
    }

    @Override
    public Team team() {
        return this.team;
    }

}

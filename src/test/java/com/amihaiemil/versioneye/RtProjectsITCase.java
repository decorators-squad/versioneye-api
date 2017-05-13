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
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.Test;

/**
 * Integration tests for {@link RtProjects}.
 * @author Mihai Andronache (amihaiemil@gmail.com)
 * @version $Id$
 * @since 1.0.0
 *
 */
public final class RtProjectsITCase {
    
    /**
     * RtProjects can fetch a team's projects.
     * @throws IOException If something goes wrong with the HTTP call.
     */
    @Test
    public void fetchesTeamsProjects() throws IOException {
        final VersionEye versioneye = new RtVersionEye(
            System.getProperty("api_key")
        );
        final List<Team> teams = versioneye
            .organizations()
            .organization("amihaiemil_orga")
            .teams()
            .fetch();
        Team owners = null;
        for(int i=0; i < teams.size(); i++) {
            if(teams.get(i).name().equals("Owners")) {
                owners = teams.get(i);
                break;
            }
        }
        final List<Project> projects = owners.projects().fetch();
        MatcherAssert.assertThat(projects.size(), Matchers.greaterThan(0));
        for(int idx = 0; idx < projects.size(); idx++) {
            MatcherAssert.assertThat(
                projects.get(idx).organization().name(),
                Matchers.equalTo("amihaiemil_orga")
            );
            MatcherAssert.assertThat(
                projects.get(idx).team().name(),
                Matchers.equalTo("Owners")
            );
        }
    }
    
    /**
     * RtProjects knows to which team the projects belong.
     * @throws IOException If something goes wrong with the HTTP call.
     */
    @Test
    public void knowsTeam() throws IOException {
        final VersionEye versioneye = new RtVersionEye(
            System.getProperty("api_key")
        );
        final List<Team> teams = versioneye
            .organizations()
            .organization("amihaiemil_orga")
            .teams()
            .fetch();
        Team owners = null;
        for(int i=0; i < teams.size(); i++) {
            if(teams.get(i).name().equals("Owners")) {
                owners = teams.get(i);
                break;
            }
        }
        final Projects projects = owners.projects();
        MatcherAssert.assertThat(
            projects.team().name(),
            Matchers.equalTo(owners.name())
        );
        MatcherAssert.assertThat(
            projects.team().createdAt(),
            Matchers.equalTo(owners.createdAt())
        );
    }
}

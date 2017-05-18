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

import javax.json.Json;
import javax.json.JsonObject;

import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.Test;
import org.mockito.Mockito;

import com.jcabi.http.mock.MkAnswer;
import com.jcabi.http.mock.MkContainer;
import com.jcabi.http.mock.MkGrizzlyContainer;
import com.jcabi.http.mock.MkQuery;
import com.jcabi.http.request.FakeRequest;
import com.jcabi.http.request.JdkRequest;

/**
 * Unit tests for {@link RtProject}.
 * @author Mihai Andronache (amihaiemil@gmail.com)
 * @version $Id$
 * @since 1.0.0
 *
 */
@SuppressWarnings("resource")
public final class RtProjectTestCase {
    
    /**
     * RtProject can return its id.
     */
    @Test
    public void returnsId() {
        final Project project = new RtProject(
            new FakeRequest(),
            Mockito.mock(Team.class),
            Json.createObjectBuilder().add("ids", "testid").build()
        );
        MatcherAssert.assertThat(
            project.projectId(), Matchers.equalTo("testid")
        );
    }
    
    /**
     * RtProject can return its name.
     */
    @Test
    public void returnsName() {
        final Project project = new RtProject(
            new FakeRequest(),
            Mockito.mock(Team.class),
            Json.createObjectBuilder().add("name", "charles").build()
        );
        MatcherAssert.assertThat(
            project.name(), Matchers.equalTo("charles")
        );
    }
    
    /**
     * RtProject can return its type.
     */
    @Test
    public void returnsType() {
        final Project project = new RtProject(
            new FakeRequest(),
            Mockito.mock(Team.class),
            Json.createObjectBuilder().add("project_type", "maven").build()
        );
        MatcherAssert.assertThat(
            project.type(), Matchers.equalTo("maven")
        );
    }

    /**
     * RtProject can return its number of dependencies.
     */
    @Test
    public void returnsDependencies() {
        final Project project = new RtProject(
            new FakeRequest(),
            Mockito.mock(Team.class),
            Json.createObjectBuilder().add("dep_number", 15).build()
        );
        MatcherAssert.assertThat(
            project.dependencies(), Matchers.is(15)
        );
    }
    
    /**
     * RtProject can return its nr of outdated dependencies.
     */
    @Test
    public void returnsOutdated() {
        final Project project = new RtProject(
            new FakeRequest(),
            Mockito.mock(Team.class),
            Json.createObjectBuilder().add("out_number", 3).build()
        );
        MatcherAssert.assertThat(
            project.outdated(), Matchers.is(3)
        );
    }

    /**
     * RtProject can return the number of unknown licenses.
     */
    @Test
    public void returnsUnknownLicenses() {
        final Project project = new RtProject(
            new FakeRequest(),
            Mockito.mock(Team.class),
            Json.createObjectBuilder().add("licenses_unknown", 2).build()
        );
        MatcherAssert.assertThat(
            project.licensesUnknown(), Matchers.is(2)
        );
    }

    /**
     * RtProject can return the number of red licenses.
     */
    @Test
    public void returnsRedLicenses() {
        final Project project = new RtProject(
            new FakeRequest(),
            Mockito.mock(Team.class),
            Json.createObjectBuilder().add("licenses_red", 1).build()
        );
        MatcherAssert.assertThat(
            project.licensesRed(), Matchers.is(1)
        );
    }

    /**
     * RtProject can return the name of the licenses' whitelist.
     */
    @Test
    public void returnsWhitelist() {
        final Project project = new RtProject(
            new FakeRequest(),
            Mockito.mock(Team.class),
            Json.createObjectBuilder()
                .add("license_whitelist_name", "white")
                .build()
        );
        MatcherAssert.assertThat(
            project.whitelist(), Matchers.equalTo("white")
        );
    }

    /**
     * RtProject can return the ids of its children.
     */
    public void returnsChildIds() {
        final Project project = new RtProject(
            new FakeRequest(),
            Mockito.mock(Team.class),
            Json.createObjectBuilder()
                .add(
                    "child_ids",
                    Json.createArrayBuilder()
                        .add("child1")
                        .add("child2")
                        .build()
                )
                .build()
        );
        MatcherAssert.assertThat(
            project.childIds(),
            Matchers.hasSize(2)
        );
        MatcherAssert.assertThat(
            project.childIds().get(0),
            Matchers.equalTo("child1")
        );
        MatcherAssert.assertThat(
            project.childIds().get(1),
            Matchers.equalTo("child2")
        );
    }

    /**
     * RtProject can return its parent's id.
     */
    @Test
    public void returnsParentId() {
        final Project project = new RtProject(
            new FakeRequest(),
            Mockito.mock(Team.class),
            Json.createObjectBuilder()
                .add("parent_id", "DarthVader")
                .build()
        );
        MatcherAssert.assertThat(
            project.parentId(), Matchers.equalTo("DarthVader")
        );
    }

    /**
     * RtProject can return its creation date.
     */
    @Test
    public void returnsCreatedAt() {
        final Project project = new RtProject(
            new FakeRequest(),
            Mockito.mock(Team.class),
            Json.createObjectBuilder()
                .add("created_at", "2017-05-12T13:22:22.032Z")
                .build()
        );
        MatcherAssert.assertThat(
            project.createdAt(),
            Matchers.equalTo("2017-05-12T13:22:22.032Z")
        );
    }

    /**
     * RtProject can return its update date.
     */
    @Test
    public void returnsUpdatedAt() {
        final Project project = new RtProject(
            new FakeRequest(),
            Mockito.mock(Team.class),
            Json.createObjectBuilder()
                .add("updated_at", "2017-05-13T16:22:22.032Z")
                .build()
        );
        MatcherAssert.assertThat(
            project.updatedAt(),
            Matchers.equalTo("2017-05-13T16:22:22.032Z")
        );
    }


    /**
     * RtProject can return itself as Json.
     */
    @Test
    public void returnsJson() {
        final JsonObject json = Json.createObjectBuilder()
            .add("name", "charles")
            .add("type", "maven")
            .add("language", "java")
            .build();
        final Project project = new RtProject(
            new FakeRequest(), Mockito.mock(Team.class), json
        );
        MatcherAssert.assertThat(
            project.json(),
            Matchers.equalTo(json)
        );
    }

    /**
     * RtProject can delete the project from the VersionEye server.
     * @throws IOException If something goes wrong.
     */
    @Test
    public void deletesProject() throws IOException {
        final MkContainer container = new MkGrizzlyContainer().next(
            new MkAnswer.Simple(HttpURLConnection.HTTP_NO_CONTENT)
        ).start();
        final Project project = new RtProject(
            new JdkRequest(container.home()),
            Mockito.mock(Team.class),
            Json.createObjectBuilder().add("ids", "project123").build()
        );
        project.delete();
        final MkQuery request = container.take();
        MatcherAssert.assertThat(
            request.method(), Matchers.equalTo("DELETE")
        );
        MatcherAssert.assertThat(
            request.uri().toString(), Matchers.equalTo("/project123")
        );
    }
}

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

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.util.List;

import org.apache.commons.io.IOUtils;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.Test;

import com.jcabi.http.mock.MkAnswer;
import com.jcabi.http.mock.MkContainer;
import com.jcabi.http.mock.MkGrizzlyContainer;
import com.jcabi.http.request.JdkRequest;

/**
 * Unit tests for {@link RepositoriesPage}.
 * @author Sherif Waly (sherifwaly95@gmail.com)
 * @version $Id$
 * @since 1.0.0
 */
@SuppressWarnings("resource")
public final class RepositoriesPageTestCase {

    /**
     * RepositoriesPage can return the first page of repositories.
     * @throws Exception If something goes wrong.
     */
    @Test
    public void iteratesOverFirstPage() throws Exception {
        final MkContainer container =
            this.mockVersionEyeRepositories().start();
        final VersionEye versionEye = new RtVersionEye(
            new JdkRequest(container.home())
        );
        List<Repository> first =
            versionEye.github().repositories().paginated().fetch();
        MatcherAssert.assertThat(first.size(), Matchers.is(14));
        MatcherAssert.assertThat(
            first.get(0).name(),
            Matchers.equalTo("Compiler")
        );
        MatcherAssert.assertThat(
            first.get(13).name(),
            Matchers.equalTo("versioneye-api")
        );
        MatcherAssert.assertThat(
            container.take().uri().toString(),
            Matchers.equalTo("/github?page=1")
        );   
    }
    
    /**
     * RepositoriesPage can iterate over all the comments.
     * @throws Exception If something goes wrong.
     */
    @Test
    public void iteratesOverAll() throws Exception {
        final MkContainer container =
            this.mockVersionEyeRepositories().start();
        final VersionEye versionEye = new RtVersionEye(
            new JdkRequest(container.home())
        );
        int pages = 0;
        for (final Page<Repository> page
            : versionEye.github().repositories().paginated()
        ){
            MatcherAssert.assertThat(
                page.fetch().size(), Matchers.is(14)
            );
            pages++;
        }
        MatcherAssert.assertThat(pages, Matchers.is(1));
        MatcherAssert.assertThat(
            container.take().uri().toString(),
            Matchers.equalTo("/github?page=1")
        );
        MatcherAssert.assertThat(
            container.take().uri().toString(),
            Matchers.equalTo("/github?page=1")
        );
        MatcherAssert.assertThat(
            container.take().uri().toString(),
            Matchers.equalTo("/github?page=2")
        );
    }
    
    /**
     * Mock the versioneye server for repositories.
     * @return MkContainer.
     * @throws IOException If something goes wrong.
     */
    private MkContainer mockVersionEyeRepositories() throws IOException {
        return new MkGrizzlyContainer().next(
            new MkAnswer.Simple(
                HttpURLConnection.HTTP_OK,
                this.readResource("repositoriespage1.json")
            )
        ).next(
            new MkAnswer.Simple(
                HttpURLConnection.HTTP_OK,
                this.readResource("repositoriespage1.json")
            )
        ).next(
            new MkAnswer.Simple(
                HttpURLConnection.HTTP_OK,
                this.readResource("repositoriespage2.json")
            )
        );
    }
    
    /**
     * Read resource for test.
     * @param resourceName Name of the file being read.
     * @return String content of the resource file.
     * @throws IOException If it goes wrong.
     */
    private String readResource(final String resourceName)
        throws IOException {
        final InputStream stream = new FileInputStream(
            new File("src/test/resources/" + resourceName)
        );
        return new String(IOUtils.toByteArray(stream));
    }
}

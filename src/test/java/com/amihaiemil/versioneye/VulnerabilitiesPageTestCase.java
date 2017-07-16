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
 * Unit tests for {@link VulnerabilitiesPage}.
 * @author Mihai Andronache (amihaiemil@gmail.com)
 * @version $Id$
 * @since 1.0.0
 *
 */
@SuppressWarnings("resource")
public final class VulnerabilitiesPageTestCase {

    /**
     * VulnerabilitiesPage can return the first page of comments.
     * @throws Exception If something goes wrong.
     */
    @Test
    public void iteratesOverFirstPage() throws Exception {
        final MkContainer container = this.mockVersionEyeComments().start();
        final VersionEye versionEye = new RtVersionEye(
            new JdkRequest(container.home())
        );
        List<Vulnerability> first = versionEye.security("Java").paginated()
            .fetch();
        MatcherAssert.assertThat(first.size(), Matchers.is(2));
        MatcherAssert.assertThat(
            container.take().uri().toString(),
            Matchers.equalTo("/security?language=Java&page=1")
        );
        
    }
    
    /**
     * VulnerabilitiesPage can iterate over all the comments.
     * @throws Exception If something goes wrong.
     */
    @Test
    public void iteratesOverAll() throws Exception {
        final MkContainer container = this.mockVersionEyeComments().start();
        final VersionEye versionEye = new RtVersionEye(
            new JdkRequest(container.home())
        );
        int pages = 0;
        for (final Page<Vulnerability> page
            : versionEye.security("Java").paginated()
        ){
            MatcherAssert.assertThat(
                page.fetch().size(), Matchers.is(2)
            );
            pages++;
        }
        MatcherAssert.assertThat(pages, Matchers.is(2));
        MatcherAssert.assertThat(
            container.take().uri().toString(),
            Matchers.equalTo("/security?page=1")
        );
        MatcherAssert.assertThat(
            container.take().uri().toString(),
            Matchers.equalTo("/security?language=Java&page=1")
        );
        MatcherAssert.assertThat(
            container.take().uri().toString(),
            Matchers.equalTo("/security?page=2")
        );
        MatcherAssert.assertThat(
            container.take().uri().toString(),
            Matchers.equalTo("/security?language=Java&page=2")
        );
        MatcherAssert.assertThat(
            container.take().uri().toString(),
            Matchers.equalTo("/security?page=3")
        );
    }
    
    /**
     * Mock the versioneye server for comments.
     * @return MkContainer.
     * @throws IOException If something goes wrong.
     */
    private MkContainer mockVersionEyeComments() throws IOException {
        final String firstPage = this.readResource(
            "vulnerabilities/vulnerabilitiespage1.json"
        );
        final String secondPage = this.readResource(
            "vulnerabilities/vulnerabilitiespage2.json"
        );
        final String thirdPage = this.readResource(
            "vulnerabilities/vulnerabilitiespage3.json"
        );
        
        return new MkGrizzlyContainer().next(
            new MkAnswer.Simple(HttpURLConnection.HTTP_OK, firstPage)
        ).next(
            new MkAnswer.Simple(HttpURLConnection.HTTP_OK, firstPage)
        ).next(
            new MkAnswer.Simple(HttpURLConnection.HTTP_OK, secondPage)
        ).next(
            new MkAnswer.Simple(HttpURLConnection.HTTP_OK, secondPage)
        ).next(
            new MkAnswer.Simple(HttpURLConnection.HTTP_OK, thirdPage)
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

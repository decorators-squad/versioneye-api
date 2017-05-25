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

import java.util.List;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URI;

import org.apache.commons.io.IOUtils;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.Test;

import com.jcabi.http.mock.MkAnswer;
import com.jcabi.http.mock.MkContainer;
import com.jcabi.http.mock.MkGrizzlyContainer;
import com.jcabi.http.mock.MkQuery;
import com.jcabi.http.request.JdkRequest;

/**
 * Unit tests for {@link RtRepositories}.
 * @author Sherif Waly (sherifwaly95@gmail.com)
 * @version $Id$
 * @since 1.0.0
 */
@SuppressWarnings("resource")
public final class RtRepositoriesTestCase {

    /**
     * RtRepositories can fetch repositories.
     * @throws IOException If something goes wrong.
     */
    @Test
    public void fetchesRepositories() throws IOException {
        final MkContainer container = new MkGrizzlyContainer().next(
            new MkAnswer.Simple(
                HttpURLConnection.HTTP_OK,
                this.readResource("repositoriespage1.json")
            )
        ).start();  
        
        final List<Repository> repositories = 
            new RtRepositories(new JdkRequest(container.home())).fetch(1);
  
        MatcherAssert.assertThat(
            repositories.size(),
            Matchers.is(14)
        );
        
        Repository repository = repositories.get(2);
        
        MatcherAssert.assertThat(
            repository.name(),
            Matchers.is("camel")
        );
        MatcherAssert.assertThat(
            repository.fullname(),
            Matchers.is("SherifWaly/camel")
        );
        MatcherAssert.assertThat(
            repository.language(),
            Matchers.is("java")
        );
        MatcherAssert.assertThat(
            repository.ownerLogin(),
            Matchers.is("SherifWaly")
        );
        MatcherAssert.assertThat(
            repository.ownerType(),
            Matchers.is("user")
        );
        MatcherAssert.assertThat(
            repository.description(),
            Matchers.is("YAML for Java. A user-friendly OOP library.")
        );
        MatcherAssert.assertThat(
            repository.isPrivate(),
            Matchers.is(false)
        );
        MatcherAssert.assertThat(
            repository.fork(),
            Matchers.is(true)
        );
        MatcherAssert.assertThat(
            repository.branches().size(),
            Matchers.is(0)
        );
        
        repository = repositories.get(11);
        
        MatcherAssert.assertThat(
            repository.branches().size(),
            Matchers.is(2)
        );
        MatcherAssert.assertThat(
            repository.branches().get(0),
            Matchers.is("TEST")
        );
        MatcherAssert.assertThat(
            repository.json().getString("name"),
            Matchers.is("SignalFlowGraph")
        );
    }

    /**
     * RtRepositories can add filters to the fetch request.
     * @throws IOException If something goes wrong.
     */
    @Test
    public void addsFilters() throws IOException {
        final MkContainer server = new MkGrizzlyContainer().next(
            new MkAnswer.Simple(
                HttpURLConnection.HTTP_OK,
                this.readResource("repositoriespage1.json")
            )
        ).start();
        final URI addr = server.home();
        final Repositories filter = new RtRepositories(new JdkRequest(addr))
            .language("Java")
            .onlyImported(true)
            .organizationName("amihaiemil_orga")
            .organizationType("orgatype")
            .isPrivate(false);
        filter.fetch(1);
        final MkQuery request = server.take();
        MatcherAssert.assertThat(
            request.uri().toString(),
            Matchers.equalTo(
                String.format(
                    "/?%s&%s&%s&%s&%s&%s",
                    "lang=Java",
                    "only_imported=true",
                    "orga_name=amihaiemil_orga",
                    "orga_type=orgatype",
                    "private=false",
                    "page=1"
                )
            )
        );
    }
    
    /**
     * Read resource for test.
     * @param resourceName Name of the file being read.
     * @return String content of the resource file.
     * @throws IOException If it goes wrong.
     */
    private String readResource(final String resourceName) throws IOException {
        final InputStream stream = new FileInputStream(
            new File("src/test/resources/" + resourceName)
        );
        return new String(IOUtils.toByteArray(stream));
    }
}

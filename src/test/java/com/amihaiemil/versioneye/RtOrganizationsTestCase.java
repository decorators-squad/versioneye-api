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
 * Unit tests for {@link RtOrganizations}.
 * @author Sherif Waly (sherifwaly95@gmail.com)
 * @version $Id$
 * @since 1.0.0
 */

@SuppressWarnings("resource")
public final class RtOrganizationsTestCase {

    /**
     * RtOrganizations can fetch list of organizations.
     * @throws IOException If something goes wrong.
     */
    @Test
    public void fetchesOrganization() throws IOException {
        final MkContainer container = new MkGrizzlyContainer().next(
            new MkAnswer.Simple(
                HttpURLConnection.HTTP_OK,
                this.readResource("organizations.json")
            )
        ).start(); 
        final Organizations organizations = new RtOrganizations(
            new JdkRequest(container.home())
        );
        
        final List<Organization> fetched = organizations.fetch();
        
        MatcherAssert.assertThat(fetched.size(), Matchers.is(2));
        Organization organization = fetched.get(0);
        MatcherAssert.assertThat(
            organization.name(),
            Matchers.equalTo("sherifwaly_orga")
        );
        MatcherAssert.assertThat(
            organization.createdAt(),
            Matchers.equalTo("2017-04-23T16:43:46.141Z")
        );
        MatcherAssert.assertThat(
            organization.updatedAt(),
            Matchers.equalTo("2017-04-23T16:43:46.141Z")
        );
        MatcherAssert.assertThat(
            organization.company(),
            Matchers.equalTo(null)
        );
        MatcherAssert.assertThat(
            organization.apiKey(),
            Matchers.equalTo("88870f2710dba853a326")
        );
        
        organization = fetched.get(1);
        MatcherAssert.assertThat(
            organization.name(),
            Matchers.equalTo("sherif_test")
        );
        MatcherAssert.assertThat(
            organization.createdAt(),
            Matchers.equalTo("2017-04-24T19:42:59.560Z")
        );
        MatcherAssert.assertThat(
            organization.updatedAt(),
            Matchers.equalTo("2017-04-24T19:42:59.599Z")
        );
        MatcherAssert.assertThat(
            organization.company(),
            Matchers.equalTo("")
        );
        MatcherAssert.assertThat(
            organization.apiKey(),
            Matchers.equalTo("60dc805d094e3972eb1a")
        );
    }
    
    /**
     * RtOrganizations can return an organization with given name.
     * @throws IOException If something goes wrong.
     */
    @Test
    public void returnsOrganizationWithName() throws IOException {
        final MkContainer container = new MkGrizzlyContainer().next(
            new MkAnswer.Simple(
                HttpURLConnection.HTTP_OK,
                this.readResource("organizations.json")
            )
        ).start(); 
        final Organizations organizations = new RtOrganizations(
            new JdkRequest(container.home())
        );
        
        MatcherAssert.assertThat(
            organizations.organization("sherifwaly_orga").apiKey(),
            Matchers.equalTo("88870f2710dba853a326")
        );
    }
    
    /**
     * RtOrganizations throws IOException with invalid organization name.
     * @throws IOException If something goes wrong.
     */
    @Test(expected=IOException.class)
    public void throwsIoExceptionForInvalidOrganizationName()
        throws IOException {
        final MkContainer container = new MkGrizzlyContainer().next(
            new MkAnswer.Simple(
                HttpURLConnection.HTTP_OK,
                this.readResource("organizations.json")
            )
        ).start(); 
        final Organizations organizations = new RtOrganizations(
            new JdkRequest(container.home())
        );
            
        organizations.organization("invalid_orga");
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

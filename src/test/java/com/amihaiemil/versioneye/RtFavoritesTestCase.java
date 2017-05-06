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
 * Unit tests for {@link RtFavorites}.
 * @author Sherif Waly (sherifwaly95@gmail.com)
 * @version $Id$
 * @since 1.0.0
 * @todo #40:15min/DEV Add integration tests for RtFavorites.
 */

@SuppressWarnings("resource")
public final class RtFavoritesTestCase {
    
    /**
     * RtFavorites can fetch a user's favorites.
     * @throws IOException If something goes wrong.
     */
    @Test
    public void fetchesFavorites() throws IOException {
        final MkContainer container = new MkGrizzlyContainer().next(
            new MkAnswer.Simple(
                HttpURLConnection.HTTP_OK,
                this.readResource("favorites.json")
            )
        ).start(); 
        final Favorites favorites = new RtFavorites(
            new JdkRequest(container.home())
        );
        
        final List<Favorite> fetched = favorites.fetch(1);
        
        MatcherAssert.assertThat(fetched.size(), Matchers.is(2));
        
        Favorite favorite = fetched.get(0);
        MatcherAssert.assertThat(
            favorite.name(), Matchers.equalTo("doctrine/common")
        );
        MatcherAssert.assertThat(
            favorite.language(), Matchers.equalTo("php")
        );
        MatcherAssert.assertThat(
            favorite.productKey(), Matchers.equalTo("doctrine/common")
        );
        MatcherAssert.assertThat(
            favorite.version(), Matchers.equalTo("2.7.2")
        );
        MatcherAssert.assertThat(
            favorite.productType(), Matchers.equalTo("composer")
        );
        
        favorite = fetched.get(1);
        MatcherAssert.assertThat(
            favorite.name(), Matchers.equalTo("doctrine/doctrine-module")
        );
        MatcherAssert.assertThat(
            favorite.language(), Matchers.equalTo("php")
        );
        MatcherAssert.assertThat(
            favorite.productKey(),
            Matchers.equalTo("doctrine/doctrine-module")
        );
        MatcherAssert.assertThat(
            favorite.version(), Matchers.equalTo("2.0.0")
        );
        MatcherAssert.assertThat(
            favorite.productType(), Matchers.equalTo("composer")
        );
        
        MatcherAssert.assertThat(
            container.take().uri().toString(),
            Matchers.equalTo("/favorites?page=1")
        );
    }
    
    /**
     * RtFavorites can return user data.
     * @throws IOException If something goes wrong.
     */
    @Test
    public void returnsUserData() throws IOException {
        final MkContainer container = new MkGrizzlyContainer().next(
            new MkAnswer.Simple(
                HttpURLConnection.HTTP_OK,
                this.readResource("favorites.json")
            )
        ).start();
        final Favorites favorites = new RtFavorites(
            new JdkRequest(container.home())
        );
   
        UserData userData = favorites.userData();
        MatcherAssert.assertThat(
            userData.fullName(),
            Matchers.is("datamd")
        );
        MatcherAssert.assertThat(
            userData.username(),
            Matchers.is("datamd")
        );
    }
    
    /**
     * RtFavorites can return paging.
     * @throws IOException If something goes wrong.
     */
    @Test
    public void returnsPaging() throws IOException {
        final MkContainer container = new MkGrizzlyContainer().next(
            new MkAnswer.Simple(
                HttpURLConnection.HTTP_OK,
                this.readResource("favorites.json")
            )
        ).start();
        final Favorites favorites = new RtFavorites(
            new JdkRequest(container.home())
        );
        
        Paging paging = favorites.paging();
        MatcherAssert.assertThat(
            paging.currentPage(),
            Matchers.is(1)
        );
        MatcherAssert.assertThat(
            paging.itemsPerPage(),
            Matchers.is(30)
        );
        MatcherAssert.assertThat(
            paging.totalPages(),
            Matchers.is(3)
        );
        MatcherAssert.assertThat(
            paging.totalEntries(),
            Matchers.is(70)
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

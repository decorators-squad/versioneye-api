package com.amihaiemil.versioneye;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;

import org.apache.commons.io.IOUtils;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.Test;

import com.jcabi.http.mock.MkAnswer;
import com.jcabi.http.mock.MkContainer;
import com.jcabi.http.mock.MkGrizzlyContainer;
import com.jcabi.http.request.JdkRequest;

/**
 * Unit tests for {@link RtRepositories}.
 * @author Sherif Waly (sherifwaly95@gmail.com)
 * @version $Id$
 * @since 1.0.0
 * @todo #89:15min/DEV Add more unit tests for RtRepositories.
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
  
        Repositories repositories = new RtRepositories(
            new JdkRequest(container.home())
        );
        
        MatcherAssert.assertThat(
            repositories.fetch(1).size(), Matchers.is(14)
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

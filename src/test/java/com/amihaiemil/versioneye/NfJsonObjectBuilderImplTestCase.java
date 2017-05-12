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

import javax.json.Json;
import javax.json.JsonObject;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.Test;
import com.amihaiemil.json.NfJsonObjectBuilder;

/**
 * Unit tests for {@link NfJsonObjectBuilderImpl}.
 * @author Mihai Andronache (amihaiemil@gmail.com)
 * @version $Id$
 * @since 1.0.0
 *
 */
public final class NfJsonObjectBuilderImplTestCase {

    /**
     * NfJsonObjectBuilderImpl can build the same object multiple times.
     */
    @Test
    public void doesNotFlushAfterBuild() {
        final NfJsonObjectBuilder builder = new NfJsonObjectBuilderImpl()
            .add("name", "Mihai")
            .add("age", 23)
            .add("developer", true)
            .add("gpa", 3.5);
        MatcherAssert.assertThat(
            builder.build().getString("name"), Matchers.equalTo("Mihai")
        );
        MatcherAssert.assertThat(
            builder.build().getString("name"), Matchers.equalTo("Mihai")
        );
        MatcherAssert.assertThat(
            builder.build().getString("name"), Matchers.equalTo("Mihai")
        );
    }
    
    /**
     * NfJsonObjectBuilderImpl can build a simple JsonObject, with no
     * other structures (other json objects) in it.
     */
    @Test
    public void buildSimpleJsonObject() {
        final NfJsonObjectBuilder builder = new NfJsonObjectBuilderImpl();
        final JsonObject contributor = builder
            .add("name", "Mihai")
            .add("age", 23)
            .add("developer", true)
            .add("gpa", 3.5)
            .build();
        MatcherAssert.assertThat(
            contributor.getString("name"), Matchers.equalTo("Mihai")
        );
        MatcherAssert.assertThat(
            contributor.getBoolean("developer"), Matchers.is(true)
        );
        MatcherAssert.assertThat(
            contributor.getInt("age"), Matchers.is(23)
        );
        MatcherAssert.assertThat(
            contributor.getJsonNumber("gpa").doubleValue(), Matchers.is(3.5)
        );
    }
    
    /**
     * NfJsonObjectBuilderImpl can build an empty JsonObject.
     */
    @Test
    public void buildeEmptyJsonObject() {
        MatcherAssert.assertThat(
            new NfJsonObjectBuilderImpl().build().toString(),
            Matchers.equalTo("{}")
        );
    }
    
    /**
     * NfJsonObjectBuilderImpl can build a comples JsonObject, with
     * other structures (other json objects) in it.
     */
    @Test
    public void buildComplexJsonObject() {
        final NfJsonObjectBuilder builder = new NfJsonObjectBuilderImpl();
        final JsonObject project = builder
            .add("name", "charles-rest")
            .add(
                "technologies",
                Json.createArrayBuilder()
                    .add("Java")
                    .add("ReactJS")
                    .add("ElasticSearch")
                    .add("Github")
                    .build()
            )
            .add(
                "team",
                Json.createObjectBuilder()
                    .add("architect", "amihaiemil")
                    .add(
                        "developers",
                        Json.createArrayBuilder()
                            .add("amihaiemil")
                            .add("SherifWaly")
                            .add("salijkan")
                    )
                    .build()
            )
            .build();
        MatcherAssert.assertThat(
            project.getString("name"), Matchers.equalTo("charles-rest")
        );
        MatcherAssert.assertThat(
            project.getJsonArray("technologies").size(), Matchers.is(4)
        );
        MatcherAssert.assertThat(
            project.getJsonObject("team").getString("architect"),
            Matchers.equalTo("amihaiemil")
        );
        MatcherAssert.assertThat(
            project.getJsonObject("team").getJsonArray("developers").size(),
            Matchers.is(3)
        );
    }
}

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
import java.util.Iterator;
import java.util.List;

/**
 * A page of vulnerabilities.
 * @author Mihai Andronache (amihaiemil@gmail.com)
 * @version $Id$
 * @since 1.0.0
 * @todo #83:30min/DEV Unit test this class, similar to how
 *  CommentsPage and FavoritesPage are tested.
 *
 */
final class VulnerabilitiesPage implements Page<Vulnerability> {

    /**
     * Vulnerabilities.
     */
    private Security vulnerabilities;
    
    /**
     * Programming language of the vulnerable projects.
     */
    private String language;
    
    /**
     * Number of this page.
     */
    private int number;
    
    /**
     * Ctor.
     * @param vulnerabilities Vulnerabilities from this page.
     * @param language Programming language of the vulnerable projects.
     */
    VulnerabilitiesPage(final Security vulnerabilities, final String language) {
        this(vulnerabilities, language,  1);
    }
    
    /**
     * Ctor.
     * @param vulnerabilities Vulnerabilities from this page.
     * @param language Programming language of the vulnerable projects.
     * @param number Number of this page.
     */
    VulnerabilitiesPage(
        final Security vulnerabilities, final String language, final int number
    ) {
        this.vulnerabilities = vulnerabilities;
        this.language = language;
        this.number = number;
    }
    
    @Override
    public List<Vulnerability> fetch() throws IOException {
        return this.vulnerabilities.language(this.language, this.number);
    }

    @Override
    public Paging paging() throws IOException {
        return this.vulnerabilities.paging(this.number);
    }

    @Override
    public Iterator<Page<Vulnerability>> iterator() {
        return new VulnerabilityPageIt(this.vulnerabilities, this.language);
    }

    /**
     * Iterator over the comments pages.
     */
    private final class VulnerabilityPageIt
        implements Iterator<Page<Vulnerability>> {
        
        /**
         * Vulnerabilities.
         */
        private Security vulnerabilities;
        
        /**
         * Programming language of the vulnerable projects.
         */
        private String language;
        
        /**
         * Number of this page.
         */
        private int number = 1;  
        
        /**
         * Ctor.
         * @param vulnerabilities Vulnerabilities from this page.
         * @param language Programming language of the vulnerable projects.
         */
        VulnerabilityPageIt(
            final Security vulnerabilities, final String language
        ) {
            this.vulnerabilities = vulnerabilities;
        }
        
        @Override
        public boolean hasNext() {
            try {
                final Paging paging = this.vulnerabilities.paging(this.number);
                return paging.currentPage() <= paging.totalPages();
            } catch (final IOException ex) {
                throw new IllegalStateException(
                    "IOException occured when checking "
                    + "vulnerabilities page number " + this.number, ex
                );
            }
        }

        @Override
        public Page<Vulnerability> next() {
            final Page<Vulnerability> next = new VulnerabilitiesPage(
                this.vulnerabilities, this.language, this.number
            );
            this.number++;
            return next;
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException(
                "Cannot remove a page of vulnerabilities!"
            );        
        }

    }
}

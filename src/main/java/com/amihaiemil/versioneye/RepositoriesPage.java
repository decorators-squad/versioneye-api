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
 * A page of repositories.
 * @author Sherif Waly (sherifwaly95@gmail.com)
 * @version $Id$
 * @since 1.0.0
 * @todo #81:30min/DEV Write some unit tests for this class.
 *  They should be simmilar to those for CommentsPage.
 *
 */
final class RepositoriesPage implements Page<Repository> {

    /**
     * Repositories.
     */
    private Repositories repositories;
    
    /**
     * Number of this page.
     */
    private int number;
    
    /**
     * Ctor.
     * @param repositories Repositories from this page.
     */
    RepositoriesPage(final Repositories repositories) {
        this(repositories,  1);
    }
    
    /**
     * Ctor.
     * @param repositories Repositories from this page.
     * @param number Number of this page.
     */
    RepositoriesPage(
        final Repositories repositories, final int number) {
        this.repositories = repositories;
        this.number = number;
    }
    
    @Override
    public List<Repository> fetch() throws IOException {
        return this.repositories.fetch(this.number);
    }

    @Override
    public Paging paging() throws IOException {
        return this.repositories.paging(this.number);
    }

    @Override
    public Iterator<Page<Repository>> iterator() {
        return new RepositoryPageIt(this.repositories);
    }

    /**
     * Iterator over the comments pages.
     */
    private final class RepositoryPageIt
        implements Iterator<Page<Repository>> {
        
        /**
         * Repositories.
         */
        private Repositories repositories;
        
        /**
         * Number of this page.
         */
        private int number = 1;  
        
        /**
         * Ctor.
         * @param repositories Repositories from this page.
         */
        RepositoryPageIt(final Repositories repositories) {
            this.repositories = repositories;
        }
        
        @Override
        public boolean hasNext() {
            try {
                final Paging paging = this.repositories.paging(this.number);
                return paging.currentPage() <= paging.totalPages();
            } catch (final IOException ex) {
                throw new IllegalStateException(
                    "IOException occured when checking "
                    + "repositories page number " + this.number, ex
                );
            }
        }

        @Override
        public Page<Repository> next() {
            final Page<Repository> next = new RepositoriesPage(
                this.repositories, this.number
            );
            this.number++;
            return next;
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException(
                "Cannot remove a page of repositories!"
            );        
        }

    }
}

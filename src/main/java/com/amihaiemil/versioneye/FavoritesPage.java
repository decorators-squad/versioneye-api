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
 * A page of Favorites.
 * @author Sherif Waly (sherifwaly95@gmail.com)
 * @version $Id$
 * @since 1.0.0
 */
final class FavoritesPage implements Page<Favorite> {

    /**
     * Favorites.
     */
    private Favorites favorites;
    
    /**
     * Number of this page.
     */
    private int number;
    
    /**
     * Ctor.
     * @param favorites Favorites from this page.
     */
    FavoritesPage(final Favorites favorites) {
        this(favorites, 1);
    }
    
    /**
     * Ctor.
     * @param favorites Favorites from this page.
     * @param number Number of this page.
     */
    FavoritesPage(final Favorites favorites, final int number) {
        this.favorites = favorites;
        this.number = number;
    }
    
    @Override
    public List<Favorite> fetch() throws IOException {
        return this.favorites.fetch(this.number);
    }

    @Override
    public Paging paging() throws IOException {
        return this.favorites.paging(this.number);
    }

    @Override
    public Iterator<Page<Favorite>> iterator() {
        return new FavoritesPageIt(this.favorites);
    }

    /**
     * Iterator over the favorites pages.
     */
    private final class FavoritesPageIt implements Iterator<Page<Favorite>> {
        
        /**
         * Favorites.
         */
        private Favorites favorites;
        
        /**
         * Number of this page.
         */
        private int number = 1;  
        
        /**
         * Ctor.
         * @param favorites Favorites from this page.
         */
        FavoritesPageIt(final Favorites favorites) {
            this.favorites = favorites;
        }
        
        @Override
        public boolean hasNext() {
            try {
                final Paging paging = this.favorites.paging(this.number);
                return paging.currentPage() <= paging.totalPages();
            } catch (final IOException ex) {
                throw new IllegalStateException(
                    "IOException occured when checking "
                    + "favorites page number " + this.number, ex
                );
            }
        }

        @Override
        public Page<Favorite> next() {
            Page<Favorite> next = 
                new FavoritesPage(this.favorites, this.number);
            this.number++;
            return next;
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException(
                "Cannot remove a page of favorites!"
            );        
        }

    }

}


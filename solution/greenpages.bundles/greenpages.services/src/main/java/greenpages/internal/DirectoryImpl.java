/*******************************************************************************
 * Copyright (c) 2008, 2010 VMware Inc.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *   VMware Inc. - initial contribution
 *******************************************************************************/

package greenpages.internal;

import greenpages.Directory;
import greenpages.Listing;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;

/**
 * Stub implementation of {@link Directory} that allows for simple testing without a database.
 *
 * Uses Spring 2.* component scanning to find this class and create a bean. The name for this bean
 * is extracted from the {@link Component} annotation.
 */
@Component("directory")
public class DirectoryImpl implements Directory {

    private static final Listing ROD_JOHNSON = new ImmutableListing(1, "Rod", "Johnson", "rod.johnson@springsource.com");

    /**
     * {@inheritDoc}
     * 
     * Stub implementation will match only for term '<code>johnson</code>'.
     */
    @Override
    public List<Listing> search(String term) {
        if (ROD_JOHNSON.getLastName().equalsIgnoreCase(term)) {
            Listing l = ROD_JOHNSON;

            return Collections.singletonList(l);
        } else {
            return Collections.emptyList();
        }
    }

    /**
     * {@inheritDoc}
     * 
     * Stub implementation will return a {@link Listing} for ID 1.
     */
    @Override
    public Listing findListing(int id) {
        if (id == ROD_JOHNSON.getListingNumber()) {
            return ROD_JOHNSON;
        } else {
            return null;
        }
    }

}

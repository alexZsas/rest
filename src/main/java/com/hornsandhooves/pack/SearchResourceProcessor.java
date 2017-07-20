package com.hornsandhooves.pack;

import org.springframework.data.rest.webmvc.RepositorySearchesResource;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.ResourceProcessor;
import org.springframework.stereotype.Component;

/**
 * Created by apriseko on 20.07.2017.
 */
@Component
public class SearchResourceProcessor implements ResourceProcessor<RepositorySearchesResource> {
    @Override
    public RepositorySearchesResource process(RepositorySearchesResource repositorySearchesResource) {
        final String search = repositorySearchesResource.getId().getHref();
        final Link complete = new Link(search + "/orderses{?q}").withRel("orderses");
        repositorySearchesResource.add(complete);

        return repositorySearchesResource;
    }
}

package eu.sanprojects.kickstart.web;

import eu.sanprojects.kickstart.model.Bookmark;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

/**
 * Created by sanjeya on 21/02/15.
 */
@RepositoryRestResource(collectionResourceRel = "bookmarks", path = "bookmarks")
public interface BookmarkRestRepoController extends PagingAndSortingRepository<Bookmark, Long> {
}
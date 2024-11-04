package org.skyhigh.afishadevappgui.service.logic.search;

import lombok.AllArgsConstructor;
import org.skyhigh.afishadevappgui.common.sort.SortDirection;
import org.skyhigh.afishadevappgui.common.validation.CommonFlkException;
import org.skyhigh.afishadevappgui.data.datasource.entity.Author;
import org.skyhigh.afishadevappgui.data.repository.AuthorRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@AllArgsConstructor
public class AuthorSearchServiceImpl implements AuthorSearchService {
    private final AuthorRepository authorRepository;

    @Override
    public List<Author> searchAuthors(UUID authorId, String authorLogin) throws CommonFlkException {
        if (authorId != null && authorLogin != null && !authorLogin.isEmpty())
            return null;
        if (authorId != null) {
            ArrayList<Author> authors = new ArrayList<>();
            authors.add(authorRepository.getAuthorById(authorId));
            return authors;
        }
        if (authorLogin != null && !authorLogin.isEmpty()) {
            ArrayList<Author> authors = new ArrayList<>();
            authors.add(authorRepository.getAuthorByName(authorLogin));
            return authors;
        }
        return authorRepository.getAllAuthors(SortDirection.NONE, null);
    }
}

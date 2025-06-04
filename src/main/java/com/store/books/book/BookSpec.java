package com.store.books.book;

import org.springframework.data.jpa.domain.Specification;

public class BookSpec {

    public static Specification<Book> withOwnerId(String ownerId) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("createdBy"), ownerId);
    }
}
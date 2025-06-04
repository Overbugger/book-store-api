package com.store.books.book;

//import com.store.books.file.FileUtils;
import com.store.books.file.FileUtils;
import com.store.books.history.BookTransactionHistory;
import com.store.books.history.BookTransactionHistoryRepo;
import org.springframework.stereotype.Service;

@Service
public class BookMapper {
    private final BookTransactionHistoryRepo bookTransactionHistoryRepo;

    public BookMapper(BookTransactionHistoryRepo bookTransactionHistoryRepo) {
        this.bookTransactionHistoryRepo = bookTransactionHistoryRepo;
    }

    public Book toBook(BookRequest request) {
        return Book.builder()
                .id(request.id())
                .title(request.title())
                .isbn(request.isbn())
                .authorName(request.authorName())
                .synopsis(request.synopsis())
                .archived(false)
                .shareable(request.shareable())
                .build();
    }

    public BookResponse toBookResponse(Book book) {
        return BookResponse.builder()
                .id(book.getId())
                .title(book.getTitle())
                .authorName(book.getAuthorName())
                .isbn(book.getIsbn())
                .synopsis(book.getSynopsis())
                .rate(book.getRate())
                .archived(book.isArchived())
                .shareable(book.isShareable())
                 .owner(book.getOwner().fullName())
                .cover(FileUtils.readFileFromLocation(book.getBookCover()))
                .build();
    }


    public BorrowedBookResponse toBorrowedBookResponse(BookTransactionHistory bookTransactionHistoryRepo) {
        return BorrowedBookResponse.builder()
                .id(bookTransactionHistoryRepo.getBook().getId())
                .title(bookTransactionHistoryRepo.getBook().getTitle())
                .authorName(bookTransactionHistoryRepo.getBook().getAuthorName())
                .isbn(bookTransactionHistoryRepo.getBook().getIsbn())
                .rate(bookTransactionHistoryRepo.getBook().getRate())
                .returned(bookTransactionHistoryRepo.isReturned())
                .returnApproved(bookTransactionHistoryRepo.isReturnApproved())
                .build();
    }
}

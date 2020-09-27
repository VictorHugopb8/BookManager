package com.digivox.seletiva.bookmanager.bookmanager.book.v1;

import com.digivox.seletiva.bookmanager.bookmanager.book.dto.BookDTO;
import com.digivox.seletiva.bookmanager.bookmanager.book.dto.DashboardDTO;
import com.digivox.seletiva.bookmanager.bookmanager.book.services.*;
import com.digivox.seletiva.bookmanager.bookmanager.exceptions.*;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/v1/books")
@RequiredArgsConstructor
public class BookManagerControllerV1 {

    private final PageBookService pageBookService;
    private final RegisterBookService registerBookService;
    private final RentBookService rentBookService;
    private final CancelBookReserveService cancelBookReserveService;
    private final ReserveBookService reserveBookService;
    private final DashboardService dashboardService;
    private final DevolutionBookService devolutionBookService;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public Page<BookDTO> page(Pageable pageable) {
        return this.pageBookService.page(pageable);
    }

    @PostMapping(path = "/register", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void save(@RequestBody @NonNull BookDTO entity) throws AlreadyRegisteredBookException {
        this.registerBookService.save(entity);
    }

    @PostMapping(path = "/cancel/{bookId}")
    public void cancel(
            @PathVariable("bookId") Long bookId,
            @RequestParam("nationalId") String nationalId
    ) throws CustomerNotFoundException, BookHistoryNotFoundException {
        this.cancelBookReserveService.cancel(bookId, nationalId);
    }

    @PostMapping("/rent/{bookId}")
    public void rent(
            @PathVariable("bookId") Long bookId,
            @RequestParam("nationalId") String nationalId,
            @RequestParam("scheduledReturnDate") LocalDate scheduledReturnDate
    ) throws BookIsRentedOrReservedException, CustomerNotFoundException {
        this.rentBookService.rent(bookId, nationalId, scheduledReturnDate);
    }

    @PostMapping("/reserve/{bookId}")
    public void reserve(
            @PathVariable("bookId") Long bookId,
            @RequestParam("nationalId") String nationalId,
            @RequestParam("scheduledReserveDate") LocalDate scheduledReserveDate
    ) throws BookHistoryNotFoundException, BookNotFoundException, CustomerNotFoundException, BookIsRentedOrReservedException {
        this.reserveBookService.reserve(bookId, nationalId, scheduledReserveDate);
    }

    @GetMapping("/dashboard/{type}")
    public List<DashboardDTO> getDashBoardInfo(@PathVariable("type") String type) {
        return this.dashboardService.getDashboardInfo(type);
    }

    @PostMapping(value = "/devolution/{bookId}")
    public String returnBook(@PathVariable("bookId") Long bookId, @RequestParam("nationalId") String nationalId)
            throws BookHistoryNotFoundException {
        return this.devolutionBookService.returnBook(bookId, nationalId);
    }
}

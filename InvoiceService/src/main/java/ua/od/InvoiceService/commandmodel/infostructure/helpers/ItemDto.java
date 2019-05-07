package ua.od.InvoiceService.commandmodel.infostructure.helpers;

public class ItemDto {
    private final Long bookId;
    private final Double bookPrice;

    public ItemDto(Long bookId, Double bookPrice) {

        this.bookId = bookId;
        this.bookPrice = bookPrice;
    }

    public Long getBookId() {

        return bookId;
    }

    public Double getBookPrice() {

        return bookPrice;
    }
}

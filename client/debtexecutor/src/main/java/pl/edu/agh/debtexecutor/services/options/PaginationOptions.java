package pl.edu.agh.debtexecutor.services.options;

public class PaginationOptions {
    private int pageSize;
    private int pageNumber = 0;

    public PaginationOptions(int pageSize) {
        this.pageSize = pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public void setPageNumber(int pageNumber) {
        this.pageNumber = pageNumber;
    }

    public int getPageSize() {
        return pageSize;
    }

    public int getPageNumber() {
        return pageNumber;
    }
}

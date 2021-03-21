package usercase.domain;

public class PageBean {
    private int page;
    private int pageTotal;
    private long userTotal;

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getPageTotal() {
        return pageTotal;
    }

    public void setPageTotal(int pageTotal) {
        this.pageTotal = pageTotal;
    }

    public long getUserTotal() {
        return userTotal;
    }

    public void setUserTotal(long userTotal) {
        this.userTotal = userTotal;
    }

    public PageBean(){};

    public PageBean(int page, int pageTotal, int userTotal) {
        this.page = page;
        this.pageTotal = pageTotal;
        this.userTotal = userTotal;
    }
}

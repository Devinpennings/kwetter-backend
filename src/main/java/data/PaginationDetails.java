package data;

import java.security.InvalidParameterException;

/**
 * Created by Devin
 */
public class PaginationDetails {

    private int limitPerPage;
    private int currentPage = 1;

    public int getLimitPerPage() {
        return this.limitPerPage;
    }

    public int getCurrentPage() {
        return this.currentPage;
    }

    public int getIndexFrom(){
        return (this.currentPage - 1) * this.limitPerPage;
    }

    public int getIndexTo(){
        return this.getIndexFrom() + this.limitPerPage;
    }

    public PaginationDetails(int limitPerPage){

        if(limitPerPage < 1) throw new InvalidParameterException("Limit per page should be positive");

        this.limitPerPage = limitPerPage;

    }

    public PaginationDetails(int limitPerPage, int currentPage){

        if(limitPerPage < 1) throw new InvalidParameterException("Limit per page should be positive");
        if(currentPage < 1) throw new InvalidParameterException("Limit per page should be positive");

        this.limitPerPage = limitPerPage;
        this.currentPage = currentPage;

    }

}

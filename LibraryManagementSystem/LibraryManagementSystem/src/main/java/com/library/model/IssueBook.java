package com.library.model;

import java.sql.Date;

public class IssueBook {

    private int issueId;
    private int studentId;
    private int bookId;
    private Date issueDate;
    private Date dueDate;
    private Date returnDate;
    private double fine;


    public IssueBook() {

    }


    public int getIssueId() {
        return issueId;
    }


    public void setIssueId(int issueId) {
        this.issueId = issueId;
    }


    public int getStudentId() {
        return studentId;
    }


    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }


    public int getBookId() {
        return bookId;
    }


    public void setBookId(int bookId) {
        this.bookId = bookId;
    }


    public Date getIssueDate() {
        return issueDate;
    }


    public void setIssueDate(Date issueDate) {
        this.issueDate = issueDate;
    }


    public Date getDueDate() {
        return dueDate;
    }


    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }


    public Date getReturnDate() {
        return returnDate;
    }


    public void setReturnDate(Date returnDate) {
        this.returnDate = returnDate;
    }


    public double getFine() {
        return fine;
    }


    public void setFine(double fine) {
        this.fine = fine;
    }
}

package by.itacademy.taskservice.core.dto;

import java.util.List;

public class PageDTO {
    private int number;
    private int size;
    private int totalPages;
    private long totalElements;
    private boolean first;
    private int numberElements;
    private boolean last;
    private List<?> content;

    public PageDTO() {
    }

    public PageDTO(int number,
                   int size,
                   int totalPages,
                   long totalElements,
                   boolean first,
                   int numberElements,
                   boolean last,
                   List<?> content) {
        this.number = number;
        this.size = size;
        this.totalPages = totalPages;
        this.totalElements = totalElements;
        this.first = first;
        this.numberElements = numberElements;
        this.last = last;
        this.content = content;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }

    public long getTotalElements() {
        return totalElements;
    }

    public void setTotalElements(long totalElements) {
        this.totalElements = totalElements;
    }

    public boolean isFirst() {
        return first;
    }

    public void setFirst(boolean first) {
        this.first = first;
    }

    public int getNumberElements() {
        return numberElements;
    }

    public void setNumberElements(int numberElements) {
        this.numberElements = numberElements;
    }

    public boolean isLast() {
        return last;
    }

    public void setLast(boolean last) {
        this.last = last;
    }

    public List<?> getContent() {
        return content;
    }

    public void setContent(List<?> content) {
        this.content = content;
    }
}

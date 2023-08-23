package by.itAcademy.auditservice.core.exception;

public class NotFoundException extends RuntimeException {
    private String filed;

    public NotFoundException(){}

    public NotFoundException(String massage){
        super(massage);
    }

    public NotFoundException(String massage, String filed) {
        super(massage);
        this.filed = filed;
    }

    public String getFiled(){
        return filed;
    }

    public String getField() {
        return filed;
    }
}

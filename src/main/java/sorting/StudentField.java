package sorting;

public enum StudentField {
    FULL_NAME,
    GROUP_NUMBER,
    AVERAGE_SCORE,
    RECORD_STUDENT_BOOK_NUMBER;

    public boolean isNumeric(){
        switch (this){
            case FULL_NAME -> {return false;}
            case GROUP_NUMBER -> {return true;}
            case AVERAGE_SCORE -> {return true;}
            case RECORD_STUDENT_BOOK_NUMBER -> {return true;}
            default -> {return false;}
        }
    }
}

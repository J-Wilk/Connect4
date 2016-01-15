public enum BoardSlot{
    EMPTY("X"), RED("R"), YELLOW("Y");
    
    private final String value;
    
    private BoardSlot(String value){
        this.value = value;
    }
    
    public String toString(){
        return value;
    }
}
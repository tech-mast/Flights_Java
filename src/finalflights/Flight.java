
package finalflights;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Flight {

    public Flight(int id, Date onDay, String fromCode, String toCode, Type type, int passengers)throws InvalidDataException {
        setId(id);
        setOnDay(onDay);
        setFromCode(fromCode);
        setToCode(toCode);
        setType(type);
       this.passengers = passengers;
    }
    
    
    int id;
    Date onDay;
    String fromCode;
    String toCode;
    Type type;
    int passengers;

    
    public static String isFromCodeValid(String fromcode) {
        if (!fromcode.matches("[A-Z]{3,5}")) {
            return "From Code  must be  3-5 characters (uppercase)";
        } else {
            return null;
        }
    }

    // null if valid, otherwise error string is returned
    public static String isToCodeValid(String tocode) {
        if (!tocode.matches("[A-Z]{3,5}")) {
            return "To Code  must be  3-5 characters (uppercase)";
        } else {
            return null;
        }
    }
    
    
    
    public enum Type {
        Domestic, International, Private
    };

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getOnDay() {
        return onDay;
    }

    public void setOnDay(Date onDay) {
        this.onDay = onDay;
    }

    public String getFromCode() {
      
        return fromCode;
    }

    public void setFromCode(String fromCode) {
        
        String msg;
        if ((msg = isFromCodeValid(fromCode)) != null) {
            throw new IllegalArgumentException(msg);
        }
        
        this.fromCode = fromCode;
    }

    public String getToCode() {
        return toCode;
    }

    public void setToCode(String toCode) {
        
        String msg;
        if ((msg = isToCodeValid(toCode)) != null) {
            throw new IllegalArgumentException(msg);
        }
        this.toCode = toCode;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public int getPassengers() {
        return passengers;
    }

    public void setPassengers(int passengers) {
        this.passengers = passengers;
    }

    static SimpleDateFormat dateFormatDisplay = new SimpleDateFormat("dd-MM-yyyy");
    
    @Override
    public String toString() {
       return String.format("id: %d, Date: %s, From Code: %s, To Code: %s, Type: %s, Passengers: %d", id, dateFormatDisplay.format(onDay), fromCode,toCode,type,passengers);
    }
    
    
    
}

//This is the abstract class Person. This class is going to be inherited by the Student and Faculty classes.
public abstract class Person {
    private String firstName;
    private String middleName;
    private String lastName;
    private String email;
    private String phoneNumber;
    private String streetAddress;
    private String city;
    private String state;
    private String zipCode;
    protected int id;

    // constructor
    protected Person(String firstName, String middleName, String lastName, String email, String phoneNumber, String streetAddress, String city, String state, String zipCode) {
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.streetAddress = streetAddress;
        this.city = city;
        this.state = state;
        this.zipCode = zipCode;
    }

    // getters
    public String getFirstName() 
    { 
        return firstName;
    }
    public String getMiddleName() 
    { 
        return middleName; 
    }
    public String getLastName() 
    { 
        return lastName; 
        
    }
    public String getEmail() 
    { 
        return email; 
        
    }
    public String getPhoneNumber() 
    { 
        return phoneNumber; 
        
    }
    public String getStreetAddress() 
    { 
        return streetAddress; 
        
    }
    public String getCity() 
    { 
        return city; 
        
    }
    public String getState() 
    { 
        return state; 
        
    }
    public String getZipCode() 
    { 
        return zipCode; 
        
    }
    public int getId() 
    { 
        return id; 
        
    }
    
    //setters
    public void setFirstName(String firstName) 
    { 
        this.firstName = firstName; 
        
    }
    public void setMiddleName(String middleName) 
    { 
        this.middleName = middleName; 
        
    }
    public void setLastName(String lastName) 
    { 
        this.lastName = lastName; 
        
    }
    public void setEmail(String email) 
    { 
        this.email = email; 
        
    }
    public void setPhoneNumber(String phoneNumber) 
    { 
        this.phoneNumber = phoneNumber; 
        
    }
    public void setStreetAddress(String streetAddress) 
    { 
        this.streetAddress = streetAddress; 
        
    }
    public void setCity(String city) 
    { 
        this.city = city; 
        
    }
    public void setState(String state) 
    { 
        this.state = state; 
        
    }
    public void setZipCode(String zipCode) 
    { 
        this.zipCode = zipCode; 
        
    }
    public void setId(int id) 
    { 
        this.id = id; 
        
    }
    //Abstract method
    public abstract String toString();
}

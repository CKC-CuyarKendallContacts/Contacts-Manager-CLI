public class Contact {
    private String name;
    private String phoneNumber;

    public Contact(String aName, String aPhoneNum){
        this.name = aName;
        this.phoneNumber = aPhoneNum;
    }

    //getters
    public String getName(){
        return this.name;
    }

    public String getPhoneNumber(){
        return this.phoneNumber;
    }

    //setters
    public void setName(String name) {
        this.name = name;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }


}

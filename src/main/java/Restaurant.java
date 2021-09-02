import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class Restaurant {
    private String name;
    private String location;
    public LocalTime openingTime;
    public LocalTime closingTime;
    private List<Item> menu = new ArrayList<Item>();

    public Restaurant(String name, String location, LocalTime openingTime, LocalTime closingTime) {
        this.name = name;
        this.location = location;
        this.openingTime = openingTime;
        this.closingTime = closingTime;
    }

    public Restaurant() {

    }

    public boolean isRestaurantOpen() {

        boolean isOpen;

        if ((getCurrentTime().isAfter(this.openingTime)) && (getCurrentTime().isBefore(this.closingTime))) {
            isOpen = true;
        } else {
            isOpen = false;
        }

        return isOpen;
    }

    public LocalTime getCurrentTime(){ return  LocalTime.now(); }

    public List<Item> getMenu() { return menu; }

    private Item findItemByName(String itemName){
        for(Item item: menu) {
            if(item.getName().equals(itemName))
                return item;
        }
        return null;
    }

    public void addToMenu(String name, int price) {
        Item newItem = new Item(name,price);
        menu.add(newItem);
    }
    
    public void removeFromMenu(String itemName) throws ItemNotFoundException {

        Item itemToBeRemoved = findItemByName(itemName);
        if (itemToBeRemoved == null)
            throw new ItemNotFoundException(itemName);

        menu.remove(itemToBeRemoved);
    }

    public void displayDetails(){
        System.out.println("Restaurant:"+ name + "\n"
                +"Location:"+ location + "\n"
                +"Opening time:"+ openingTime +"\n"
                +"Closing time:"+ closingTime +"\n"
                +"Menu:"+"\n"+getMenu());

    }

    public String getName() {
        return name;
    }


    public int getOrderTotal(List<String> orderedItems) {
        int totalPrice = 0;
        for (String itemName: orderedItems) {
            for (Item item:menu) {
                if (item.getName().equals(itemName)) {
                    totalPrice += item.getPrice();
                    break;
                }
            }
        }
        return totalPrice;
    }



    @Override
    public boolean equals(Object other) {
        if (!(other instanceof Restaurant))
            return false;

        Restaurant r = (Restaurant) other;

        return r.name.equals(this.name);
    }
}

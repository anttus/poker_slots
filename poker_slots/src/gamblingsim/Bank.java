package gamblingsim;

public class Bank {
    private double money;
    
    //Money getter
    public double getMoney() {
        money = Math.round(money * 100.0) / 100.0;
        return money;
    }
    
    //Money setter
    public void setMoney(double newMoney) {
        money = newMoney;
    }
    
    //Money adder
    public void add(double add) {
        money += add;
    }
    
    //Removing money
    public boolean remove(double remove) {
        boolean success;
        if (remove > money){
            success = false;
        }
        else {
            money -= remove;
            success = true;
        }
        return success;
    }
    
    //Bank's constructor
    public Bank(double money){
        this.money = money;
    }
 
}

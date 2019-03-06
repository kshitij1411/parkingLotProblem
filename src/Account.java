public class Account {

    int currentAmount;
    Owner owner;
    Auditor auditor;

    public Account(int currentAmount, Owner owner, Auditor auditor) {
        this.currentAmount = currentAmount;
        this.owner = owner;
        this.auditor = auditor;
    }

    public int addMoney(int amount) {
        currentAmount = currentAmount + amount;
        return currentAmount;
    }

    public int withdrawMoney(int amount) {
        if (currentAmount < amount && owner != null) {
            owner.sendEmail(currentAmount - amount);

        }
        this.auditor.notifywithdraw(amount);
        this.currentAmount = currentAmount - amount;
        return amount;
    }


    public void setOwner(Owner owner) {
        this.owner = owner;
    }
}

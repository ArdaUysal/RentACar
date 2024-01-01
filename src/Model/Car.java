package Model;

public class Car {
	private int id;
	String brand, gear, fuel;
	int capacity, deposit, rental_fee, count;
		
	public Car(int id, String brand, String gear, String fuel, int capacity, int deposit, int rental_fee, int count) {
		this.id = id;
		this.brand = brand;
		this.gear = gear;
		this.fuel = fuel;
		this.capacity = capacity;
		this.deposit = deposit;
		this.rental_fee = rental_fee;
		this.count = count;	
	}

	public Car() {}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public String getGear() {
		return gear;
	}

	public void setGear(String gear) {
		this.gear = gear;
	}

	public String getFuel() {
		return fuel;
	}

	public void setFuel(String fuel) {
		this.fuel = fuel;
	}

	public int getCapacity() {
		return capacity;
	}

	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}

	public int getDeposit() {
		return deposit;
	}

	public void setDeposit(int deposit) {
		this.deposit = deposit;
	}

	public int getRental_fee() {
		return rental_fee;
	}

	public void setRental_fee(int rental_fee) {
		this.rental_fee = rental_fee;
	}
	
	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}
	
	
	
	
	
	
	
	
	
}

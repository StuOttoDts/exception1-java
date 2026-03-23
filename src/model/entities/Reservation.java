package model.entities;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import model.exceptions.DomainException;

public class Reservation {

	private int roomNumber;
	private Date checkin;
	private Date checkout;
	
	private static SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
	
	public Reservation(Integer roomNumber, Date checkin, Date checkout) throws DomainException{
		if (!checkout.after(checkin)) {
			throw new DomainException("Error in reservation: Checkout date must be after checkin date");
		}
		
		this.checkin = checkin;
		this.checkout = checkout;
		this.roomNumber = roomNumber;
	}
	public int getRoomNumber() {
		return roomNumber;
	}
	public void setRoomNumber(int roomNumber) {
		this.roomNumber = roomNumber;
	}
	public Date getCheckin() {
		return checkin;
	}	
	public Date getCheckout() {
		return checkout;
	}
	public long duration(){
		long diff = checkout.getTime() - checkin.getTime();
		return TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);
	}
	public void updateDates(Date checkin, Date checkout) throws DomainException {
		
		Date now = new Date();
		if(checkin.before(now) || checkout.before(now)) {
			throw new DomainException("Reservation dates for update must be future dates");
		} 
		if (!checkout.after(checkin)) {
			throw new DomainException("Error in reservation: Checkout date must be after checkin date");
		}
		this.checkin = checkin;
		this.checkout = checkout;
	}

	@Override
	public String toString() {
		return "Room " 
			  + roomNumber 
			  + ", check-in " 
			  + sdf.format(checkin) 
			  + ", Checkout " 
			  + sdf.format(checkout) 
			  + ", " 
			  + duration()
			  + " nights";
	}

}

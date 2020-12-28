package labor.Entity;

import java.time.DayOfWeek;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import labor.Entity.Embedded.TimeSlot;

@Entity
@Table(name="labor_chart")
public class LaborSlot {
	// Day:Hour:Minute
	@Id
	private String id;
	
	@ManyToOne
	Member member;
	
	@ManyToOne(optional=false, 
			fetch = FetchType.LAZY)
	@JoinColumn(name = "position_id")
	@NotFound(action=NotFoundAction.IGNORE)
	private Position position;
	private TimeSlot timeSlot;
	
	LaborSlot() {
		
	}
	
	LaborSlot(Position position, DayOfWeek dayOfWeek, String stringTime, int positionNumber) {
		this.position = position;
		this.timeSlot = new TimeSlot(dayOfWeek, stringTime);
		setLaborSlotId(timeSlot, positionNumber);
		System.out.println("Creating LaborSlot with Id: " + this.id);
	}


	
	public void setTime(DayOfWeek dayOfWeek, String stringTime) {
		this.timeSlot = new TimeSlot(dayOfWeek, stringTime);
		
	}
	
	public String setLaborSlotId(TimeSlot timeSlot, int positionNumber) {
		String myId = new String(timeSlot.getDayOfWeek() + ":" + timeSlot.getTimeString() + ":" + position.getId() + ":" + positionNumber);
		this.id = myId;
		return myId;
	}
	
	@Override
	public String toString() {
		return new String(this.id);
	}

	public String getId() {
		return id;
	}

	public Position getPosition() {
		return position;
	}

	public TimeSlot getTime() {
		return timeSlot;
	}
	
	public void setMember(Member member) {
		this.member = member;
	}
	
	public Member getMember() {
		return member;
	}

}
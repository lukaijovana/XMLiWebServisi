package rs.ac.uns.ftn.informatika.domen;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class BrojacFaktura {
	@Id
	@GeneratedValue
	private Long id;

	public BrojacFaktura() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
}

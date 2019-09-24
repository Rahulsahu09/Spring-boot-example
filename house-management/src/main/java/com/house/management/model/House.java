package com.house.management.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/** This class object will store House attributes.
 * @author Rahul
 *
 */
@Entity
@Table(name = "House")
public class House {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
	@NotEmpty(message = "Please provide name")
	private String name;
	@NotNull(message = "Please provide house number")
	private Long number;
	@NotEmpty(message = "Please provide street")
	private String street;
	@Size(min = 6, max=8, message = "Please provide minimum six digit postal code")
	@NotEmpty(message = "Please provide postal coder")
	private String postalCode;
	@NotEmpty(message = "please provide city")
	private String city;
	@NotEmpty(message = "please provide state")
	private String state;

	public House() {
		
		
	};
	
	
	
	public House(@NotEmpty(message = "Please provide name") String name,
			@NotNull(message = "Please provide house number") Long number,
			@NotEmpty(message = "Please provide street") String street,
			@Size(min = 6, max = 8, message = "Please provide minimum six digit postal code") @NotEmpty(message = "Please provide postal coder") String postalCode,
			@NotEmpty(message = "please provide city") String city,
			@NotEmpty(message = "please provide state") String state) {
		super();
		this.name = name;
		this.number = number;
		this.street = street;
		this.postalCode = postalCode;
		this.city = city;
		this.state = state;
	}









	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Long getNumber() {
		return number;
	}

	public void setNumber(Long number) {
		this.number = number;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getPostalCode() {
		return postalCode;
	}

	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((city == null) ? 0 : city.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((number == null) ? 0 : number.hashCode());
		result = prime * result + ((postalCode == null) ? 0 : postalCode.hashCode());
		result = prime * result + ((state == null) ? 0 : state.hashCode());
		result = prime * result + ((street == null) ? 0 : street.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		House other = (House) obj;
		if (city == null) {
			if (other.city != null)
				return false;
		} else if (!city.equals(other.city))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (number == null) {
			if (other.number != null)
				return false;
		} else if (!number.equals(other.number))
			return false;
		if (postalCode == null) {
			if (other.postalCode != null)
				return false;
		} else if (!postalCode.equals(other.postalCode))
			return false;
		if (state == null) {
			if (other.state != null)
				return false;
		} else if (!state.equals(other.state))
			return false;
		if (street == null) {
			if (other.street != null)
				return false;
		} else if (!street.equals(other.street))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "House [id=" + id + ", name=" + name + ", number=" + number + ", street=" + street + ", postalCode="
				+ postalCode + ", city=" + city + ", state=" + state + "]";
	}

	
}

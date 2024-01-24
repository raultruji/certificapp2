package com.raultruji.certificapp2.domain.models;



import java.io.Serializable;
import java.time.LocalDateTime;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.stereotype.Component;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Data;


@Data
@Embeddable
public class Audit implements Serializable{
	
	public Audit() {};
	
	
	public Audit(LocalDateTime createdOn, LocalDateTime updatedOn) {
		super();
		this.createdOn = createdOn;
		this.updatedOn = updatedOn;
	}


	//private final LocalDateTime ltime = LocalDateTime.now(); 
	//TODO  LocalDateTime in a TIMESTAMP column
	
	@CreatedDate
	@Column(name = "created_on", nullable = false)
    private LocalDateTime createdOn;
	@LastModifiedDate
    @Column(name = "updated_on", nullable = true)
    private LocalDateTime updatedOn;
    
   
}

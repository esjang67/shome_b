package com.esjang.sthome.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "tbl_doitbatch")
//@IdClass(DoItBatchKey.class)
@Data
@NoArgsConstructor
@SequenceGenerator(
		name = "BATCH_SEQ_GENERATOR"
	    , sequenceName = "BATCH_SEQ"
	    , initialValue = 1
	    , allocationSize = 1
	)
public class DoItBatch {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "BATCH_SEQ_GENERATOR")
	private Integer	id;
	
//	@Column(nullable = false, length = 10)
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "userid", referencedColumnName = "userid", insertable = true, updatable = false)
	private User user;
	
	@Column(nullable = false, length = 7)
	private String defineday;
		
	@Column(nullable = false, length = 500)
	private String content;

}

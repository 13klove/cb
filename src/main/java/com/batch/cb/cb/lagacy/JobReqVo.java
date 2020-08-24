package com.batch.cb.cb.lagacy;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class JobReqVo {

	private String jobName;
	private String jobStatus;
	private String jobReqFile;
	private String jobResultFile;
	private String jobDiv;

}

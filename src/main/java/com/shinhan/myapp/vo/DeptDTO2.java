package com.shinhan.myapp.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
 
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter 
public class DeptDTO2 {
	int dept_id  ;   // column 이름과 다르다 
	String dept_name ;  // column 이름과 다르다
	int manager_id  ;  // column 이름과 같다
	int location_id  ;     // column 이름과 같다
}


















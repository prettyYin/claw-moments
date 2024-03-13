package com.moments.claw.domain.common.domain;

import com.moments.claw.domain.common.utils.StrUtils;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.StringUtils;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class PageDomain {
	private Integer pageNum;
	private Integer pageSize;
	private String orderByColumn;
	private String isAsc = "asc";

	public String getOrderBy() {
		return StringUtils.isEmpty(this.orderByColumn) ? "" : StrUtils.toUnderScoreCase(this.orderByColumn) + " " + this.isAsc;
	}
}

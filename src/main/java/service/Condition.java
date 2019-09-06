package service;

public enum Condition {
	CONTAIN("contain", "包含"),
	NOT_CONTAIN("not_contain", "不包含"),
	EQUAL("equal", "等於"),
	NOT_EQUAL("not_equal", "不等於"),
	START("start", "開頭"),
	NOT_START("not_start", "開頭不是"),
	END("end", "結尾"),
	NOT_END("not_end", "結尾不是");
	
	
	private final String condition;
	private final String conditionName;
	private Condition(String condition, String conditionName){
		this.condition = condition;
		this.conditionName = conditionName;
	}
	public String getCondition() {
		return condition;
	}
	public String getConditionName() {
		return conditionName;
	}
	
}

package util.zapiObjects;


import com.google.gson.Gson;
import com.google.gson.JsonObject;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import util.jiraObjects.Issue;

public class ExecutionDetails {
	public String id;
	public String issueId;
	public String versionId;
	public String projectId;
	public String executionId;
	public int orderId;
	public String createdBy;
	public String createdByAccountId;
	public ExecutionStatus status;
	public String cycleName;
	public Issue[] defects;
	public String cycleId;
	public String projectCycleVersionIndex;
	public String projectIssueCycleVersionIndex;
	private Logger logger = LogManager.getLogger(ExecutionDetails.class);
	
	public String getUpdateJson() {

//		String json="{";
//
//		json+="\"status\":{\"id\":"+status.id+"},";
//		json+="projectId\":"+projectId+",\"";
//		json+="issueId\":"+issueId+",\"";
//		json+="cycleId\":\""+cycleId+"\",\"";
//		json+="versionId\":"+versionId;
//
//		if(defects != null && defects.length>0) {
//			json += ",\"defects\":[";
//			for(int i =0; i < defects.length; i ++) {
//				if(i>0) {
//					json+=",";
//				}
//				json += "\""+defects[i].id+"\"";
//			}
//			json += "]";
//		}
//
//		json+="}";
		JsonObject status= new JsonObject();
		status.addProperty("id",this.status.id);
		JsonObject object = new JsonObject();
		object.add("status",status);
		object.addProperty("id",id);
		object.addProperty("projectId",projectId);
		object.addProperty("issueId",issueId);
		object.addProperty("cycleId",cycleId);
		object.addProperty("versionId",versionId);
		logger.info(object.getAsString());
		return new Gson().toJson(object);
	}
}

package util.zapiObjects;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

public class CycleList {
	public ProjectCycles[] cycles;


	public static String createCycleJson(int versionId,int projectId,String cycleId){
		JsonObject object = new JsonObject();
		object.addProperty("versionId",versionId);
		object.addProperty("projectId",projectId);
		object.addProperty("cycleId",cycleId);
		return new Gson().toJson(object);
	}
}
